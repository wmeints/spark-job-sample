name := "sample-project"
organization := "com.infosupport"
scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "3.2.2",
    "org.apache.spark" %% "spark-sql" % "3.2.2",
    "com.github.scopt" %% "scopt" % "4.1.0",
    "org.scalatest" %% "scalatest" % "3.2.13" % "test"
)
