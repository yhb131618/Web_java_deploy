FROM eclipse-temurin
#SSH 서버 설치
RUN apt-get update
RUN apt-get install -y openssh-server
RUN apt-get install -y git
RUN apt-get clean
RUN mkdir /var/run/sshd
#root 암호 설정
RUN echo 'root:1234' | chpasswd
#sshd_config 설정 변경
RUN sed -ri 's/^#?PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config
#작업 공간 지정
WORKDIR /work
#SSH 포트 노출
EXPOSE 22
#SSH 서비스 실행
CMD ["/usr/sbin/sshd", "-D"]
