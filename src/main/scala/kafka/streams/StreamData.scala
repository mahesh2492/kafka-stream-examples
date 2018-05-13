package kafka.streams

import java.util.{Calendar, Properties}

import akka.actor.ActorSystem
import kafka.streams.Constants._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random

object StreamData extends App {

  val properties = new Properties()

  properties.put("bootstrap.servers", config.getString("BOOTSTRAP_SERVER"))
  properties.put("key.serializer", config.getString("SERIALIZER"))
  properties.put("value.serializer", config.getString("VALUE_SERIALIZER"))

  val producer = new KafkaProducer[String, String](properties)
  val system = ActorSystem("word-generator")
  val messageList = List("spark", "mesos", "akka", "cassandra", "kafka", "lagom", "mongodb",
                          "mysql", "akka-http", "angular", "flink","storm", "hadoob")
  var currentTime = Calendar.getInstance().getTime.getTime

  logger.info(s"Streaming data to kafka topics $firstTopic and $secondTopic")

  /**
    * akka scheduler to generate words in every 5 seconds
    */
  system.scheduler.schedule(0 seconds, 5 seconds) {

    val firstMessage = Random.shuffle(messageList).head
    val secondMessage = Random.shuffle(messageList).last
    val firstRecord: ProducerRecord[String, String] = new ProducerRecord[String, String](firstTopic, currentTime.toString, firstMessage)
    val secondRecord: ProducerRecord[String, String] = new ProducerRecord[String, String](secondTopic, currentTime.toString, secondMessage)

    currentTime += 1

    producer.send(firstRecord)
    producer.send(secondRecord)
  }
}
