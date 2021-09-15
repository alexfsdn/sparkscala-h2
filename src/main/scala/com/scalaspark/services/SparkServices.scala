package com.scalaspark.services

import org.apache.spark.sql.SparkSession

class SparkServices {

  def connect: SparkSession = {
    val spark = SparkSession.builder.master("local")
      .appName("Java Spark SQL")
      .config("spark.some.conifg.option", "some-value")
      .getOrCreate
    //.enableHiveSupport()
       spark
  }
}
