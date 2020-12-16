# Build

## Requirements

- JDK 16+. It rely on the Vector API which has been integrated in JDK 16

## Commands

```
$> JAVA_HOME=/usr/lib/jvm/jdk mvn clean package
```

# Run

You've a set of benchmarks in [benchmarks/src/main/java](https://github.com/luhenry/blas/tree/master/benchmarks/src/main/java/). You can run them with:

```
$> /usr/lib/jvm/jdk/bin/java --add-modules jdk.incubator.vector -jar benchmarks/target/blas-benchmarks.jar
```

# Contribution

I welcome the addition of any BLAS operation as long as it comes with corresponding tests and benchmarks.
