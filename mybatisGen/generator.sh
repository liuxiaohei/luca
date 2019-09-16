#!/bin/sh

set -e

basepath=$(cd `dirname $0`; pwd)
cd ${basepath}


rm -fr target
mkdir target
java -jar mybatis-generator.jar -configfile generator.xml -overwrite