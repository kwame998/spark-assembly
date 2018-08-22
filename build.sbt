lazy val sparkSettings = Seq(
  organization := "com.didichuxing",
  version := "1.0",
  scalaVersion := "2.12.3",
  libraryDependencies := Seq(
    "org.scalactic" %% "scalactic" % "3.0.5",
    "org.scalatest" %% "scalatest" % "3.0.5" % "test",
    "org.apache.spark" % "spark-sql_2.11" % "2.3.1",
    "org.apache.spark" % "spark-streaming_2.11" % "2.3.1",
    "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.3.1"
  )
)

lazy val root = (project in file("."))
  .settings(
    sparkSettings,
    name := "spark-assembly",
    mainClass in assembly := Some("com.mirana.spark.ExampleApp"),
    assemblyJarName in assembly := "scala-spark-final.jar",
    assemblyMergeStrategy in assembly := {
      case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
      case PathList("javax", "inject", xs @ _*) => MergeStrategy.last
      case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
      case PathList("org", "apache", xs @ _*) => MergeStrategy.last
      case PathList("org", "aopalliance", xs @ _*) => MergeStrategy.last
      case PathList("net", "jpountz", xs @ _*) => MergeStrategy.last
      case PathList("com", "google", xs @ _*) => MergeStrategy.last
      case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
      case PathList("com", "codahale", xs @ _*) => MergeStrategy.last
      case PathList("com", "yammer", xs @ _*) => MergeStrategy.last
      case "about.html" => MergeStrategy.rename
      case "META-INF/mailcap" => MergeStrategy.last
      case "META-INF/mimetypes.default" => MergeStrategy.last
      case "plugin.properties" => MergeStrategy.last
      case "git.properties" => MergeStrategy.last
      case "log4j.properties" => MergeStrategy.last
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )