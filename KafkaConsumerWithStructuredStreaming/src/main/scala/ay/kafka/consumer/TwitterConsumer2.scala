package ay.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
/*import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Duration*/
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.LongDeserializer
/*import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe*/
import org.apache.spark.sql.functions._
import org.apache.spark.sql.SparkSession

object TwitterConsumer2 {
  
  def main(args: Array[String]): Unit = {
    
   val spark = SparkSession
  .builder
  .appName("TwitterConsumer")
  .getOrCreate()

  val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "bigdata-tweets")
      .load()

  /*val query = df.writeStream
  .outputMode("append")
  .format("console")
  .start()*/
  val query = df.writeStream
    .format("parquet")
      .outputMode("append")
      .option("checkpointLocation", "/home/amit/KafkaTwitterProject/checkPoints")
      .start("/home/amit/KafkaTwitterProject/parquet/tweets")

  query.awaitTermination()

   /* import spark.implicits._

    val tweets = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]
    tweets.show(10)*/

    
    

  }
}
