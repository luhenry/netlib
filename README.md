# Build

## Requirements

- JDK 16+. It rely on the Vector API which has been integrated in JDK 16

## Commands

```
$> JAVA_HOME=/usr/lib/jvm/jdk-17+1 mvn clean package
```

# Run

You've a set of benchmarks in [benchmarks/src/main/java](https://github.com/luhenry/blas/tree/master/benchmarks/src/main/java/). You can run them with:

```
$> /usr/lib/jvm/jdk/bin/java -jar benchmarks/target/blas-benchmarks.jar -f 3 -wi 4 -i 2 -rff jmh-results.csv
```

# Contribution

I welcome the addition of any BLAS operation as long as it comes with corresponding tests and benchmarks.
