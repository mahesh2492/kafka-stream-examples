name := "learning-kafka-streams"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies ++= Seq(
  //kafka-streams
  "org.apache.kafka" % "kafka-streams" % "1.1.0",

  //akka
  "com.typesafe.akka" %% "akka-actor" % "2.5.2",

  //type safe
  "com.typesafe" % "config" % "1.2.1"
)