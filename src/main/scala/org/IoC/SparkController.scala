package org.IoC

import org.IoC.Model.SparkModel

class SparkController {

  def spark_streaming_starter(app_name: String) = {

    val spark = new SparkModel

    val spark_session = spark.createSession(app_name)

    val spark_streaming_session = spark.kafka_read_stream_session(spark_session)

    val spark_json_parsed = spark.kafka_json_value_parser(spark_streaming_session)

    spark.kafka_streaming_starter(spark_json_parsed, spark_session)
  }


}
