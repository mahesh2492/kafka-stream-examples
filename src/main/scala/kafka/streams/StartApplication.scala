package kafka.streams

import java.util.Properties

import kafka.streams.Constants._
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.{StreamsBuilder, _}

/**
  * Starting point of application
  */
object StartApplication extends App {

  val properties = new Properties()

  properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-application")
  properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, config.getString("BOOTSTRAP_SERVER"))
  properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
  properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)

  val builder = new StreamsBuilder()

  val joinedStream = (new JoinedStreamExample).join(builder, firstTopic, secondTopic)
  logger.info("Going to print joined stream to console")
  joinedStream.foreach((key: String, value: String) => logger.info(s"Key: $key value: $value \n"))

  joinedStream.to(joinedStreamTopic) //to is a method which will write joined streams to specified kafka topic

  private val topology = builder.build()
  val stream = new KafkaStreams(topology, properties)
  stream.start()
}
