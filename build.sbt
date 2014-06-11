name := "diff-guideline"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.1"

mainClass in (Compile,run) := Some("Application")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.googlecode" % "difflib" % "1.2.1" from "https://java-diff-utils.googlecode.com/files/diffutils-1.2.1.jar"
, "net.databinder.dispatch" %% "dispatch-core" % "0.11.0"
, "org.slf4j" % "slf4j-simple" % "1.6.4"
, "com.github.nscala-time" %% "nscala-time" % "1.2.0"
, "com.typesafe" % "config" % "1.2.1"
)
