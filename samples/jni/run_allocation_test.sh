cd native
./build.sh
cd ..
javac AllocationTest.java

java -Djava.library.path=./native -Xmx1G AllocationTest


