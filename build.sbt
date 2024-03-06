ThisBuild / version := "0.1.1-SNAPSHOT-TEST"

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
  .settings(
    name := "SPARK_OOZIE_EVENT"
  )

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.2.3",
  "org.apache.spark" %% "spark-sql" % "3.2.3",
  "com.oracle.database.jdbc" % "ojdbc8" % "21.10.0.0",
  "org.apache.hadoop" % "hadoop-common" % "3.2.1",
  "org.apache.spark" %% "spark-streaming" % "3.2.3",
  "commons-net" % "commons-net" % "3.8.0"
//  "org.apache.oozie" % "oozie-client" % "5.2.1"
)

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  "SPARK_OOZIE_EVENT-0.1.2-SNAPSHOT-TEST.jar"
}

