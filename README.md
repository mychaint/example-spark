# Spark Example Project
This a template of maven project of apache spark application, containing mixed java and scala programs.
>This folder structure and maven config is suitable to build a robost and long-term maintainable enterprise project.
### How to create scala-maven project
##### Requirements
- JDK
- Maven
##### Create project by maven archetype
let's use archetyp provided by net.alchim31.maven, artifactId is scala-archetype-simple
```bash
mvn -B archetype:generate\
    -DarchetypeGroupId=net.alchim31.maven\
    -DarchetypeArtifactId=scala-archetype-simple\
    -DgroupId=<groupId>\
    -DartifactId=<artifactId>
```
it is possible to use maven official template of scala maven project: `org.scala-tools.archetypes:scala-archetype-simple`
please specify repository url `-DremoteRepositories=http://scala-tools.org/repo-releases`
##### Project Structure
###### src
>This is the folder to contain source code. There are two different sub folders under it, "main" is for production scripts to 
serve business demands, while "test" folder contains test suites for every single production script.
###### pom.xml
>header defines model version of prototype, groupId, artifactId as well as project version
```xml
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.mychaint</groupId>
    <artifactId>example-spark</artifactId>
    <version>1.0-SNAPSHOT</version>
    
    ...
</project>
```
>properties referred by dependencies 
```xml
    <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <encoding>UTF-8</encoding>
        <scala.version>2.11.8</scala.version>
        <scala.compat.version>2.11</scala.compat.version>
    </properties>
```
>under "build" tag, define scala source code and test code directories
```xml
    <sourceDirectory>src/main/scala</sourceDirectory>
    <testSourceDirectory>src/test/scala</testSourceDirectory>
```
> include scala maven plugin to enable scala compilation
```xml
<plugin>
    <!-- see http://davidb.github.com/scala-maven-plugin -->
    <groupId>net.alchim31.maven</groupId>
    <artifactId>scala-maven-plugin</artifactId>
    <version>3.2.2</version>
    <executions>
        <execution>
            <goals>
                <goal>compile</goal>
                <goal>testCompile</goal>
            </goals>
            <configuration>
                <args>
                    <arg>-dependencyfile</arg>
                    <arg>${project.build.directory}/.scala_dependencies</arg>
                </args>
            </configuration>
        </execution>
    </executions>
</plugin>
```
>include scala test plugin to enable scala test
```xml
<plugin>
    <groupId>org.scalatest</groupId>
    <artifactId>scalatest-maven-plugin</artifactId>
    <version>1.0</version>
    <configuration>
        <reportsDirectory>${project.build.directory}/surefire-reports</reportsDirectory>
        <junitxml>.</junitxml>
        <filereports>WDF TestSuite.txt</filereports>
    </configuration>
    <executions>
        <execution>
            <id>test</id>
            <goals>
                <goal>test</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
>include junit and scalatest as dependencies
```xml
 <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.scalatest</groupId>
    <artifactId>scalatest_${scala.compat.version}</artifactId>
    <version>3.0.1</version>
    <scope>test</scope>
</dependency>
```
>include spark test base to boost your spark test suites, this lib is able to handle spark context initialization and simplify RDD comparsion
```xml
<dependency>
    <groupId>com.holdenkarau</groupId>
    <artifactId>spark-testing-base_${scala.compat.version}</artifactId>
    <version>2.2.0_0.8.0</version>
    <scope>test</scope>
</dependency>
```
>for dateframe test purposes, also include spark-hive and spark-sql modules, and specify scope of spark-hive as test
```xml
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-hive_2.11</artifactId>
    <version>2.1.1</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-sql_2.11</artifactId>
    <version>2.1.1</version>
</dependency>
```
>include spark core
```xml
<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-core_${scala.compat.version}</artifactId>
    <version>2.1.1</version>
</dependency>
```
