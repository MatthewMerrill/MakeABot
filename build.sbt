name := """MakeABot"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.18",
  javaJdbc,
  cache,
  javaWs
)

libraryDependencies += jdbc

playEbeanModels in Compile := Seq("models.*")
playEbeanDebugLevel := 4
playEbeanModels in Test := Seq("models.*")

fork in run := false