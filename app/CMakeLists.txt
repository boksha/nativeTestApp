# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
include_directories(${CMAKE_SOURCE_DIR}\\..\\openCVLibrary310\\src\\main\\jniLibs\\include)
add_library( # Sets the name of the library.
             nativetest

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).

             src/main/cpp/NegativeFilter.cpp
             src/main/cpp/TvFilter.cpp
             src/main/cpp/SnowFilter.cpp
             src/main/cpp/OneColorFilter.cpp
             src/main/cpp/PixelateFilter.cpp
             src/main/cpp/GrayScaleFilter.cpp
             src/main/cpp/NoiseFilter.cpp
             src/main/cpp/color.cpp
             src/main/cpp/nativefacade.cpp )
# Add the directories where the Cpp header files are to let CMake find them during compile time
include_directories(src/main/cpp/)
# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
# [EDIT] Similar to above lines, add the OpenCV library
add_library( lib_opencv SHARED IMPORTED )

set_target_properties(lib_opencv PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}\\..\\openCVLibrary310\\src\\main\\jniLibs\\${ANDROID_ABI}\\libopencv_java3.so)

target_link_libraries( # Specifies the target library.
                       nativetest
                       #add openCV Miki
                        lib_opencv
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )