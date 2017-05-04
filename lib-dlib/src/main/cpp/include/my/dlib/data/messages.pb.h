// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: messages.proto

#ifndef PROTOBUF_messages_2eproto__INCLUDED
#define PROTOBUF_messages_2eproto__INCLUDED

#include <string>

#include <google/protobuf/stubs/common.h>

#if GOOGLE_PROTOBUF_VERSION < 3002000
#error This file was generated by a newer version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please update
#error your headers.
#endif
#if 3002000 < GOOGLE_PROTOBUF_MIN_PROTOC_VERSION
#error This file was generated by an older version of protoc which is
#error incompatible with your Protocol Buffer headers.  Please
#error regenerate this file with a newer version of protoc.
#endif

#include <google/protobuf/io/coded_stream.h>
#include <google/protobuf/arena.h>
#include <google/protobuf/arenastring.h>
#include <google/protobuf/generated_message_util.h>
#include <google/protobuf/metadata.h>
#include <google/protobuf/message_lite.h>
#include <google/protobuf/repeated_field.h>  // IWYU pragma: export
#include <google/protobuf/extension_set.h>  // IWYU pragma: export
// @@protoc_insertion_point(includes)
namespace com {
namespace my {
namespace jni {
namespace dlib {
namespace data {
class Face;
class FaceDefaultTypeInternal;
extern FaceDefaultTypeInternal _Face_default_instance_;
class FaceList;
class FaceListDefaultTypeInternal;
extern FaceListDefaultTypeInternal _FaceList_default_instance_;
class Landmark;
class LandmarkDefaultTypeInternal;
extern LandmarkDefaultTypeInternal _Landmark_default_instance_;
class LandmarkList;
class LandmarkListDefaultTypeInternal;
extern LandmarkListDefaultTypeInternal _LandmarkList_default_instance_;
class Rectangle;
class RectangleDefaultTypeInternal;
extern RectangleDefaultTypeInternal _Rectangle_default_instance_;
}  // namespace data
}  // namespace dlib
}  // namespace jni
}  // namespace my
}  // namespace com

namespace com {
namespace my {
namespace jni {
namespace dlib {
namespace data {

namespace protobuf_messages_2eproto {
// Internal implementation detail -- do not call these.
struct TableStruct {
  static const ::google::protobuf::uint32 offsets[];
  static void InitDefaultsImpl();
  static void Shutdown();
};
void AddDescriptors();
void InitDefaults();
}  // namespace protobuf_messages_2eproto

// ===================================================================

class Landmark : public ::google::protobuf::MessageLite /* @@protoc_insertion_point(class_definition:com.my.jni.dlib.data.Landmark) */ {
 public:
  Landmark();
  virtual ~Landmark();

  Landmark(const Landmark& from);

  inline Landmark& operator=(const Landmark& from) {
    CopyFrom(from);
    return *this;
  }

  static const Landmark& default_instance();

  static inline const Landmark* internal_default_instance() {
    return reinterpret_cast<const Landmark*>(
               &_Landmark_default_instance_);
  }

  void Swap(Landmark* other);

  // implements Message ----------------------------------------------

  inline Landmark* New() const PROTOBUF_FINAL { return New(NULL); }

  Landmark* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from)
    PROTOBUF_FINAL;
  void CopyFrom(const Landmark& from);
  void MergeFrom(const Landmark& from);
  void Clear() PROTOBUF_FINAL;
  bool IsInitialized() const PROTOBUF_FINAL;

  size_t ByteSizeLong() const PROTOBUF_FINAL;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) PROTOBUF_FINAL;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const PROTOBUF_FINAL;
  void DiscardUnknownFields();
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(Landmark* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::std::string GetTypeName() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // float x = 1;
  void clear_x();
  static const int kXFieldNumber = 1;
  float x() const;
  void set_x(float value);

  // float y = 2;
  void clear_y();
  static const int kYFieldNumber = 2;
  float y() const;
  void set_y(float value);

  // @@protoc_insertion_point(class_scope:com.my.jni.dlib.data.Landmark)
 private:

  ::google::protobuf::internal::InternalMetadataWithArenaLite _internal_metadata_;
  float x_;
  float y_;
  mutable int _cached_size_;
  friend struct  protobuf_messages_2eproto::TableStruct;
};
// -------------------------------------------------------------------

class LandmarkList : public ::google::protobuf::MessageLite /* @@protoc_insertion_point(class_definition:com.my.jni.dlib.data.LandmarkList) */ {
 public:
  LandmarkList();
  virtual ~LandmarkList();

