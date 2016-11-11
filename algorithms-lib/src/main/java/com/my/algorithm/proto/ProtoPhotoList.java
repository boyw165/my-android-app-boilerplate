// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protocol/ProtoPhotoList.proto

package com.my.algorithm.proto;

public final class ProtoPhotoList {
  private ProtoPhotoList() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface MsgPhotoListOrBuilder extends
      // @@protoc_insertion_point(interface_extends:com.my.algorithm.proto.MsgPhotoList)
      com.google.protobuf.MessageLiteOrBuilder {

    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    java.util.List<com.my.algorithm.proto.ProtoPhoto.MsgPhoto> 
        getItemsList();
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    com.my.algorithm.proto.ProtoPhoto.MsgPhoto getItems(int index);
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    int getItemsCount();
  }
  /**
   * Protobuf type {@code com.my.algorithm.proto.MsgPhotoList}
   */
  public  static final class MsgPhotoList extends
      com.google.protobuf.GeneratedMessageLite<
          MsgPhotoList, MsgPhotoList.Builder> implements
      // @@protoc_insertion_point(message_implements:com.my.algorithm.proto.MsgPhotoList)
      MsgPhotoListOrBuilder {
    private MsgPhotoList() {
      items_ = emptyProtobufList();
    }
    public static final int ITEMS_FIELD_NUMBER = 1;
    private com.google.protobuf.Internal.ProtobufList<com.my.algorithm.proto.ProtoPhoto.MsgPhoto> items_;
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    public java.util.List<com.my.algorithm.proto.ProtoPhoto.MsgPhoto> getItemsList() {
      return items_;
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    public java.util.List<? extends com.my.algorithm.proto.ProtoPhoto.MsgPhotoOrBuilder> 
        getItemsOrBuilderList() {
      return items_;
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    public int getItemsCount() {
      return items_.size();
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    public com.my.algorithm.proto.ProtoPhoto.MsgPhoto getItems(int index) {
      return items_.get(index);
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    public com.my.algorithm.proto.ProtoPhoto.MsgPhotoOrBuilder getItemsOrBuilder(
        int index) {
      return items_.get(index);
    }
    private void ensureItemsIsMutable() {
      if (!items_.isModifiable()) {
        items_ =
            com.google.protobuf.GeneratedMessageLite.mutableCopy(items_);
       }
    }

    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void setItems(
        int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureItemsIsMutable();
      items_.set(index, value);
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void setItems(
        int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto.Builder builderForValue) {
      ensureItemsIsMutable();
      items_.set(index, builderForValue.build());
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void addItems(com.my.algorithm.proto.ProtoPhoto.MsgPhoto value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureItemsIsMutable();
      items_.add(value);
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void addItems(
        int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureItemsIsMutable();
      items_.add(index, value);
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void addItems(
        com.my.algorithm.proto.ProtoPhoto.MsgPhoto.Builder builderForValue) {
      ensureItemsIsMutable();
      items_.add(builderForValue.build());
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void addItems(
        int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto.Builder builderForValue) {
      ensureItemsIsMutable();
      items_.add(index, builderForValue.build());
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void addAllItems(
        java.lang.Iterable<? extends com.my.algorithm.proto.ProtoPhoto.MsgPhoto> values) {
      ensureItemsIsMutable();
      com.google.protobuf.AbstractMessageLite.addAll(
          values, items_);
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void clearItems() {
      items_ = emptyProtobufList();
    }
    /**
     * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
     */
    private void removeItems(int index) {
      ensureItemsIsMutable();
      items_.remove(index);
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      for (int i = 0; i < items_.size(); i++) {
        output.writeMessage(1, items_.get(i));
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < items_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, items_.get(i));
      }
      size += unknownFields.getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, data, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input);
    }
    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageLite.parseFrom(
          DEFAULT_INSTANCE, input, extensionRegistry);
    }

    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    /**
     * Protobuf type {@code com.my.algorithm.proto.MsgPhotoList}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList, Builder> implements
        // @@protoc_insertion_point(builder_implements:com.my.algorithm.proto.MsgPhotoList)
        com.my.algorithm.proto.ProtoPhotoList.MsgPhotoListOrBuilder {
      // Construct using com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList.newBuilder()
      private Builder() {
        super(DEFAULT_INSTANCE);
      }


      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public java.util.List<com.my.algorithm.proto.ProtoPhoto.MsgPhoto> getItemsList() {
        return java.util.Collections.unmodifiableList(
            instance.getItemsList());
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public int getItemsCount() {
        return instance.getItemsCount();
      }/**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public com.my.algorithm.proto.ProtoPhoto.MsgPhoto getItems(int index) {
        return instance.getItems(index);
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder setItems(
          int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto value) {
        copyOnWrite();
        instance.setItems(index, value);
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder setItems(
          int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto.Builder builderForValue) {
        copyOnWrite();
        instance.setItems(index, builderForValue);
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder addItems(com.my.algorithm.proto.ProtoPhoto.MsgPhoto value) {
        copyOnWrite();
        instance.addItems(value);
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder addItems(
          int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto value) {
        copyOnWrite();
        instance.addItems(index, value);
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder addItems(
          com.my.algorithm.proto.ProtoPhoto.MsgPhoto.Builder builderForValue) {
        copyOnWrite();
        instance.addItems(builderForValue);
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder addItems(
          int index, com.my.algorithm.proto.ProtoPhoto.MsgPhoto.Builder builderForValue) {
        copyOnWrite();
        instance.addItems(index, builderForValue);
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder addAllItems(
          java.lang.Iterable<? extends com.my.algorithm.proto.ProtoPhoto.MsgPhoto> values) {
        copyOnWrite();
        instance.addAllItems(values);
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder clearItems() {
        copyOnWrite();
        instance.clearItems();
        return this;
      }
      /**
       * <code>repeated .com.my.algorithm.proto.MsgPhoto items = 1;</code>
       */
      public Builder removeItems(int index) {
        copyOnWrite();
        instance.removeItems(index);
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.my.algorithm.proto.MsgPhotoList)
    }
    private byte memoizedIsInitialized = -1;
    protected final Object dynamicMethod(
        com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
        Object arg0, Object arg1) {
      switch (method) {
        case NEW_MUTABLE_INSTANCE: {
          return new com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList();
        }
        case IS_INITIALIZED: {
          byte isInitialized = memoizedIsInitialized;
          if (isInitialized == 1) return DEFAULT_INSTANCE;
          if (isInitialized == 0) return null;

          boolean shouldMemoize = ((Boolean) arg0).booleanValue();
          for (int i = 0; i < getItemsCount(); i++) {
            if (!getItems(i).isInitialized()) {
              if (shouldMemoize) {
                memoizedIsInitialized = 0;
              }
              return null;
            }
          }
          if (shouldMemoize) memoizedIsInitialized = 1;
          return DEFAULT_INSTANCE;

        }
        case MAKE_IMMUTABLE: {
          items_.makeImmutable();
          return null;
        }
        case NEW_BUILDER: {
          return new Builder();
        }
        case VISIT: {
          Visitor visitor = (Visitor) arg0;
          com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList other = (com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList) arg1;
          items_= visitor.visitList(items_, other.items_);
          if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
              .INSTANCE) {
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
                case 10: {
                  if (!items_.isModifiable()) {
                    items_ =
                        com.google.protobuf.GeneratedMessageLite.mutableCopy(items_);
                  }
                  items_.add(
                      input.readMessage(com.my.algorithm.proto.ProtoPhoto.MsgPhoto.parser(), extensionRegistry));
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
          if (PARSER == null) {    synchronized (com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList.class) {
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


    // @@protoc_insertion_point(class_scope:com.my.algorithm.proto.MsgPhotoList)
    private static final com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new MsgPhotoList();
      DEFAULT_INSTANCE.makeImmutable();
    }

    public static com.my.algorithm.proto.ProtoPhotoList.MsgPhotoList getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static volatile com.google.protobuf.Parser<MsgPhotoList> PARSER;

    public static com.google.protobuf.Parser<MsgPhotoList> parser() {
      return DEFAULT_INSTANCE.getParserForType();
    }
  }


  static {
  }

  // @@protoc_insertion_point(outer_class_scope)
}
