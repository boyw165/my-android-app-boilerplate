// Copyright (c) 2016-present boyw165
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
//    The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
//    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.my.boilerplate.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.my.boilerplate.R;
import com.my.boilerplate.util.ScrollViewUtil;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * The layout supporting a drop-down child view with drawer liked fling gesture.
 */
public class DropDownMenuLayout extends ViewGroup
    implements INavMenu {

    private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION = Build.VERSION.SDK_INT >= 21;
    private static final float ANIMATION_DURATION_OPEN = 300.f;
    private static final float ANIMATION_DURATION_CLOSE = 250.f;

    /**
     * Minimum drawer margin in DP.
     */
    private static final int MIN_DRAWER_MARGIN = 64;

    /**
     * Drawer elevation in DP.
     */
    private static final int DRAWER_ELEVATION = 10;

    private float mDrawerElevation;
    /**
     * Distance to travel before a drag may begin.
     */
    private int mTouchSlop;

    private static final int STATE_DRAWER_CLOSED = 0x0;
    private static final int STATE_DRAWER_OPENED = 0x1;
    private static final int STATE_DRAWER_OPENING = 0x2;
    private static final int STATE_DRAWER_CLOSING = 0x3;

    private int mDrawerState = STATE_DRAWER_CLOSED;

    private float mTouchInitY;
    private float mTouchCurrentY;

    private Matrix mChildInverseMatrix;
    private float[] mChildTouchPoint;

    /**
     * The view with isDrawer=true LayoutParams.
     * <br/>
     * See {@link LayoutParams#isDrawer}.
     */
    private View mDrawerView;
    /**
     * The view is being dragging.
     */
    private View mTouchingChild;
    /**
     * Animator for the drawer animation.
     */
    private AnimatorSet mAnimatorSet;

    private OnMenuStateChange mMenuStateListener;

    public DropDownMenuLayout(Context context) {
        this(context, null);
    }

    public DropDownMenuLayout(Context context,
                              AttributeSet attrs) {
        super(context, attrs);

        final float density = getResources().getDisplayMetrics().density;
        final ViewConfiguration vc = ViewConfiguration.get(context);

        mDrawerElevation = DRAWER_ELEVATION * density;
        mTouchSlop = vc.getScaledTouchSlop();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        mChildTouchPoint = new float[2];
        mChildInverseMatrix = new Matrix();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        mChildTouchPoint = null;
        mChildInverseMatrix = null;
        mDrawerView = null;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            throw new IllegalArgumentException(
                "DropDownMenuLayout must be measured with MeasureSpec.EXACTLY.");
        }

        setMeasuredDimension(widthSize, heightSize);

        final int childCount = getChildCount();
        if (childCount != 2) {
            throw new IllegalStateException(
                "DropDownMenuLayout should always have two children views " +
                "where one is a drawer view with \"isDrawerView=true\" " +
                "LayoutParams and the other is a content view.");
        }

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (isDrawerView(child)) {
                mDrawerView = child;

                // TODO: Figure out why setting the view elevation is handled
                // TODO: in the onMeasure function.
                if (SET_DRAWER_SHADOW_FROM_ELEVATION) {
                    if (ViewCompat.getElevation(child) != mDrawerElevation) {
                        ViewCompat.setElevation(child, mDrawerElevation);
                    }
                }

//                final int drawerWidthSpec = getChildMeasureSpec(
//                    widthMeasureSpec,
//                    lp.leftMargin + lp.rightMargin,
//                    lp.width);
                final int drawerWidthSpec = MeasureSpec.makeMeasureSpec(
                    widthSize - lp.leftMargin - lp.rightMargin,
                    MeasureSpec.EXACTLY);
                final int drawerHeightSpec = getChildMeasureSpec(
                    heightMeasureSpec,
                    lp.topMargin + lp.bottomMargin,
                    lp.height);

                child.measure(drawerWidthSpec, drawerHeightSpec);
            } else {
                // Content views get measured at exactly the layout's size.
                final int contentWidthSpec = MeasureSpec.makeMeasureSpec(
                    widthSize - lp.leftMargin - lp.rightMargin,
                    MeasureSpec.EXACTLY);
                final int contentHeightSpec = MeasureSpec.makeMeasureSpec(
                    heightSize - lp.topMargin - lp.bottomMargin,
                    MeasureSpec.EXACTLY);
//                final int contentWidthSpec = getChildMeasureSpec(
//                    widthMeasureSpec,
//                    lp.leftMargin + lp.rightMargin,
//                    lp.width);
//                final int contentHeightSpec = getChildMeasureSpec(
//                    heightMeasureSpec,
//                    lp.topMargin + lp.bottomMargin,
//                    lp.height);

                child.measure(contentWidthSpec, contentHeightSpec);
            }
//            else {
//                throw new IllegalStateException(
//                    "Child " + child + " at " + i + "is neither a drawer " +
//                    "view nor a ScrollingView view.");
//            }
        }
    }

    @Override
    protected void onLayout(boolean changed,
                            int left,
                            int top,
                            int right,
                            int bottom) {
        final int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            final LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if (isDrawerView(child)) {
                child.layout(lp.leftMargin,
                             -childHeight,
                             lp.rightMargin + childWidth,
                             0);
            } else {
                child.layout(lp.leftMargin,
                             lp.topMargin,
                             lp.leftMargin + childWidth,
                             lp.topMargin + childHeight);
            }
        }
    }

    /**
     * Responsible for whether to intercept the touch event. If so, call
     * {@link DropDownMenuLayout#onTouchEvent(MotionEvent)}.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mDrawerView == null) {
            return super.dispatchTouchEvent(event);
        }

        final int action = MotionEventCompat.getActionMasked(event);

        // The layout would be interested in that the touched list view is
        // scrolling out.
        if (action == MotionEvent.ACTION_DOWN) {
            mTouchInitY = event.getY();
            mTouchCurrentY = event.getY();
            Log.d("xyz", "dispatchTouchEvent(ACTION_DOWN)");
        } else if (action == MotionEvent.ACTION_MOVE) {
            final float dy = event.getY() - mTouchCurrentY;
            final float offsetY = event.getY() - mTouchInitY;
            final boolean ifCross = ifCrossDragSlop(offsetY);
            Log.d("xyz", String.format("dispatchTouchEvent(ACTION_MOVE), ifCross=%s", ifCross));

            if (ifCross) {
                final View touchingChild = findTopChildUnder(event.getX(), event.getY());
                // Will be set to true at the moment that this ViewGroup will
                // take over the touch event from its child view.
                boolean shouldIntercept = false;

                if (mDrawerState == STATE_DRAWER_CLOSED) {
                    // If sliding thumb down till the content view is being over
                    // scrolled, intercept the touch event.
                    if (ScrollViewUtil.ifOverScrollingVertically(touchingChild, dy)) {
                        mDrawerState = STATE_DRAWER_OPENING;
                        shouldIntercept = true;
                    }
                } else if (mDrawerState == STATE_DRAWER_OPENING) {
                    // If sliding thumb up till the drawer is completely hidden,
                    // return the touch event to the child view.
                    if (dy < 0 && mDrawerView.getTranslationY() == 0.f) {
                        Log.d("xyz", "  [*] Scrolling up will return touch event to the child view");
                        mDrawerState = STATE_DRAWER_CLOSED;

                        // Cancel the intercepting.
                        cancelSelfTouchEvent(event);

                        // Must cheat the action so that the child view knows
                        // to start a new touch handling session.
                        event.setAction(MotionEvent.ACTION_DOWN);
                    }
                } else if (mDrawerState == STATE_DRAWER_OPENED) {
                    if (touchingChild == mDrawerView) {
                        mDrawerState = STATE_DRAWER_CLOSING;
                        shouldIntercept = true;
                    }
                }

                if (shouldIntercept) {
                    // Because the layout will handle the dragging later,
                    // dispatch ACTION_CANCEL to all the child views.
                    cancelChildrenTouchEvent(event);

                    // We cheat the event so that mTouchInitY will be updated
                    // for calculating the translationY correctly.
                    event.setAction(MotionEvent.ACTION_DOWN);
                }
            }

            mTouchCurrentY = event.getY();
        } else if (action == MotionEvent.ACTION_UP ||
                   action == MotionEvent.ACTION_CANCEL) {
            Log.d("xyz", "dispatchTouchEvent(ACTION_UP|ACTION_CANCEL)");
            mTouchInitY = 0;
            mTouchCurrentY = 0;
        }

        if (mDrawerState == STATE_DRAWER_OPENING ||
            mDrawerState == STATE_DRAWER_CLOSING) {
            return onTouchEvent(event);
        } else {
            return super.dispatchTouchEvent(event);
        }
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        final int action = MotionEventCompat.getActionMasked(event);
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                break;
//        }
//
//        return mTouchingChild != null &&
//               mDrawerState == STATE_DRAWER_OPENING ||
//               mDrawerState == STATE_DRAWER_CLOSING;
//    }

    /**
     * Responsible for coordinating the position of the drawer and the content
     * view.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.d("xyz", "  onTouchEvent(ACTION_DOWN)");
                mTouchingChild = findTopChildUnder(event.getX(), event.getY());
                mTouchInitY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float offsetY = event.getY() - mTouchInitY;
                Log.d("xyz", String.format("  onTouchEvent(ACTION_MOVE), offsetY=%f", offsetY));

                if (mAnimatorSet != null) {
                    mAnimatorSet.cancel();
                    mAnimatorSet = null;
                }

                if (mDrawerState == STATE_DRAWER_OPENING) {
                    final float ty = Math.min(
                        Math.max(0, offsetY),
                        mDrawerView.getHeight());
                    ViewCompat.setTranslationY(mDrawerView, ty);
                    ViewCompat.setTranslationY(mTouchingChild, ty);
                } else if (mDrawerState == STATE_DRAWER_CLOSING) {
                    final float ty = Math.min(
                        Math.max(0, mDrawerView.getHeight() + offsetY),
                        mDrawerView.getHeight());
                    Log.d("xyz", String.format("    onTouchEvent(ACTION_MOVE), ty=%f", ty));
                    ViewCompat.setTranslationY(mDrawerView, ty);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                if (ifOpenDrawer()) {
                    Log.d("xyz", "  onTouchEvent(ACTION_UP|ACTION_CANCEL), open drawer");
                    openDrawer();
                } else {
                    Log.d("xyz", "  onTouchEvent(ACTION_UP|ACTION_CANCEL), close drawer");
                    closeDrawer();
                }

                mTouchingChild = null;
                break;
        }

        // Note: Be careful of using this layout in a nested view hierarchy.
        // Because it always return true, the parent's onTouchEvent will never
        // be called.
        return true;
    }

//    @Override
//    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//        super.requestDisallowInterceptTouchEvent(disallowIntercept);
//
//        // Ignore the disabling and pass to its parent.
//        if (getParent() != null) {
//            getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
//        }
//    }

    @Override
    public void setOnMenuStateChangeListener(OnMenuStateChange listener) {
        mMenuStateListener = listener;
    }

    public boolean isDrawerOpened() {
        return mDrawerState == STATE_DRAWER_OPENED;
    }

    public void openDrawer() {
        if (mDrawerView == null) return;

        mDrawerState = STATE_DRAWER_OPENED;

        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }

        // The duration is proportional to the translationY.
        final float ty1 = ViewCompat.getTranslationY(mDrawerView);
        final int duration1 = (int) (ANIMATION_DURATION_OPEN * (mDrawerView.getHeight() - Math.abs(ty1)) /
                                    mDrawerView.getHeight());

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mDrawerView, "translationY", mDrawerView.getHeight());
        anim1.setDuration(duration1);
        anim1.setInterpolator(new AccelerateDecelerateInterpolator());

        // TODO: Animation the translucent overlay.

        if (mTouchingChild != null && mTouchingChild != mDrawerView) {
            final float ty3 = mTouchingChild.getTranslationY();
            final int duration3 = (int) (ANIMATION_DURATION_OPEN * ty3) / mTouchingChild.getHeight();

            ObjectAnimator anim3 = ObjectAnimator.ofFloat(mTouchingChild, "translationY", 0);
            anim3.setDuration(duration3);
            anim3.setInterpolator(new AccelerateDecelerateInterpolator());

            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(anim1, anim3);
            mAnimatorSet.start();
        } else {
            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(anim1);
            mAnimatorSet.start();
        }

        if (mMenuStateListener != null) {
            mMenuStateListener.onShowMenu();
        }
    }

    public void closeDrawer() {
        if (mDrawerView == null) return;

        mDrawerState = STATE_DRAWER_CLOSED;

        if (mAnimatorSet != null) {
            mAnimatorSet.cancel();
        }

        // The duration is proportional to the translationY.
        final float ty1 = ViewCompat.getTranslationY(mDrawerView);
        final int duration1 = (int) (ANIMATION_DURATION_CLOSE * Math.abs(ty1) / mDrawerView.getHeight());

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(mDrawerView, "translationY", 0);
        anim1.setDuration(duration1);
        anim1.setInterpolator(new AccelerateDecelerateInterpolator());

        // TODO: Animation the translucent overlay.

        if (mTouchingChild != null && mTouchingChild != mDrawerView) {
            final float ty3 = mTouchingChild.getTranslationY();
            final int duration3 = (int) (ANIMATION_DURATION_OPEN * ty3) / mTouchingChild.getHeight();

            ObjectAnimator anim3 = ObjectAnimator.ofFloat(mTouchingChild, "translationY", 0);
            anim3.setDuration(duration3);
            anim3.setInterpolator(new AccelerateDecelerateInterpolator());

            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(anim1, anim3);
            mAnimatorSet.start();
        } else {
            mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(anim1);
            mAnimatorSet.start();
        }

        if (mMenuStateListener != null) {
            mMenuStateListener.onHideMenu();
        }
    }

    public void cancel() {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(MATCH_PARENT, MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    private boolean isDrawerView(View child) {
        LayoutParams params = (LayoutParams) child.getLayoutParams();
        return params.isDrawer;
    }

    private boolean ifCrossDragSlop(float dy) {
        return Math.abs(dy) > mTouchSlop;
    }

    /**
     * Find the topmost child under the given point within the parent view's
     * coordinate system. The child order is determined using
     * {@link Callback#getOrderedChildIndex(int)}.
     *
     * @param y Y position to test in the parent's coordinate system
     * @return The topmost child view under (x, y) or null if none found.
     */
    private View findTopChildUnder(float x, float y) {
        View foundChild = null;
        final int childCount = getChildCount();

        for (int i = childCount - 1; i >= 0; --i) {
            final View child = getChildAt(i);

            // Transform the touch point to the child's coordinate system.
            mChildTouchPoint[0] = x;
            mChildTouchPoint[1] = y;
            child.getMatrix().invert(mChildInverseMatrix);
            mChildInverseMatrix.mapPoints(mChildTouchPoint);

            final float childX = mChildTouchPoint[0];
            final float childY = mChildTouchPoint[1];
            if (childX >= child.getLeft() && childX <= child.getRight() &&
                childY >= child.getTop() && childY < child.getBottom()) {
                foundChild = child;
                break;
            }
        }

        return foundChild;
    }

    private void cancelSelfTouchEvent(MotionEvent event) {
        final MotionEvent canceledEvent = MotionEvent.obtain(event);

        canceledEvent.setAction(MotionEvent.ACTION_CANCEL);
        onTouchEvent(canceledEvent);
        canceledEvent.recycle();
    }

    private void cancelChildrenTouchEvent(MotionEvent event) {
        final MotionEvent canceledEvent = MotionEvent.obtain(event);

        canceledEvent.setAction(MotionEvent.ACTION_CANCEL);
        super.dispatchTouchEvent(canceledEvent);
        canceledEvent.recycle();
    }

    private boolean ifOpenDrawer() {
        if (mDrawerState == STATE_DRAWER_OPENING) {
            final float threshold = 0.35f * mDrawerView.getHeight();

            return mDrawerView != null &&
                   ViewCompat.getTranslationY(mDrawerView) > threshold;
        } else if (mDrawerState == STATE_DRAWER_CLOSING) {
            final float threshold = 0.8f * mDrawerView.getHeight();

            return mDrawerView != null &&
                   ViewCompat.getTranslationY(mDrawerView) > threshold;
        } else {
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Clazz //////////////////////////////////////////////////////////////////

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        private static final int FLAG_IS_CLOSED = 0x0;

        /**
         * The state of the drawer view.
         */
        private int openState;
        /**
         * Child view with "isDrawerView=true" attribute would be a drawer view.
         * <pre>
         *     <DropDownMenuLayout ...>
         *         <FrameLayout ...
         *             app:isDrawerView="true"/>
         *     <DropDownMenuLayout/>
         * </pre>
         */
        private boolean isDrawer;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);

            final TypedArray array = c.obtainStyledAttributes(attrs, R.styleable.DropDownMenuLayout);
            try {
                openState = FLAG_IS_CLOSED;
                isDrawer = array.getBoolean(R.styleable.DropDownMenuLayout_isDrawerView, false);
            } finally {
                array.recycle();
            }
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }
    }
}