  LandmarkList(const LandmarkList& from);

  inline LandmarkList& operator=(const LandmarkList& from) {
    CopyFrom(from);
    return *this;
  }

  static const LandmarkList& default_instance();

  static inline const LandmarkList* internal_default_instance() {
    return reinterpret_cast<const LandmarkList*>(
               &_LandmarkList_default_instance_);
  }

  void Swap(LandmarkList* other);

  // implements Message ----------------------------------------------

  inline LandmarkList* New() const PROTOBUF_FINAL { return New(NULL); }

  LandmarkList* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from)
    PROTOBUF_FINAL;
  void CopyFrom(const LandmarkList& from);
  void MergeFrom(const LandmarkList& from);
  void Clear() PROTOBUF_FINAL;
  bool IsInitialized() const PROTOBUF_FINAL;

  size_t ByteSizeLong() const PROTOBUF_FINAL;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) PROTOBUF_FINAL;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const PROTOBUF_FINAL;
  void DiscardUnknownFields();
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(LandmarkList* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::std::string GetTypeName() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // repeated .com.my.jni.dlib.data.Landmark landmarks = 1;
  int landmarks_size() const;
  void clear_landmarks();
  static const int kLandmarksFieldNumber = 1;
  const ::com::my::jni::dlib::data::Landmark& landmarks(int index) const;
  ::com::my::jni::dlib::data::Landmark* mutable_landmarks(int index);
  ::com::my::jni::dlib::data::Landmark* add_landmarks();
  ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >*
      mutable_landmarks();
  const ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >&
      landmarks() const;

  // @@protoc_insertion_point(class_scope:com.my.jni.dlib.data.LandmarkList)
 private:

  ::google::protobuf::internal::InternalMetadataWithArenaLite _internal_metadata_;
  ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark > landmarks_;
  mutable int _cached_size_;
  friend struct  protobuf_messages_2eproto::TableStruct;
};
// -------------------------------------------------------------------

class Rectangle : public ::google::protobuf::MessageLite /* @@protoc_insertion_point(class_definition:com.my.jni.dlib.data.Rectangle) */ {
 public:
  Rectangle();
  virtual ~Rectangle();

  Rectangle(const Rectangle& from);

  inline Rectangle& operator=(const Rectangle& from) {
    CopyFrom(from);
    return *this;
  }

  static const Rectangle& default_instance();

  static inline const Rectangle* internal_default_instance() {
    return reinterpret_cast<const Rectangle*>(
               &_Rectangle_default_instance_);
  }

  void Swap(Rectangle* other);

  // implements Message ----------------------------------------------

  inline Rectangle* New() const PROTOBUF_FINAL { return New(NULL); }

  Rectangle* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from)
    PROTOBUF_FINAL;
  void CopyFrom(const Rectangle& from);
  void MergeFrom(const Rectangle& from);
  void Clear() PROTOBUF_FINAL;
  bool IsInitialized() const PROTOBUF_FINAL;

  size_t ByteSizeLong() const PROTOBUF_FINAL;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) PROTOBUF_FINAL;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const PROTOBUF_FINAL;
  void DiscardUnknownFields();
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(Rectangle* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::std::string GetTypeName() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // float left = 1;
  void clear_left();
  static const int kLeftFieldNumber = 1;
  float left() const;
  void set_left(float value);

  // float top = 2;
  void clear_top();
  static const int kTopFieldNumber = 2;
  float top() const;
  void set_top(float value);

  // float right = 3;
  void clear_right();
  static const int kRightFieldNumber = 3;
  float right() const;
  void set_right(float value);

  // float bottom = 4;
  void clear_bottom();
  static const int kBottomFieldNumber = 4;
  float bottom() const;
  void set_bottom(float value);

  // @@protoc_insertion_point(class_scope:com.my.jni.dlib.data.Rectangle)
 private:

  ::google::protobuf::internal::InternalMetadataWithArenaLite _internal_metadata_;
  float left_;
  float top_;
  float right_;
  float bottom_;
  mutable int _cached_size_;
  friend struct  protobuf_messages_2eproto::TableStruct;
};
// -------------------------------------------------------------------

class Face : public ::google::protobuf::MessageLite /* @@protoc_insertion_point(class_definition:com.my.jni.dlib.data.Face) */ {
 public:
  Face();
  virtual ~Face();

  Face(const Face& from);

