This project provides multiple Java implementations of BLAS, LAPACK, and ARPACK subroutines, supporting Java 8+. It provides hardware acceleration of BLAS, LAPACK, and ARPACK with native implementations like [OpenBLAS](https://github.com/xianyi/OpenBLAS) and [Intel MKL](https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/onemkl.html).

# Usage

## Run-time dependencies

- JDK 8+
- _[optional]_ A native library implementing BLAS, LAPACK, or ARPACK installed on the machine
  - ex: [OpenBLAS](https://github.com/xianyi/OpenBLAS) and [Intel MKL](https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/onemkl.html)

## Native implementations wrappers

`dev.ludovic.netlib` relies on native libraries to provide hardware acceleration, and invokes them through JNI with [JNIBLAS](https://github.com/luhenry/netlib/blob/master/blas/src/main/java/dev/ludovic/netlib/blas/JNIBLAS.java), [JNILAPACK](https://github.com/luhenry/netlib/blob/master/lapack/src/main/java/dev/ludovic/netlib/lapack/JNILAPACK.java), and [JNIARPACK](https://github.com/luhenry/netlib/blob/master/arpack/src/main/java/dev/ludovic/netlib/arpack/JNIARPACK.java) for BLAS, LAPACK, and ARPACK respectively.

These [JNIBLAS](https://github.com/luhenry/netlib/blob/master/blas/src/main/java/dev/ludovic/netlib/blas/JNIBLAS.java), [JNILAPACK](https://github.com/luhenry/netlib/blob/master/lapack/src/main/java/dev/ludovic/netlib/lapack/JNILAPACK.java), and [JNIARPACK](https://github.com/luhenry/netlib/blob/master/arpack/src/main/java/dev/ludovic/netlib/arpack/JNIARPACK.java) classes distribute and automatically unpack the native JNI wrappers ([blas/jni.c](https://github.com/luhenry/netlib/blob/master/blas/src/main/native/jni.c), [lapack/jni.c](https://github.com/luhenry/netlib/blob/master/lapack/src/main/native/jni.c), and [arpack/jni.c](https://github.com/luhenry/netlib/blob/master/arpack/src/main/native/jni.c)) when needed.

It supports all versions of Java 8+.

### Native libraries installation

The native libraries must be installed on the machine; `dev.ludovic.netlib` doesn't ship any native implementation.

For BLAS and LAPACK, you can install OpenBLAS. For example on Ubuntu:
```
sudo apt-get install libopenblas-base
```

For ARPACK, you can install the Fortran77 reference implementation. For example on Ubuntu:
```
sudo apt-get install libarpack2
```

To install Intel MKL, follow the instructions at https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/onemkl.html

### Overriding the native implementations

The native JNI wrappers dynamically load the native libraries ([OpenBLAS](https://github.com/xianyi/OpenBLAS) or [Intel MKL](https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/onemkl.html) for example). You can override which library is dynamically loaded through two system properties, checked in order:
1. `nativeLibPath`: the full path to the library
2. `nativeLib`: the filename of the library; it should be found on the dynamic loader search path (see the search order in [`man 8 ld.so`](https://linux.die.net/man/8/ld.so))

For BLAS, LAPACK, and ARPACK, the system properties are the following:
|   | `nativeLib` | `nativeLibPath` |
| - | ----------- | --------------- |
| BLAS | `-Ddev.ludovic.netlib.blas.nativeLib` set to `liblas.so.3` by default | `-Ddev.ludovic.netlib.blas.nativeLibPath` unset by default |
| LAPACK | `-Ddev.ludovic.netlib.lapack.nativeLib` set to `liblapack.so.3` by default | `-Ddev.ludovic.netlib.lapack.nativeLibPath` unset by default |
| ARPACK | `-Ddev.ludovic.netlib.arpack.nativeLib` set to `libarpack.so.2` by default | `-Ddev.ludovic.netlib.arpack.nativeLibPath` unset by default |

Here are some examples of overriding the loaded native library:
- `-Ddev.ludovic.netlib.blas.nativeLibPath=/usr/lib/x86_64-linux-gnu/libopenblas.so` for [OpenBLAS](https://github.com/xianyi/OpenBLAS)
- `-Ddev.ludovic.netlib.blas.nativeLib=intel_mkl.so` for [Intel MKL](https://software.intel.com/content/www/us/en/develop/tools/oneapi/components/onemkl.html)

### GPU acceleration

As you can override the native library which is dynamically loaded, you can also load [NVBLAS](https://docs.nvidia.com/cuda/nvblas/index.html). This native library provides CUDA-based GPU acceleration for some subroutines and automatically falls back to a more generic, CPU-only implementation for other subroutines. You can find the full documentation on how to use it at [https://docs.nvidia.com/cuda/nvblas/index.html](https://docs.nvidia.com/cuda/nvblas/index.html).

To dynamically load it, you can set either of the above system properties:
- `-Ddev.ludovic.netlib.blas.nativeLibPath=/path/to/libnvblas.so`
- `-Ddev.ludovic.netlib.blas.nativeLib=libnvblas.so`

## Vector-based acceleration for Java 16+

Java 16 introduced the [Vector API](https://openjdk.java.net/jeps/338), a Java-based implementation providing access to hardware acceleration. [VectorBLAS](https://github.com/luhenry/netlib/blob/master/blas/src/main/java/dev/ludovic/netlib/blas/VectorBLAS.java) takes advantage of this API to implement most of the BLAS API.

The performance is on-par or above the native libraries on most [Level-1](http://www.netlib.org/blas/#_level_1) and [Level-2](http://www.netlib.org/blas/#_level_2) BLAS subroutines. For [Level-3](http://www.netlib.org/blas/#_level_3) BLAS subroutines, the performance still doesn't match native libraries ([some thought on why](https://mail.openjdk.java.net/pipermail/panama-dev/2021-January/011930.html)).

## Pure Java fallback for Java 8+

If neither a native implementation nor the Vector API are available, it falls back to a pure Java implementation. For most subroutines, it uses [F2j](https://icl.utk.edu/f2j/overview/index.html), available in [`net.sourceforge.f2j:arpack_combined_all:0.1`](https://mvnrepository.com/artifact/net.sourceforge.f2j/arpack_combined_all/0.1).

For some BLAS subroutines, [Java8BLAS](https://github.com/luhenry/netlib/blob/master/blas/src/main/java/dev/ludovic/netlib/blas/Java8BLAS.java) and [Java11BLAS](https://github.com/luhenry/netlib/blob/master/blas/src/main/java/dev/ludovic/netlib/blas/Java11BLAS.java) provide optimized implementations using primitives available in Java 8 and Java 11 respectively.

# Build

## Dependencies

- JDK 16+ for the Vector API and Foreign Linker API which have been integrated in JDK 16
- [`net.sourceforge.f2j:arpack_combined_all:0.1`](https://mvnrepository.com/artifact/net.sourceforge.f2j/arpack_combined_all/0.1) for [F2j](https://icl.utk.edu/f2j/overview/index.html) fallback

## Commands

```
$> mvn clean package
```

# Benchmarks

A set of benchmarks is available in [benchmarks/src/main/java/dev/ludovic/netlib/benchmarks/](https://github.com/luhenry/netlib/tree/master/benchmarks/src/main/java/dev/ludovic/netlib/benchmarks/). Run them with:

```
$> java -jar benchmarks/target/netlib-benchmarks.jar
```

# Release

Update the version in the `**/pom.xml`, create a tag, and push it:

```
$> export VERSION=3.0.3
$> git checkout --detach HEAD
$> sed -i -E "s/<version>[0-9]+\-SNAPSHOT<\/version>/<version>$VERSION<\/version>/g" **/pom.xml
$> git commit -p -m "v$VERSION" **/pom.xml
$> git tag v$VERSION
$> git push origin v$VERSION
```

That will trigger the upload of the package to Sonatype via Github Actions.

Then, go to https://oss.sonatype.org/, go to "Staging Repositories", verify the content of the package, "Close" the package, and finally click "Release" to deploy it to Maven Central.

Finally, go to https://github.com/luhenry/netlib/releases to update and publish the release on the Github repository.

# Thanks

This project has been largely inspired by [`netlib-java`](https://github.com/fommil/netlib-java) and [@fommil](https://github.com/fommil)'s hard work.

# Contribution

I welcome the addition of any BLAS operation as long as it comes with corresponding tests and benchmarks.
