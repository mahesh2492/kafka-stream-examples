package kafka.streams

import java.util.Properties

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object WordsGenerator extends App {

  val config = ConfigFactory.load()
  val properties = new Properties()

  val firstTopic = config.getString("FIRST_TOPIC")
  val secondTopic = config.getString("SECOND_TOPIC")

  properties.put("bootstrap.servers", config.getString("BOOTSTRAP_SERVER"))
  properties.put("key.serializer", config.getString("SERIALIZER"))
  properties.put("value.serializer", config.getString("VALUE_SERIALIZER"))

  val producer = new KafkaProducer[String, String](properties)
  val system = ActorSystem("generator")
  var counter = 1

  system.scheduler.schedule(0 seconds, 5 seconds) {

    val message = "name"
    val firstRecord: ProducerRecord[String, String] = new ProducerRecord[String, String](firstTopic, counter.toString, message)
    val secondRecord: ProducerRecord[String, String] = new ProducerRecord[String, String](secondTopic, counter.toString, message)

    counter += 1

    producer.send(firstRecord)
    producer.send(secondRecord)
  }

}
