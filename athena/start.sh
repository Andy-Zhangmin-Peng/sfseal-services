#!/bin/sh

nohup /usr/bin/java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -cp app:app/lib/* com.wanyun.sfseal.athena.Application
