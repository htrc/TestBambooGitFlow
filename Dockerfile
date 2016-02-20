FROM anapsix/alpine-java:8

MAINTAINER Boris Capitanu <borice@hotmail.com>

COPY target/universal/stage/ /root/app/
WORKDIR /root/app/

EXPOSE 8888

CMD [ "./bin/testbamboogitflow" ]