  inline Face& operator=(const Face& from) {
    CopyFrom(from);
    return *this;
  }

  static const Face& default_instance();

  static inline const Face* internal_default_instance() {
    return reinterpret_cast<const Face*>(
               &_Face_default_instance_);
  }

  void Swap(Face* other);

  // implements Message ----------------------------------------------

  inline Face* New() const PROTOBUF_FINAL { return New(NULL); }

  Face* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from)
    PROTOBUF_FINAL;
  void CopyFrom(const Face& from);
  void MergeFrom(const Face& from);
  void Clear() PROTOBUF_FINAL;
  bool IsInitialized() const PROTOBUF_FINAL;

  size_t ByteSizeLong() const PROTOBUF_FINAL;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) PROTOBUF_FINAL;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const PROTOBUF_FINAL;
  void DiscardUnknownFields();
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(Face* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::std::string GetTypeName() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // repeated .com.my.jni.dlib.data.Landmark landmarks = 2;
  int landmarks_size() const;
  void clear_landmarks();
  static const int kLandmarksFieldNumber = 2;
  const ::com::my::jni::dlib::data::Landmark& landmarks(int index) const;
  ::com::my::jni::dlib::data::Landmark* mutable_landmarks(int index);
  ::com::my::jni::dlib::data::Landmark* add_landmarks();
  ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >*
      mutable_landmarks();
  const ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >&
      landmarks() const;

  // .com.my.jni.dlib.data.Rectangle bound = 1;
  bool has_bound() const;
  void clear_bound();
  static const int kBoundFieldNumber = 1;
  const ::com::my::jni::dlib::data::Rectangle& bound() const;
  ::com::my::jni::dlib::data::Rectangle* mutable_bound();
  ::com::my::jni::dlib::data::Rectangle* release_bound();
  void set_allocated_bound(::com::my::jni::dlib::data::Rectangle* bound);

  // @@protoc_insertion_point(class_scope:com.my.jni.dlib.data.Face)
 private:

  ::google::protobuf::internal::InternalMetadataWithArenaLite _internal_metadata_;
  ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark > landmarks_;
  ::com::my::jni::dlib::data::Rectangle* bound_;
  mutable int _cached_size_;
  friend struct  protobuf_messages_2eproto::TableStruct;
};
// -------------------------------------------------------------------

class FaceList : public ::google::protobuf::MessageLite /* @@protoc_insertion_point(class_definition:com.my.jni.dlib.data.FaceList) */ {
 public:
  FaceList();
  virtual ~FaceList();

  FaceList(const FaceList& from);

  inline FaceList& operator=(const FaceList& from) {
    CopyFrom(from);
    return *this;
  }

  static const FaceList& default_instance();

  static inline const FaceList* internal_default_instance() {
    return reinterpret_cast<const FaceList*>(
               &_FaceList_default_instance_);
  }

  void Swap(FaceList* other);

  // implements Message ----------------------------------------------

  inline FaceList* New() const PROTOBUF_FINAL { return New(NULL); }

  FaceList* New(::google::protobuf::Arena* arena) const PROTOBUF_FINAL;
  void CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from)
    PROTOBUF_FINAL;
  void CopyFrom(const FaceList& from);
  void MergeFrom(const FaceList& from);
  void Clear() PROTOBUF_FINAL;
  bool IsInitialized() const PROTOBUF_FINAL;

  size_t ByteSizeLong() const PROTOBUF_FINAL;
  bool MergePartialFromCodedStream(
      ::google::protobuf::io::CodedInputStream* input) PROTOBUF_FINAL;
  void SerializeWithCachedSizes(
      ::google::protobuf::io::CodedOutputStream* output) const PROTOBUF_FINAL;
  void DiscardUnknownFields();
  int GetCachedSize() const PROTOBUF_FINAL { return _cached_size_; }
  private:
  void SharedCtor();
  void SharedDtor();
  void SetCachedSize(int size) const;
  void InternalSwap(FaceList* other);
  private:
  inline ::google::protobuf::Arena* GetArenaNoVirtual() const {
    return NULL;
  }
  inline void* MaybeArenaPtr() const {
    return NULL;
  }
  public:

  ::std::string GetTypeName() const PROTOBUF_FINAL;

  // nested types ----------------------------------------------------

  // accessors -------------------------------------------------------

