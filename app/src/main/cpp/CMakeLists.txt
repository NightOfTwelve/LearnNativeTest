# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

# Declares the project name. The project name can be accessed via ${ PROJECT_NAME},
# Since this is the top level CMakeLists.txt, the project name is also accessible
# with ${CMAKE_PROJECT_NAME} (both CMake variables are in-sync within the top level
# build script scope).
project("nativetest")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#
# In this top level CMakeLists.txt, ${CMAKE_PROJECT_NAME} is used to define
# the target library name; in the sub-module's CMakeLists.txt, ${PROJECT_NAME}
# is preferred for the same purpose.
#
# In order to load a library into your app from Java/Kotlin, you must call
# System.loadLibrary() and pass the name of the library defined here;
# for GameActivity/NativeActivity derived applications, the same library name must be
# used in the AndroidManifest.xml file.
add_library(${CMAKE_PROJECT_NAME} SHARED
        # List C/C++ source files with relative paths to this CMakeLists.txt.
        native-lib.cpp)


include_directories(botan)
include_directories(botan/include)
#include_directories(botan/include/botan)
#include_directories(botan/include/botan/internal)
include_directories(botan/include/external)
add_subdirectory(botan)

## 添加第三方静态库
## 将第三方静态库文件（.a 或 .lib）放置于 app/src/main/cpp/libs/[架构目录] 中（如 arm64-v8a、armeabi-v7a 等）
## 静态库头文件建议放置于 app/src/main/cpp/include 目录。
## 添加头文件目录
#include_directories(${CMAKE_SOURCE_DIR}/include)
## 导入第三方静态库
#add_library(third_party_lib STATIC IMPORTED)
#set_target_properties(third_party_lib PROPERTIES
#        IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI}/libmylib.a
#)
## 链接到目标库
#target_link_libraries(native-lib third_party_lib)






##
## 添加子目录（包含第三方库）
#add_subdirectory(botan)
## 包含第三方头文件
#target_include_directories(${CMAKE_PROJECT_NAME} PRIVATE
#        ${CMAKE_CURRENT_SOURCE_DIR}/botan
#)


# Specifies libraries CMake should link to your target library. You
# can link libraries from various origins, such as libraries defined in this
# build script, prebuilt third-party libraries, or Android system libraries.
target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        android
        log
        botan)