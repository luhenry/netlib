FROM mcr.microsoft.com/devcontainers/base:ubuntu-24.04

# Avoid prompts from apt
ENV DEBIAN_FRONTEND=noninteractive

# Install cross-compilers and scientific libraries
RUN apt-get update \
 && apt-get install -y --no-install-recommends \
    build-essential \
    gcc-aarch64-linux-gnu libc6-dev-arm64-cross \
    gcc-riscv64-linux-gnu libc6-dev-riscv64-cross \
    gcc-x86-64-linux-gnu libc6-dev-amd64-cross \
    libopenblas-dev \
    liblapack-dev \
    libarpack2-dev \
 && rm -rf /var/lib/apt/lists/*

# Install multiple Java versions from Adoptium (Eclipse Temurin)
RUN bash -eux -o pipefail <<EOF
    ARCH=\$(dpkg --print-architecture)
    if [ "\$ARCH" = "amd64" ]; then
        ADOPTIUM_ARCH=x64;
    elif [ "\$ARCH" = "arm64" ]; then
        ADOPTIUM_ARCH=aarch64;
    else
        echo "Unsupported architecture: \$ARCH" && exit 1;
    fi
    for VERSION in 8 11 17 21 25; do
        mkdir -p /usr/lib/jvm/temurin-\${VERSION}-jdk-\${ARCH};
        wget -O - "https://api.adoptium.net/v3/binary/latest/\${VERSION}/ga/linux/\${ADOPTIUM_ARCH}/jdk/hotspot/normal/eclipse?project=jdk" --progress=dot:giga \
            | tar -xzf - --strip-components=1 -C /usr/lib/jvm/temurin-\${VERSION}-jdk-\${ARCH};
    done
EOF

# Set Java 25 as default
RUN ARCH=$(dpkg --print-architecture) \
 && ln -sf /usr/lib/jvm/temurin-25-jdk-${ARCH} /usr/lib/jvm/default-java
ENV JAVA_HOME=/usr/lib/jvm/default-java
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Install Maven
ARG MAVEN_VERSION=3.9.6
RUN mkdir -p /opt/apache-maven-${MAVEN_VERSION} \
 && wget -O - https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz --progress=dot:giga \
        | tar -xzf - --strip-components=1 -C /opt/apache-maven-${MAVEN_VERSION}

ENV MAVEN_HOME=/opt/apache-maven-${MAVEN_VERSION}
ENV PATH="${MAVEN_HOME}/bin:${PATH}"