  // repeated .com.my.jni.dlib.data.Face faces = 1;
  int faces_size() const;
  void clear_faces();
  static const int kFacesFieldNumber = 1;
  const ::com::my::jni::dlib::data::Face& faces(int index) const;
  ::com::my::jni::dlib::data::Face* mutable_faces(int index);
  ::com::my::jni::dlib::data::Face* add_faces();
  ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Face >*
      mutable_faces();
  const ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Face >&
      faces() const;

  // @@protoc_insertion_point(class_scope:com.my.jni.dlib.data.FaceList)
 private:

  ::google::protobuf::internal::InternalMetadataWithArenaLite _internal_metadata_;
  ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Face > faces_;
  mutable int _cached_size_;
  friend struct  protobuf_messages_2eproto::TableStruct;
};
// ===================================================================


// ===================================================================

#if !PROTOBUF_INLINE_NOT_IN_HEADERS
// Landmark

// float x = 1;
inline void Landmark::clear_x() {
  x_ = 0;
}
inline float Landmark::x() const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Landmark.x)
  return x_;
}
inline void Landmark::set_x(float value) {
  
  x_ = value;
  // @@protoc_insertion_point(field_set:com.my.jni.dlib.data.Landmark.x)
}

// float y = 2;
inline void Landmark::clear_y() {
  y_ = 0;
}
inline float Landmark::y() const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Landmark.y)
  return y_;
}
inline void Landmark::set_y(float value) {
  
  y_ = value;
  // @@protoc_insertion_point(field_set:com.my.jni.dlib.data.Landmark.y)
}

// -------------------------------------------------------------------

// LandmarkList

// repeated .com.my.jni.dlib.data.Landmark landmarks = 1;
inline int LandmarkList::landmarks_size() const {
  return landmarks_.size();
}
inline void LandmarkList::clear_landmarks() {
  landmarks_.Clear();
}
inline const ::com::my::jni::dlib::data::Landmark& LandmarkList::landmarks(int index) const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.LandmarkList.landmarks)
  return landmarks_.Get(index);
}
inline ::com::my::jni::dlib::data::Landmark* LandmarkList::mutable_landmarks(int index) {
  // @@protoc_insertion_point(field_mutable:com.my.jni.dlib.data.LandmarkList.landmarks)
  return landmarks_.Mutable(index);
}
inline ::com::my::jni::dlib::data::Landmark* LandmarkList::add_landmarks() {
  // @@protoc_insertion_point(field_add:com.my.jni.dlib.data.LandmarkList.landmarks)
  return landmarks_.Add();
}
inline ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >*
LandmarkList::mutable_landmarks() {
  // @@protoc_insertion_point(field_mutable_list:com.my.jni.dlib.data.LandmarkList.landmarks)
  return &landmarks_;
}
inline const ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >&
LandmarkList::landmarks() const {
  // @@protoc_insertion_point(field_list:com.my.jni.dlib.data.LandmarkList.landmarks)
  return landmarks_;
}

// -------------------------------------------------------------------

// Rectangle

// float left = 1;
inline void Rectangle::clear_left() {
  left_ = 0;
}
inline float Rectangle::left() const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Rectangle.left)
  return left_;
}
inline void Rectangle::set_left(float value) {
  
  left_ = value;
  // @@protoc_insertion_point(field_set:com.my.jni.dlib.data.Rectangle.left)
}

// float top = 2;
inline void Rectangle::clear_top() {
  top_ = 0;
}
inline float Rectangle::top() const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Rectangle.top)
  return top_;
}
inline void Rectangle::set_top(float value) {
  
  top_ = value;
  // @@protoc_insertion_point(field_set:com.my.jni.dlib.data.Rectangle.top)
}

// float right = 3;
inline void Rectangle::clear_right() {
  right_ = 0;
}
inline float Rectangle::right() const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Rectangle.right)
  return right_;
}
inline void Rectangle::set_right(float value) {
  
  right_ = value;
  // @@protoc_insertion_point(field_set:com.my.jni.dlib.data.Rectangle.right)
}

// float bottom = 4;
inline void Rectangle::clear_bottom() {
  bottom_ = 0;
}
inline float Rectangle::bottom() const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Rectangle.bottom)
  return bottom_;
}
inline void Rectangle::set_bottom(float value) {
  
  bottom_ = value;
  // @@protoc_insertion_point(field_set:com.my.jni.dlib.data.Rectangle.bottom)
}

// -------------------------------------------------------------------

// Face

