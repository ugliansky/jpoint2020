export JDK_DIR=<path to target JDK>

gcc -O1 -Wall -fPIC -I$JDK_DIR/include -I$JDK_DIR/include/linux -I. -o libJNINatives.so -shared org_sample_NativeBenchmark.c