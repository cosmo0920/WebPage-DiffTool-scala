name := "diff-guideline"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.4"

mainClass in (Compile,run) := Some("Application")

libraryDependencies += "com.googlecode" % "difflib" % "1.2.1" from "https://java-diff-utils.googlecode.com/files/diffutils-1.2.1.jar"
