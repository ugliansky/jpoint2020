export PATH=<path to GraalVM>:$PATH
export LLVM_TOOLCHAIN=$(lli --print-toolchain-path)

$LLVM_TOOLCHAIN/clang -O2 -c native.c -o native.bc

