package com.mirana.spark
import org.apache.spark.sql.SparkSession;

object ExampleApp {

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder().appName("READ THE FILE").getOrCreate();
    //  你的HDFS路径
    val rdd = sc.read.textFile("/your/hdfs/input");
    //  请不要忽略此语句
    import sc.implicits._
    rdd.map(line => line.split("\t").apply(2))
      .distinct()
      .write
      .save("/your/hdfs/output");
    sc.stop();
  }
}
