# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.177.0/containers/java/.devcontainer/base.Dockerfile

# [Choice] Java version: 11, 15
ARG VARIANT="15"
FROM mcr.microsoft.com/vscode/devcontainers/java:0-${VARIANT}

# [Option] Install Maven
ARG INSTALL_MAVEN="false"
ARG MAVEN_VERSION=""
# [Option] Install Gradle
ARG INSTALL_GRADLE="false"
ARG GRADLE_VERSION=""
RUN if [ "${INSTALL_MAVEN}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi \
    && if [ "${INSTALL_GRADLE}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi

# [Option] Install Node.js
ARG INSTALL_NODE="true"
ARG NODE_VERSION="lts/*"
RUN if [ "${INSTALL_NODE}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/share/nvm/nvm.sh && nvm install ${NODE_VERSION} 2>&1"; fi

COPY --from=openjdk:8-jdk-buster /usr/local/openjdk-8 /usr/local/openjdk-8
COPY --from=openjdk:11-jdk-buster /usr/local/openjdk-11 /usr/local/openjdk-11
COPY --from=openjdk:16-jdk-buster /usr/local/openjdk-16 /usr/local/openjdk-16
COPY --from=openjdk:17-jdk-buster /usr/local/openjdk-17 /usr/local/openjdk-17

ENV JAVA_HOME="/usr/local/openjdk-16"
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# [Optional] Uncomment this section to install additional OS packages.
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
    && apt-get -y install \
        libopenblas-dev libarpack2-dev gdb vim build-essential gcc-x86-64-linux-gnu gcc-aarch64-linux-gnu

# [Optional] Uncomment this line to install global node packages.
# RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1
