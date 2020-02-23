# 参考网址：
# * docker pool：http://www.docker.org.cn/article/114.html
# * docker官网：https://docs.docker.com/develop/develop-images/dockerfile_best-practices/
# * docker官网：https://docs.docker.com/engine/reference/builder/
#
# --------------------------- 分割线 ------------------------------
#
# DockerFile参考指令如下：
#
# FROM：指定运行镜像。例如：FROM image or FROM image:tag。
# MAINTAINER：维护者信息。
# WORKDIR：为后续的RUN、CMD、ENTRYPOINT指令配置工作目录。可以使用多个WORKDIR指令，后续命令如果参数是相对路径，则会基于之前命令指定的路径。
#   例如，WORKDIR /path/to/workedir，调用RUN pwd则会输出"/path/to/workdir"。
# VOLUME：创建一个可以从本地主机或其他容器挂载的挂载点。
# ENV：指定一个环节变量，会被后续RUN指令使用，并在容器运行时保留。例如，ENV VAR1 A // ENV VAR2 B ...
# ADD：ADD复制本地主机文件、目录或者远程文件 URLS 从 并且添加到容器指定路径中。
# COPY：COPY复制新文件或者目录从 并且添加到容器指定路径中 。用法同ADD，唯一的不同是不能指定远程文件 URLS。
# RUN：将在当前镜像基础上执行指定命令，并提交为新的镜像，后续的RUN都在之前RUN提交后的镜像为基础。
#   （注意：RUN只是在构建镜像的时候执行，后续镜像构建完成之后，启动容器就与RUN无关了）
# CMD：配置容器启动后默认执行的命令，若用户使用"docker run"填入了相对应的参数，则会覆盖CMD所配置的参数。
#   （注意：CMD会在启动容器的时候执行，build 时不执行）
# ENTRYPOINT：配置容器启动后执行的命令(ENTRYPOINT  "executable", "param1", "param2")，并且不可被 docker run 提供的参数覆盖，而CMD是可以被覆盖的。如果需要覆盖，则可以使用docker run --entrypoint选项。
#   （每个 Dockerfile 中只能有一个ENTRYPOINT，当指定多个时，只有最后一个生效。）
#   tips：通过ENTRYPOINT使用 exec form 方式设置稳定的默认命令和选项，而使用CMD添加默认之外经常被改动的选项。
# EXPOSE：告诉 Docker 服务端容器对外映射的本地端口，需要在 docker run 的时候使用-p或者-P选项生效。
#
# --------------------------- 分割线 ------------------------------
#
# 运行DockerFile：
#
# 编写好DockerFile文件后，可通过docker build命令创建镜像。
# 基本的格式为docker build[选项]路径，该命令将读取指定路径下（包括子目录）的Dockerfile，并将该路径下所有内容发送给Docker服务端，
# 由服务端来创建镜像。因此一般建议放置Dockerfile的目录为空目录。另外，可以通过.dockerignore文件（每一行添加一条匹配模式）来让Docker忽略路径下的目录和文件。
# 要指定镜像的标签信息，可以通过-t选项。例如，指定Dockerfile所在路径为/tmp/docker_builder/，并且希望生成镜像标签为build_repo/first_image，
# 可以使用下面的命令：$ sudo docker build -t build_repo/first_image /tmp/docker_builder/。
#

# 设置镜像源
FROM java:8-jre

# 设置参数. 注：若使用maven的配置作为参数，就必须通过maven来运行DockerFIle文件
ARG JAR_PATH
ARG JAR_NAME

COPY target/springboot-template-framework-dev-local-1.0.0.RELEASE.jar springboot-template-framework.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx256m", "-jar", "springboot-template-framework.jar"]

# 1.配置镜像来源
#FROM java:8-jre
#FROM openjdk:8u181-jdk-alpine
#
## 设置参数
#ARG JAR_PATH
#ARG JAR_NAME
#
## 2.执行镜像维护者的信息
#MAINTAINER chang3300589
#
## 设置jar包的复制目录(exec进来的默认目录)
#WORKDIR /data/jar
#
#RUN echo ${JAR_PATH}
#
## 3.设置挂载的数据卷
##VOLUME /data/jar
#
## 4.将编译好的jar包复制到，容器指定的目录之上
#COPY ${JAR_PATH} springboot_template_framework.jar
#
## 5.指定docker容器运行时的命令
#ENTRYPOINT ["java", "-jar", 'springboot_template_framework.jar', "-Xmx256m"]
#
#EXPOSE 8080

