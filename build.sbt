name := "learning-kafka-streams"

version := "0.1"

scalaVersion := "2.12.5"

libraryDependencies ++= Seq(
  //kafka-streams
  "org.apache.kafka" % "kafka-streams" % "1.1.0",

  //akka
  "com.typesafe.akka" %% "akka-actor" % "2.5.2",

  //type safe
  "com.typesafe" % "config" % "1.2.1",

  //log4j
  "log4j" % "log4j" % "1.2.17",

  //for slfj4
  "org.slf4j" % "slf4j-simple" % "1.7.25",
)