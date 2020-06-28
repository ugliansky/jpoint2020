cd native
./build.sh
cd ..
javac JavaToNative.java

java -Djava.library.path=./native JavaToNative


