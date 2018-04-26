name := "learning-kafka-streams"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-streams" % "1.1.0",
  "com.typesafe" % "config" % "1.2.1"
)