package kafka.streams

import org.apache.kafka.streams.kstream.{JoinWindows, KStream, KStreamBuilder, ValueJoiner}



class JoinedStreamExample {

  /**
    * This method whill join two kafka streams via '-'
     * @param builder KStreamBuilder
    * @param firstTopic String
    * @param secondTopic String
    * @return KStream
    */
  def join(builder: KStreamBuilder, firstTopic: String, secondTopic: String): KStream[String, String] = {
    val firstStream: KStream[String, String] = builder.stream(firstTopic)
    val secondStream: KStream[String, String] = builder.stream(secondTopic)

    firstStream.join(secondStream, new ValueJoiner[String, String, String] {
      override def apply(value1: String, value2: String): String = value1 +  "-" + value2
    }, JoinWindows.of(1000))
  }
}
