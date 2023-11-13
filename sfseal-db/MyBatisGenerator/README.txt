Refer to the documentation for the MyBatis Generator application:
http://www.mybatis.org/generator/
http://www.mybatis.org/generator/running/runningFromCmdLine.html

Download mybatis generator here:
https://github.com/mybatis/generator/releases/download/mybatis-generator-1.3.3/mybatis-generator-1.3.3.zip

Download postgresql jar here:
https://jdbc.postgresql.org/download/postgresql-9.4.1211.jar

Use Java 1.8.

To run the MyBatis Generator application and regenerate the POJOs, MyBatis mappers, and xml mapper files, execute the following:

java -jar mybatis-generator-core-1.3.3.jar -configfile userGeneratorConfig.xml
java -jar mybatis-generator-core-1.3.3.jar -configfile communityGeneratorConfig.xml
