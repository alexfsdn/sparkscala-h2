name := "scalaspark"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.7"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.7" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.4.7" % "provided"
libraryDependencies += "com.h2database" % "h2" % "1.4.192"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.2.0" % "provided"



libraryDependencies += "junit" % "junit" % "4.12" % Test