// .com.my.jni.dlib.data.Rectangle bound = 1;
inline bool Face::has_bound() const {
  return this != internal_default_instance() && bound_ != NULL;
}
inline void Face::clear_bound() {
  if (GetArenaNoVirtual() == NULL && bound_ != NULL) delete bound_;
  bound_ = NULL;
}
inline const ::com::my::jni::dlib::data::Rectangle& Face::bound() const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Face.bound)
  return bound_ != NULL ? *bound_
                         : *::com::my::jni::dlib::data::Rectangle::internal_default_instance();
}
inline ::com::my::jni::dlib::data::Rectangle* Face::mutable_bound() {
  
  if (bound_ == NULL) {
    bound_ = new ::com::my::jni::dlib::data::Rectangle;
  }
  // @@protoc_insertion_point(field_mutable:com.my.jni.dlib.data.Face.bound)
  return bound_;
}
inline ::com::my::jni::dlib::data::Rectangle* Face::release_bound() {
  // @@protoc_insertion_point(field_release:com.my.jni.dlib.data.Face.bound)
  
  ::com::my::jni::dlib::data::Rectangle* temp = bound_;
  bound_ = NULL;
  return temp;
}
inline void Face::set_allocated_bound(::com::my::jni::dlib::data::Rectangle* bound) {
  delete bound_;
  bound_ = bound;
  if (bound) {
    
  } else {
    
  }
  // @@protoc_insertion_point(field_set_allocated:com.my.jni.dlib.data.Face.bound)
}

// repeated .com.my.jni.dlib.data.Landmark landmarks = 2;
inline int Face::landmarks_size() const {
  return landmarks_.size();
}
inline void Face::clear_landmarks() {
  landmarks_.Clear();
}
inline const ::com::my::jni::dlib::data::Landmark& Face::landmarks(int index) const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.Face.landmarks)
  return landmarks_.Get(index);
}
inline ::com::my::jni::dlib::data::Landmark* Face::mutable_landmarks(int index) {
  // @@protoc_insertion_point(field_mutable:com.my.jni.dlib.data.Face.landmarks)
  return landmarks_.Mutable(index);
}
inline ::com::my::jni::dlib::data::Landmark* Face::add_landmarks() {
  // @@protoc_insertion_point(field_add:com.my.jni.dlib.data.Face.landmarks)
  return landmarks_.Add();
}
inline ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >*
Face::mutable_landmarks() {
  // @@protoc_insertion_point(field_mutable_list:com.my.jni.dlib.data.Face.landmarks)
  return &landmarks_;
}
inline const ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Landmark >&
Face::landmarks() const {
  // @@protoc_insertion_point(field_list:com.my.jni.dlib.data.Face.landmarks)
  return landmarks_;
}

// -------------------------------------------------------------------

// FaceList

// repeated .com.my.jni.dlib.data.Face faces = 1;
inline int FaceList::faces_size() const {
  return faces_.size();
}
inline void FaceList::clear_faces() {
  faces_.Clear();
}
inline const ::com::my::jni::dlib::data::Face& FaceList::faces(int index) const {
  // @@protoc_insertion_point(field_get:com.my.jni.dlib.data.FaceList.faces)
  return faces_.Get(index);
}
inline ::com::my::jni::dlib::data::Face* FaceList::mutable_faces(int index) {
  // @@protoc_insertion_point(field_mutable:com.my.jni.dlib.data.FaceList.faces)
  return faces_.Mutable(index);
}
inline ::com::my::jni::dlib::data::Face* FaceList::add_faces() {
  // @@protoc_insertion_point(field_add:com.my.jni.dlib.data.FaceList.faces)
  return faces_.Add();
}
inline ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Face >*
FaceList::mutable_faces() {
  // @@protoc_insertion_point(field_mutable_list:com.my.jni.dlib.data.FaceList.faces)
  return &faces_;
}
inline const ::google::protobuf::RepeatedPtrField< ::com::my::jni::dlib::data::Face >&
FaceList::faces() const {
  // @@protoc_insertion_point(field_list:com.my.jni.dlib.data.FaceList.faces)
  return faces_;
}

#endif  // !PROTOBUF_INLINE_NOT_IN_HEADERS
// -------------------------------------------------------------------

// -------------------------------------------------------------------

// -------------------------------------------------------------------

// -------------------------------------------------------------------


// @@protoc_insertion_point(namespace_scope)


}  // namespace data
}  // namespace dlib
}  // namespace jni
}  // namespace my
}  // namespace com

// @@protoc_insertion_point(global_scope)

#endif  // PROTOBUF_messages_2eproto__INCLUDED
