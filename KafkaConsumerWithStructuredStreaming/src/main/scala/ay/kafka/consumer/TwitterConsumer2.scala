package ay.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.LongDeserializer
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
      .option("kafka.bootstrap.servers", "bootstrapServer Host:Port")
      .option("subscribe", "topic")
      .load()

  val query = df.writeStream
      .format("parquet")
      .outputMode("append")
      .option("checkpointLocation", "path to commit checkpoints")
      .start("path to write parquet files to")

  query.awaitTermination()

  }
}
