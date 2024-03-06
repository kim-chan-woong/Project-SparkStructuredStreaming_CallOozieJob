package org.di.Model

object ConfigModel {
  private val _APP_NAME = "SPARK_OOZIE_EVENT-0.1.1-TEST"
  private val _BOOTSTRAP_SERVER = "mast01:9092,mast02:9092,mast03:9092"
  private val _SINK_TOPIC = "spark-oozie-event-topic-02-test"
  private val _SINK_STARTING_OFFSETS = "latest"
  private val _SINK_GROUP_ID = "SPARK_OOZIE_EVENT_GROUP_TEST"
  private val _SINK_CHECKPOINT_LOCATION = "/user/spark/checkpoint/spark_oozie_event/APP_01_TEST"
  private val _SINK_MAX_OFFSETS_PER_TRIGGER = 50
  private val _FAIL_ON_DATA_LOSS = "false"

  private val _OOZIE_LIBPATH = "hdfs://hadoopHA:8020/user/spark/share/lib/"
  private val _OOZIE_WF_APPLICATION_PATH = "hdfs://hadoopHA:8020/user/oozie/assets/oozie/test/spark_oozie_event/"
  private val _WFAPPPATH = "hdfs://hadoopHA:8020/user/oozie/assets/oozie/test/spark_oozie_event/assets/"
  private val _APPURL = "http:/util01:11000/oozie/v1/jobs?action=start&queue=spark"


  def APP_NAME:String = this._APP_NAME
  def BOOTSTRAP_SERVER:String = this._BOOTSTRAP_SERVER
  def SINK_TOPIC:String = this._SINK_TOPIC
  def SINK_STARTING_OFFSETS:String = this._SINK_STARTING_OFFSETS
  def SINK_GROUP_ID:String = this._SINK_GROUP_ID
  def SINK_CHECKPOINT_LOCAION:String = this._SINK_CHECKPOINT_LOCATION
  def SINK_MAX_OFFSETS_PER_TRIGGER:Int = this._SINK_MAX_OFFSETS_PER_TRIGGER
  def FAIL_ON_DATA_LOSS:String = this._FAIL_ON_DATA_LOSS

  def OOZIE_LIBPATH:String = this._OOZIE_LIBPATH
  def OOZIE_WF_APPLICATION_PATH:String = this._OOZIE_WF_APPLICATION_PATH
  def WFAPPPATH:String = this._WFAPPPATH
  def APPURL:String = this._APPURL


}
