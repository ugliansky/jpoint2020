cd native
./build_jni.sh
./build_native.sh

export PATH=<path to jextract tool>/bin:$PATH
jextract -lpanamatest -t org.sample panamatest.h

jar cf panamatest.jar org/sample/*.class
mvn install:install-file -Dfile=panamatest.jar -DgroupId=org.sample -DartifactId=panamatest -Dpackaging=jar -Dversion=1.0

cd ..

export PATH=<path to Panama build>:$PATH
export LD_LIBRARY_PATH=$(pwd)/native

mvn clean install
java --add-modules=jdk.incubator.foreign -jar target/benchmarks.jar


