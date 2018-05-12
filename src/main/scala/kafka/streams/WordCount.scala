package kafka.streams

import java.util.Properties

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.KStreamBuilder

object WordCount extends App {

  val properties = new Properties()

  properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application")
  properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass)
  properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass)

  val builder = new KStreamBuilder()

  val textLines = builder.stream("wordcount-topic")
}
