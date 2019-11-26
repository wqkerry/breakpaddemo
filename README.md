**学习使用breakpad捕获native异常**  
#### 1.首先在app的gradle.build文件中关联依赖breakpad库  
    implementation project(":breakpad-build")  
#### 2.在android{}中配置cmake文件   
  //重要步骤  
  ```
  externalNativeBuild {  
        cmake {   
            path "CMakeLists.txt"    
        }    
    }     
   ```
#### 3.配置CMakeLists文件  
 ```
 cmake_minimum_required(VERSION 3.4.1)  
 add_library( # 自定义lib的名字.可以自己命名  
        local-native-lib  
        # Sets the library as a shared library.  
        SHARED  
        #Provides a relative path to your source file(s).  
        #可能出问题的文件，有个疑问，可以是个目录么？？，当用breakpad捕获异常的时候，这里怎么填写呢？  
        src/main/cpp/local-crash.cpp)  
 find_library( # Sets the name of the path variable.  
        log-lib  
        # Specifies the name of the NDK library that  
        #you want CMake to locate.  
        log)  
 target_link_libraries( # Specifies the target library.  
        #跟上边自定义的保持一致  
        local-native-lib  
        #Links the target library to the log library  
        #included in the NDK.  
        ${log-lib})  
  ```
#### 4.一般在application中初始化加载breakpad类库，demo中是在activity中  
 ```
 static {  
        System.loadLibrary("local-native-lib");  
    }  
  ```
#### 5.初始化breakpad  
 `BreakpadInit.initBreakpad(“dump文件存放路径”);`  
#### 6.分析dmp文件  
 利用minidump-stackwalk来解析dump文件，此处以windows环境下为例，命令是  
 `minidump-stackwalk.exe  xxx.dmp   >  log.txt`  
 在log文件中有异常相关信息
#### 7.利用ndk的工具找出报错代码  

 
 
