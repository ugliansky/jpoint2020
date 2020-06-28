cd native
./build_jni.sh
./build_bc.sh
cp *.bc ..
cd ..

export PATH=<path to GraalVM>:$PATH
mvn clean install

export LD_LIBRARY_PATH=$(pwd)/native
java -jar target/benchmarks.jar
