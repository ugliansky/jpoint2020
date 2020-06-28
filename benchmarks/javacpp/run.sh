export PATH=<path to JDK8>/bin:$PATH
mvn clean install

export PATH=<path to JDK14>/bin:$PATH
java -jar target/benchmarks.jar