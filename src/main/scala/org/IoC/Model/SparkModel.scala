package org.IoC.Model

import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions.from_json
import org.apache.spark.sql.functions.col
import org.di.MicroBatchProcessor
import org.di.Model.ConfigModel

class SparkModel {

  def createSession(appName: String): SparkSession = {
    /*
    # Spark session 선언
    # appName -> str : SparkSession의 App 이름 변수
    # return : pyspark.sql.SparkSession
    */
    SparkSession
      .builder
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .config("partitionoverwritemode", "dynamic")
      .appName(appName)
      .enableHiveSupport()
      .getOrCreate()
  }

  def kafka_read_stream_session(spark: SparkSession) = {
    /*
    # kafka 의 지정된 클러스터, 토픽에서 stream 을 실시간으로 읽어오는 함수
    */

    spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", ConfigModel.BOOTSTRAP_SERVER)
      .option("subscribe", ConfigModel.SINK_TOPIC)
      .option("startingOffsets", ConfigModel.SINK_STARTING_OFFSETS)
      .option("kafka.group.id", ConfigModel.SINK_GROUP_ID)
      .option("failOnDataLoss", ConfigModel.FAIL_ON_DATA_LOSS)
      .option("maxOffsetsPerTrigger", ConfigModel.SINK_MAX_OFFSETS_PER_TRIGGER)
      .load()
  }


  def kafka_json_value_parser(spark:DataFrame):DataFrame={
    /*
    # kafka 에서 읽어온 json value 를 테이블화 시키는 함수
    */
    spark.selectExpr("CAST(value AS STRING)")
  }



  def kafka_streaming_starter(spark:DataFrame, sparkSession:SparkSession):Unit={
    /*
    # 카프카 스트리밍 실행 함수
    */

    val microBatchProcessor = new MicroBatchProcessor

    spark
      .writeStream
      .outputMode("append")
      .foreachBatch((batchDF: DataFrame, batchId: Long) =>
        microBatchProcessor.batch(batchDF, batchId, sparkSession)
      )
      .option("checkpointLocation", ConfigModel.SINK_CHECKPOINT_LOCAION)
      .start()
      .awaitTermination()

  }

}
