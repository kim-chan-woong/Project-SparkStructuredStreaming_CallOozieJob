package org.di.Model


import java.io.{BufferedWriter, OutputStreamWriter}
import java.net.{HttpURLConnection, URL}


class OozieSubmit extends Serializable {

  def callOozieJob(kafka_value: String): Unit = {


    val oozie_libpath = ConfigModel.OOZIE_LIBPATH
    val oozie_wf_application_path = ConfigModel.OOZIE_WF_APPLICATION_PATH
    val wfAppPath = ConfigModel.WFAPPPATH
    val appUrl = ConfigModel.APPURL

    val oozie_xml = f"""
                       |<configuration>
                       |  <property>
                       |    <name>oozie.libpath</name>
                       |    <value>$oozie_libpath%s</value>
                       |  </property>
                       |  <property>
                       |    <name>oozie.use.system.libpath</name>
                       |    <value>true</value>
                       |  </property>
                       |  <property>
                       |    <name>dryrun</name>
                       |    <value>False</value>
                       |  </property>
                       |  <property>
                       |    <name>security_enabled</name>
                       |    <value>False</value>
                       |  </property>
                       |  <property>
                       |    <name>user.name</name>
                       |    <value>spark</value>
                       |  </property>
                       |  <property>
                       |    <name>mapreduce.job.user.name</name>
                       |    <value>spark</value>
                       |  </property>
                       |  <property>
                       |    <name>mapreduce.job.queuename</name>
                       |    <value>spark</value>
                       |  </property>
                       |  <property>
                       |    <name>master</name>
                       |    <value>yarn</value>
                       |  </property>
                       |  <property>
                       |    <name>mode</name>
                       |    <value>cluster</value>
                       |  </property>
                       |  <property>
                       |    <name>oozie.wf.application.path</name>
                       |    <value>$oozie_wf_application_path%s</value>
                       |  </property>
                       |  <property>
                       |    <name>queueName</name>
                       |    <value>spark</value>
                       |  </property>
                       |  <property>
                       |    <name>jobTracker</name>
                       |    <value>rm159</value>
                       |  </property>
                       |  <property>
                       |    <name>wfAppPath</name>
                       |    <value>$wfAppPath%s</value>
                       |  </property>
                       |  <property>
                       |    <name>nameNode</name>
                       |    <value>hdfs://hadoopHA:8020</value>
                       |  </property>
                       |  <property>
                       |    <name>resourceManager</name>
                       |    <value>mast02:8032</value>
                       |  </property>
                       |  <property>
                       |    <name>parameter</name>
                       |    <value>$kafka_value%s</value>
                       |  </property>
                       |</configuration>
      """.stripMargin

    val apiUrl = new URL(appUrl)
    val connection = apiUrl.openConnection.asInstanceOf[HttpURLConnection]

    try{

      connection.setRequestMethod("POST")
      connection.setRequestProperty("Content-Type", "application/xml;charset=UTF-8")

      connection.setDoOutput(true)

      val out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream, "UTF-8"))
      out.write(oozie_xml)
      out.close()

      val responseCode = connection.getResponseCode

      if(responseCode == 200) {
        println("good")

      }else{
        println(connection.getResponseMessage)
      }

    } finally {
      println(connection.getResponseMessage)
    }


  }

}

