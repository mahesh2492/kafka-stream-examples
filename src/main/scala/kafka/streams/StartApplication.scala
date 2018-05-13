package kafka.streams

import java.awt.print.PrinterAbortException
import java.util.Properties

import kafka.streams.Constants._
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams._
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Printed

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
  logger.info("Going to print joined stream to consosle")
  joinedStream.print()
  joinedStream.to(joinedStreamTopic) //to is method which will write joined stream to specified kafka topic

  private val topology = builder.build()
  val stream = new KafkaStreams(topology, properties)
  stream.start()
}
