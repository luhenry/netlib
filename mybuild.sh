#!/usr/bin/env bash
# Replicates the CI "build-windows-natives" job locally.
# Run from the repo root in an MSYS2 UCRT64 shell.
set -euo pipefail

PATH="/ucrt64/bin:$PATH"

# Step 1: compile Java + generate JNI header into blas/target/include/
echo "=== Generating JNI header ==="
mvn --batch-mode --no-transfer-progress -pl blas -am compile -Dmaven.antrun.skip=true

# Step 2: build the DLL (mirrors CI "Build Windows DLL" step)
echo "=== Building Windows DLL ==="
JAVA_INC="$(cygpath -u "$JAVA_HOME")/include"
mkdir -p blas/target/native/windows-amd64 blas/target/objs/windows-amd64

gcc -c -Werror -Wall -Wextra -Wno-unused-label -O2 \
  -I blas/src/main/native \
  -I blas/target/include \
  -I "$JAVA_INC" -I "$JAVA_INC/win32" \
  -o blas/target/objs/windows-amd64/jni.o \
  blas/src/main/native/jni.c

gcc -shared -Wl,-Bstatic,-ldl,-Bdynamic \
  -o blas/target/native/windows-amd64/libnetlibblasjni.dll \
  blas/target/objs/windows-amd64/jni.o

echo ""
echo "Done: blas/target/native/windows-amd64/libnetlibblasjni.dll"
echo ""
echo "DLL imports:"
objdump -p blas/target/native/windows-amd64/libnetlibblasjni.dll | grep "DLL Name"
