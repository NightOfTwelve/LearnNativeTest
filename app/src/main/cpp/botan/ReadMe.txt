

Windows 上编译:

[编程与调试 C++ -- Botan for Android: Crypto and TLS for Modern C++](https://blog.hawkhai.com/blog/2021/02/24/botan-for-Android)
[Building The Library - Botan](https://botan.randombit.net/handbook/building.html)


```
$ export CXX=/opt/android-ndk/toolchains/llvm/prebuilt/linux-x86_64/bin/aarch64-linux-android28-clang++
$ ./configure.py --os=android --cc=clang --cpu=arm64
```

```
set path=D:\Android\Sdk\ndk\22.0.7026061\toolchains\llvm\prebuilt\windows-x86_64\bin;%path% # 直接找到 clang++
python configure.py --os=android --cc=clang --cpu=x86_64 \
    --minimized-build --amalgamation --disable-shared-library \
    --enable-modules=aes,sha2_32,md5,cbc,filters,zlib --without-os-feature=getauxval
```

```
set CXX=D:\Android\Sdk\ndk\22.0.7026061\toolchains\llvm\prebuilt\windows-x86_64\bin\aarch64-linux-android28-clang++.cmd
python configure.py --os=android --cc=clang --cpu=arm64 \
    --minimized-build --amalgamation --disable-shared-library \
    --enable-modules=aes,sha2_32,md5,cbc,filters,zlib --without-os-feature=getauxval
```

```
set CXX=D:\Android\Sdk\ndk\22.0.7026061\toolchains\llvm\prebuilt\windows-x86_64\bin\armv7a-linux-androideabi28-clang++.cmd
python configure.py --os=android --cc=clang --cpu=arm \
    --minimized-build --amalgamation --disable-shared-library \
    --enable-modules=aes,sha2_32,md5,cbc,filters,zlib --without-os-feature=getauxval
```

```
set CXX=D:\Android\Sdk\ndk\22.0.7026061\toolchains\llvm\prebuilt\windows-x86_64\bin\i686-linux-android28-clang++.cmd
python configure.py --os=android --cc=clang --cpu=x86 \
    --minimized-build --amalgamation --disable-shared-library \
    --enable-modules=aes,sha2_32,md5,cbc,filters,zlib --without-os-feature=getauxval
```

```
set CXX=D:\Android\Sdk\ndk\22.0.7026061\toolchains\llvm\prebuilt\windows-x86_64\bin\x86_64-linux-android28-clang++.cmd
python configure.py --os=android --cc=clang --cpu=x86_64 \
    --minimized-build --amalgamation --disable-shared-library \
    --enable-modules=aes,sha2_32,md5,cbc,filters,zlib --without-os-feature=getauxval
```




```powershell

set path=D:\Android\Sdk\ndk\27.0.12077973\toolchains\llvm\prebuilt\windows-x86_64\bin;%path%

set CXX=D:\Android\Sdk\ndk\27.0.12077973\toolchains\llvm\prebuilt\windows-x86_64\bin\aarch64-linux-android28-clang++.cmd

```




```powershell

python configure.py --os=android --cc=clang --cpu=arm64 --amalgamation

```

编译botan for Android 版本 3.7.1

在windows上执行 python configure.py --os=android --cc=clang --cpu=arm64 --amalgamation 之后，

拷贝生成的 botan_all.h 和 botan_all.cpp 到 新建的目录jni中

拷贝build目录中的include目录到jni目录中

jni目录中新建 Android.mk 和 Application.mk 文件，

Android.mk

```powershell
LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := botan
LOCAL_EXPORT_C_INCLUDES := $(LOCAL_PATH)
LOCAL_C_INCLUDES := $(LOCAL_PATH)/include/external
LOCAL_SRC_FILES := botan_all.cpp
#LOCAL_CPPFLAGS := -DBOTAN_USE_GCC_INLINE_ASM=0
LOCAL_CPPFLAGS += -std=c++20 -frtti -fexceptions
#LOCAL_LDLIBS := -llog

include $(BUILD_SHARED_LIBRARY)
```

Application.mk

```powershell
#APP_ABI := armeabi
#APP_ABI := armeabi-v7a arm64-v8a
APP_ABI := arm64-v8a
#APP_ABI := armeabi-v7a
APP_CPPFLAGS += -fexceptions -frtti
#APP_STL := c++_shared
#APP_STL := stlport_shared
#APP_STL := gnustl_shared
APP_PLATFORM := android-21  # 指定最低API级别  Defaulting to minimum supported version android-21.
APP_STL := c++_shared  # 使用动态STL库
```

即可使用 ndk-build 编译出 arm64-v8a 的 so 文件


