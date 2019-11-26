cmake_minimum_required(VERSION 3.4.1)


add_library( # 自定义lib的名字.
        local-native-lib

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/local-crash.cpp)


find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

target_link_libraries( # Specifies the target library.
        #跟上边的保持一致
        local-native-lib

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})