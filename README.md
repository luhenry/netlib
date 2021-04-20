# Build

## Requirements

- JDK 16+. It rely on the Vector API and Foreign Linker API which have been integrated in JDK 16

## Commands

```
$> mvn clean package
```

# Run benchmarks

You've a set of benchmarks in [benchmarks/src/main/java/dev/ludovic/netlib/benchmarks/](https://github.com/luhenry/netlib/tree/master/benchmarks/src/main/java/dev/ludovic/netlib/benchmarks/). You can run them with:

```
$> java -jar benchmarks/target/netlib-benchmarks.jar
```

# Contribution

I welcome the addition of any BLAS operation as long as it comes with corresponding tests and benchmarks.
