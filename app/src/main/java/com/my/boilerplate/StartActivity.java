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

package com.my.boilerplate;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.my.boilerplate.util.ViewUtil;
import com.my.boilerplate.view.INavMenu;
import com.my.boilerplate.view.IProgressBarView;
import com.my.boilerplate.view.SampleMenuAdapter;
import com.my.boilerplate.view.ScrapView;

public class StartActivity
    extends AppCompatActivity
    implements IProgressBarView {

    private final static String TAG = StartActivity.class.getCanonicalName();

    private Toolbar mToolbar;
    private ListView mStartMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // List menu.
        mStartMenu = (ListView) findViewById(R.id.menu);
        mStartMenu.setAdapter(onStartMenuCreate());
        mStartMenu.setOnItemClickListener(onClickStartMenuItem());

//        // The collage editor.
//        mCollageEditor = (CollageLayout) findViewById(R.id.collage_editor);

//        // Example: Chain multiple observables.
//        doHttpRequests();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                toggleDrawerMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgressBar() {
        ViewUtil
            .with(this)
            .setProgressBarCancelable(false)
            .showProgressBar(getString(R.string.loading));
    }

    @Override
    public void hideProgressBar() {
        ViewUtil
            .with(this)
            .hideProgressBar();
    }

    @Override
    public void updateProgress(int progress) {
        showProgressBar();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Protected / Private Methods ////////////////////////////////////////////

    private void toggleDrawerMenu() {
        // DO NOTHING
    }

    private INavMenu.OnMenuStateChange onMenuStateChange() {
        return new INavMenu.OnMenuStateChange() {
            @Override
            public void onShowMenu() {
                mToolbar.setNavigationIcon(R.drawable.ic_close);
            }

            @Override
            public void onHideMenu() {
                mToolbar.setNavigationIcon(R.drawable.ic_list_black_24px);
            }
        };
    }

    @SuppressWarnings({"unchecked"})
    protected SampleMenuAdapter onStartMenuCreate() {
        return new SampleMenuAdapter(
            this,
            new Pair[] {
                // item 0.
                new Pair<>("CollageEditor",
                           "A view-based collage editor (on-going)."),
                // item 1.
                new Pair<>("CoordinatorLayout and Behavior",
                           "Use the CoordinatorLayout and Behavior to imitate " +
                           "the drag-and-drop drawer menu in the vertical way. " +
                           "I use \"imitate\" here is because I think the best " +
                           "solution is to customize a ViewGroup."),
                // item 2.
                new Pair<>("RecyclerView",
                           "Add/Remove/Reposition the items in the RecyclerView " +
                           "with animation. (on-going)"),
                // item 3.
                new Pair<>("Notification",
                           "Fire notifications and lead the user to the Activity " +
                           "in the current task or in a new task."),
                // item 4.
                new Pair<>("Services",
                           "Use the service to do a long operation in the background " +
                           "and notify the binding Activity the processing status.\n" +
                           "The service might be still alive when the task is moved " +
                           "to the background."),
                // item 5.
                new Pair<>("DownloadManager",
                           "Use DownloadManager (a system service) to download files."),
                // item 6.
                new Pair<>("AlertManager",
                           "(constructing)"),
                // item 7.
                new Pair<>("BroadcastReceiver",
                           "(constructing)"),
                // item 8.
                new Pair<>("RxJava-2",
                           "(constructing)."),
                // item 9.
                new Pair<>("OkHttp",
                           "(constructing)."),
            });
    }

    private OnItemClickListener onClickStartMenuItem() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(StartActivity.this,
                                                 CollageEditorActivity.class)
                                          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        break;
                    case 1:
                        startActivity(new Intent(StartActivity.this,
                                                 DrawerSampleActivity.class)
                                          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        break;
                    case 2:
                        break;
                    case 3:
                        startActivity(new Intent(StartActivity.this,
                                                 NotificationSampleActivity.class)
                                          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        break;
                    case 4:
                        startActivity(new Intent(StartActivity.this,
                                                 ServiceSampleActivity.class)
                                          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        break;
                    case 5:
                        startActivity(new Intent(StartActivity.this,
                                                 DownloadManagerSampleActivity.class)
                                          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        break;
                    default:
                        // DO NOTHING.
                }
            }
        };
    }

    protected OnClickListener onClickAddScrap(float ratio) {
        final float scrapRatio = ratio;

        return new OnClickListener() {
            @Override
            public void onClick(View view) {
//                int scrapWidth = (int) ((float) mCollageEditor.getWidth() / 3.f);
                ScrapView scrap = new ScrapView(StartActivity.this);

                scrap.setTranslationX(100);
                scrap.setTranslationY(100);
//                scrap.setScaleX(0.33f);
//                scrap.setScaleY(0.33f);
                scrap.setAspectRatio(scrapRatio);
                scrap.setBackgroundColor(Color.rgb((int) ((double) 0xFF * Math.random()),
                                                   (int) ((double) 0xFF * Math.random()),
                                                   (int) ((double) 0xFF * Math.random())));

//                mCollageEditor.addView(scrap);

                Toast.makeText(StartActivity.this,
                               String.format("Make a scrap (aspect ratio is %f)", scrapRatio),
                               Toast.LENGTH_SHORT);
            }
        };
    }

//    protected void doHttpRequests() {
//        Observable
//            .just(null)
//            // Ask for permission.
//            .concatWith(PermUtil
//                            .with(this)
//                            .request(Manifest.permission.CAMERA,
//                                     Manifest.permission.WRITE_EXTERNAL_STORAGE))
//            // Request data.
//            .concatWith(WebApiUtil
//                            .with(this)
//                            .showProgressBar(this)
//                            .getJsonWhatever())
//            // Only handle the non-NULL stream.
//            .filter(new Func1<Object, Boolean>() {
//                @Override
//                public Boolean call(Object o) {
//                    return o != null;
//                }
//            })
//            .subscribe(new Action1<Object>() {
//                           @Override
//                           public void call(Object o) {
//                               if (o == null) {
//                                   Log.i(TAG, "null");
//                               } else if (o instanceof Boolean) {
//                                   if ((Boolean) o) {
//                                       Log.i(TAG, "Permissions are granted.");
//                                   } else {
//                                       Log.i(TAG, "Permissions are not granted.");
//                                   }
//                               } else if (o instanceof JsonWhatever) {
//                                   JsonWhatever data = (JsonWhatever) o;
//                                   Log.i(TAG, String.format("Get JsonWhatever data = %s", data.toString()));
//                               }
//                           }
//                       },
//                       new Action1<Throwable>() {
//                           @Override
//                           public void call(Throwable t) {
//                               Log.e(TAG, "onError", t);
//                           }
//                       });
//    }
}
