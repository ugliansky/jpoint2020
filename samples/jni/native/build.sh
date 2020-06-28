export JDK_DIR=/home/user/tools-area/java/jdk-amd64/jdk1.8.0_242

gcc -O1 -Wall -fPIC -I$JDK_DIR/include -I$JDK_DIR/include/linux -I. -o libNativeLib.so -shared JavaToNative.c