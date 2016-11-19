// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protocol/ProtoRectSlot.proto

package com.my.algorithm.proto;

public final class ProtoRectSlot {
  private ProtoRectSlot() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface MsgRectSlotOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.my.algorithm.proto.MsgRectSlot)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>required float x = 1;</code>
     */
    boolean hasX();
    /**
     * <code>required float x = 1;</code>
     */
    float getX();

    /**
     * <code>required float y = 2;</code>
     */
    boolean hasY();
    /**
     * <code>required float y = 2;</code>
     */
    float getY();

    /**
     * <code>required float width = 3;</code>
     */
    boolean hasWidth();
    /**
     * <code>required float width = 3;</code>
     */
    float getWidth();

    /**
     * <code>required float height = 4;</code>
     */
    boolean hasHeight();
    /**
     * <code>required float height = 4;</code>
     */
    float getHeight();

    /**
     * <code>required uint64 relatedPhotoId = 5;</code>
     */
    boolean hasRelatedPhotoId();
    /**
     * <code>required uint64 relatedPhotoId = 5;</code>
     */
    long getRelatedPhotoId();
  }
  /**
   * Protobuf type {@code com.my.algorithm.proto.MsgRectSlot}
   */
  public  static final class MsgRectSlot extends
      com.google.protobuf.GeneratedMessageLite<
          MsgRectSlot, MsgRectSlot.Builder> implements
      // @@protoc_insertion_point(message_implements:com.my.algorithm.proto.MsgRectSlot)
      MsgRectSlotOrBuilder {
    private MsgRectSlot() {
    }
    private int bitField0_;
    public static final int X_FIELD_NUMBER = 1;
    private float x_;
    /**
     * <code>required float x = 1;</code>
     */
    public boolean hasX() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required float x = 1;</code>
     */
    public float getX() {
      return x_;
    }
    /**
     * <code>required float x = 1;</code>
     */
    private void setX(float value) {
      bitField0_ |= 0x00000001;
      x_ = value;
    }
    /**
     * <code>required float x = 1;</code>
     */
    private void clearX() {
      bitField0_ = (bitField0_ & ~0x00000001);
      x_ = 0F;
    }

    public static final int Y_FIELD_NUMBER = 2;
    private float y_;
    /**
     * <code>required float y = 2;</code>
     */
    public boolean hasY() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required float y = 2;</code>
     */
    public float getY() {
      return y_;
    }
    /**
     * <code>required float y = 2;</code>
     */
    private void setY(float value) {
      bitField0_ |= 0x00000002;
      y_ = value;
    }
    /**
     * <code>required float y = 2;</code>
     */
    private void clearY() {
      bitField0_ = (bitField0_ & ~0x00000002);
      y_ = 0F;
    }

    public static final int WIDTH_FIELD_NUMBER = 3;
    private float width_;
    /**
     * <code>required float width = 3;</code>
     */
    public boolean hasWidth() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required float width = 3;</code>
     */
    public float getWidth() {
      return width_;
    }
    /**
     * <code>required float width = 3;</code>
     */
    private void setWidth(float value) {
      bitField0_ |= 0x00000004;
      width_ = value;
    }
    /**
     * <code>required float width = 3;</code>
     */
    private void clearWidth() {
      bitField0_ = (bitField0_ & ~0x00000004);
      width_ = 0F;
    }

    public static final int HEIGHT_FIELD_NUMBER = 4;
    private float height_;
    /**
     * <code>required float height = 4;</code>
     */
    public boolean hasHeight() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required float height = 4;</code>
     */
    public float getHeight() {
      return height_;
    }
    /**
     * <code>required float height = 4;</code>
     */
    private void setHeight(float value) {
      bitField0_ |= 0x00000008;
      height_ = value;
    }
    /**
     * <code>required float height = 4;</code>
     */
    private void clearHeight() {
      bitField0_ = (bitField0_ & ~0x00000008);
      height_ = 0F;
    }

    public static final int RELATEDPHOTOID_FIELD_NUMBER = 5;
    private long relatedPhotoId_;
    /**
     * <code>required uint64 relatedPhotoId = 5;</code>
     */
    public boolean hasRelatedPhotoId() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required uint64 relatedPhotoId = 5;</code>
     */
    public long getRelatedPhotoId() {
      return relatedPhotoId_;
    }
    /**
     * <code>required uint64 relatedPhotoId = 5;</code>
     */
    private void setRelatedPhotoId(long value) {
      bitField0_ |= 0x00000010;
      relatedPhotoId_ = value;
    }
    /**
     * <code>required uint64 relatedPhotoId = 5;</code>
     */
    private void clearRelatedPhotoId() {
      bitField0_ = (bitField0_ & ~0x00000010);
      relatedPhotoId_ = 0L;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeFloat(1, x_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeFloat(2, y_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeFloat(3, width_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeFloat(4, height_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeUInt64(5, relatedPhotoId_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(1, x_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(2, y_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(3, width_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(4, height_);
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt64Size(5, relatedPhotoId_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    /**
     * Protobuf type {@code com.my.algorithm.proto.MsgRectSlot}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot, Builder> implements
        // @@protoc_insertion_point(builder_implements:com.my.algorithm.proto.MsgRectSlot)
        com.my.algorithm.proto.ProtoRectSlot.MsgRectSlotOrBuilder {
      // Construct using com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <code>required float x = 1;</code>
       */
      public boolean hasX() {
        return instance.hasX();
      }
      /**
       * <code>required float x = 1;</code>
       */
      public float getX() {
        return instance.getX();
      }
      /**
       * <code>required float x = 1;</code>
       */
      public Builder setX(float value) {
        copyOnWrite();
        instance.setX(value);
        return this;
      }
      /**
       * <code>required float x = 1;</code>
       */
      public Builder clearX() {
        copyOnWrite();
        instance.clearX();
        return this;
      }

      /**
       * <code>required float y = 2;</code>
       */
      public boolean hasY() {
        return instance.hasY();
      }
      /**
       * <code>required float y = 2;</code>
       */
      public float getY() {
        return instance.getY();
      }
      /**
       * <code>required float y = 2;</code>
       */
      public Builder setY(float value) {
        copyOnWrite();
        instance.setY(value);
        return this;
      }
      /**
       * <code>required float y = 2;</code>
       */
      public Builder clearY() {
        copyOnWrite();
        instance.clearY();
        return this;
      }

      /**
       * <code>required float width = 3;</code>
       */
      public boolean hasWidth() {
        return instance.hasWidth();
      }
      /**
       * <code>required float width = 3;</code>
       */
      public float getWidth() {
        return instance.getWidth();
      }
      /**
       * <code>required float width = 3;</code>
       */
      public Builder setWidth(float value) {
        copyOnWrite();
        instance.setWidth(value);
        return this;
      }
      /**
       * <code>required float width = 3;</code>
       */
      public Builder clearWidth() {
        copyOnWrite();
        instance.clearWidth();
        return this;
      }

      /**
       * <code>required float height = 4;</code>
       */
      public boolean hasHeight() {
        return instance.hasHeight();
      }
      /**
       * <code>required float height = 4;</code>
       */
      public float getHeight() {
        return instance.getHeight();
      }
      /**
       * <code>required float height = 4;</code>
       */
      public Builder setHeight(float value) {
        copyOnWrite();
        instance.setHeight(value);
        return this;
      }
      /**
       * <code>required float height = 4;</code>
       */
      public Builder clearHeight() {
        copyOnWrite();
        instance.clearHeight();
        return this;
      }

      /**
       * <code>required uint64 relatedPhotoId = 5;</code>
       */
      public boolean hasRelatedPhotoId() {
        return instance.hasRelatedPhotoId();
      }
      /**
       * <code>required uint64 relatedPhotoId = 5;</code>
       */
      public long getRelatedPhotoId() {
        return instance.getRelatedPhotoId();
      }
      /**
       * <code>required uint64 relatedPhotoId = 5;</code>
       */
      public Builder setRelatedPhotoId(long value) {
        copyOnWrite();
        instance.setRelatedPhotoId(value);
        return this;
      }
      /**
       * <code>required uint64 relatedPhotoId = 5;</code>
       */
      public Builder clearRelatedPhotoId() {
        copyOnWrite();
        instance.clearRelatedPhotoId();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.my.algorithm.proto.MsgRectSlot)
    }
    private byte memoizedIsInitialized = -1;
    protected final Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        Object arg0, Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot();
        }
        case IS_INITIALIZED: {
          byte isInitialized = memoizedIsInitialized;
          if (isInitialized == 1) return DEFAULT_INSTANCE;
          if (isInitialized == 0) return null;

          boolean shouldMemoize = ((Boolean) arg0).booleanValue();
          if (!hasX()) {
            if (shouldMemoize) {
              memoizedIsInitialized = 0;
            }
            return null;
          }
          if (!hasY()) {
            if (shouldMemoize) {
              memoizedIsInitialized = 0;
            }
            return null;
          }
          if (!hasWidth()) {
            if (shouldMemoize) {
              memoizedIsInitialized = 0;
            }
            return null;
          }
          if (!hasHeight()) {
            if (shouldMemoize) {
              memoizedIsInitialized = 0;
            }
            return null;
          }
          if (!hasRelatedPhotoId()) {
            if (shouldMemoize) {
              memoizedIsInitialized = 0;
            }
            return null;
          }
          if (shouldMemoize) memoizedIsInitialized = 1;
          return DEFAULT_INSTANCE;

        }
        case MAKE_IMMUTABLE: {
          return null;
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case VISIT: {
          Visitor visitor = (Visitor) arg0;
          com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot other = (com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot) arg1;
          x_ = visitor.visitFloat(
              hasX(), x_,
              other.hasX(), other.x_);
          y_ = visitor.visitFloat(
              hasY(), y_,
              other.hasY(), other.y_);
          width_ = visitor.visitFloat(
              hasWidth(), width_,
              other.hasWidth(), other.width_);
          height_ = visitor.visitFloat(
              hasHeight(), height_,
              other.hasHeight(), other.height_);
          relatedPhotoId_ = visitor.visitLong(
              hasRelatedPhotoId(), relatedPhotoId_,
              other.hasRelatedPhotoId(), other.relatedPhotoId_);
          if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
              .INSTANCE) {
            bitField0_ |= other.bitField0_;
          }
          return this;
        }
        case MERGE_FROM_STREAM: {
          com.google.protobuf.CodedInputStream input =
              (com.google.protobuf.CodedInputStream) arg0;
          com.google.protobuf.ExtensionRegistryLite extensionRegistry =
              (com.google.protobuf.ExtensionRegistryLite) arg1;
          try {
            boolean done = false;
            while (!done) {
              int tag = input.readTag();
              switch (tag) {
                case 0:
                  done = true;
                  break;
                default: {
                  if (!parseUnknownField(tag, input)) {
                    done = true;
                  }
                  break;
                }
                case 13: {
                  bitField0_ |= 0x00000001;
                  x_ = input.readFloat();
                  break;
                }
                case 21: {
                  bitField0_ |= 0x00000002;
                  y_ = input.readFloat();
                  break;
                }
                case 29: {
                  bitField0_ |= 0x00000004;
                  width_ = input.readFloat();
                  break;
                }
                case 37: {
                  bitField0_ |= 0x00000008;
                  height_ = input.readFloat();
                  break;
                }
                case 40: {
                  bitField0_ |= 0x00000010;
                  relatedPhotoId_ = input.readUInt64();
                  break;
                }
              }
            }
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw new RuntimeException(e.setUnfinishedMessage(this));
          } catch (java.io.IOException e) {
            throw new RuntimeException(
                new com.google.protobuf.InvalidProtocolBufferException(
                    e.getMessage()).setUnfinishedMessage(this));
          } finally {
          }
        }
        case GET_DEFAULT_INSTANCE: {
          return DEFAULT_INSTANCE;
        }
        case GET_PARSER: {
          if (PARSER == null) {    synchronized (com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot.class) {
              if (PARSER == null) {
                PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
              }
            }
          }
          return PARSER;
        }
      }
      throw new UnsupportedOperationException();
    }


    // @@protoc_insertion_point(class_scope:com.my.algorithm.proto.MsgRectSlot)
    private static final com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new MsgRectSlot();
      DEFAULT_INSTANCE.makeImmutable();
    }

    public static com.my.algorithm.proto.ProtoRectSlot.MsgRectSlot getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<MsgRectSlot> PARSER;

    public static com.google.protobuf.Parser<MsgRectSlot> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}