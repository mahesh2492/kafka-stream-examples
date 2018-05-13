package kafka.streams

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.log4j.Logger

object Constants {

  val logger: Logger = Logger.getLogger(this.getClass)
  val config: Config = ConfigFactory.load()
  val firstTopic: String = config.getString("FIRST_TOPIC")
  val secondTopic: String = config.getString("SECOND_TOPIC")
  val uuid: String = java.util.UUID.randomUUID().toString
  val joinedStreamTopic: String = config.getString("JOINED_STREAM") + s"-$uuid"
}
