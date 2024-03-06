package org.di

import org.apache.spark.sql._
import org.di.Model.OozieSubmit

class MicroBatchProcessor {
  def batch(df: DataFrame, batch_id: Long, sparkSession: SparkSession): Unit = {
    /*
    # 의존성 주입 메인 함수
     */

//    val kafkaPublisher = new KafkaPublisher
    val oozieSubmit = new OozieSubmit

    // 들어온 스트림이 여러 개 잡힐 경우 대비 foreach
    df.foreach(row => {
      val kafka_value = """"""" +  row(0).toString.replace(""""""", "'") + """""""
      oozieSubmit.callOozieJob(kafka_value)


    })


  }
}
