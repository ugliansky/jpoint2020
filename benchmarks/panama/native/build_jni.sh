export JDK_DIR=/home/user/tools-area/java/jdk-amd64/jdk1.8.0_242

gcc -O1 -Wall -fPIC -I$JDK_DIR/include -I$JDK_DIR/include/linux -I. -o libJNINatives.so -shared org_sample_NativeBenchmark.c