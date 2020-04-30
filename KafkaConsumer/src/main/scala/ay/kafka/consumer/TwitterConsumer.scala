package ay.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Duration
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe

object TwitterConsumer {
  
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("KafkaTweets")
    val ssc = new StreamingContext(sparkConf, new Duration(5000))

	val kafkaParams = Map[String, Object](
	"bootstrap.servers" -> "localhost:9092",
	"key.deserializer" -> classOf[LongDeserializer],
	"value.deserializer" -> classOf[StringDeserializer],
	"group.id" -> "consumer-1",
	"auto.offset.reset" -> "latest",
	"enable.auto.commit" -> (false: java.lang.Boolean)
	)
	
	val topics = Array("bigdata-tweets")
	val tweets = KafkaUtils.createDirectStream[Long, String](
	ssc,
	PreferConsistent,
	Subscribe[Long, String](topics, kafkaParams)
	)
	
	
    val lines = tweets.map(record => (record.key, record.value))
    lines.print()
    ssc.start()
    ssc.awaitTermination()

  }
}  
  
  
