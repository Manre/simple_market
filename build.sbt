name := """simple_market"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaJpa,
  cache,
  javaWs,
  "org.apache.derby" % "derby" % "10.11.1.1",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.8.Final"
)
