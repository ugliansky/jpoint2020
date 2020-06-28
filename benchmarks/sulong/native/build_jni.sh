export JDK_DIR=<path to GraalVM>
clang -O2 -Wall -fPIC -I$JDK_DIR/include -I$JDK_DIR/include/linux -I. -o libJNINatives.so -shared org_sample_NativeBenchmark.c