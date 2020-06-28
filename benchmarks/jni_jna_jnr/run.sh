cd native
./build_jni.sh
./build_native.sh
cd ..

mvn clean install

export LD_LIBRARY_PATH=$(pwd)/native
java -jar target/benchmarks.jar
