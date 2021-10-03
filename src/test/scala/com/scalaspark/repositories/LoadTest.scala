package com.scalaspark.repositories

import com.scalaspark.models.PlayList
import com.scalaspark.services.SparkServices
import org.apache.spark.sql.{Dataset, Encoders}
import org.junit.Test
import org.junit.Assert.assertTrue


class LoadCsvTest {

  @Test def mustLoadCsv(): Unit = {
    val VALUE_EXPECTED: String = "-RECORD 0------------------\n" + " id   | 1                  \n" + " name | Northlane          \n" + "-RECORD 1------------------\n" + " id   | 2                  \n" + " name | Linkin Park        \n" + "-RECORD 2------------------\n" + " id   | 3                  \n" + " name | Momoland           \n" + "-RECORD 3------------------\n" + " id   | 4                  \n" + " name | ITZY               \n" + "-RECORD 4------------------\n" + " id   | 5                  \n" + " name | Fire From The Gods \n" + "-RECORD 5------------------\n" + " id   | 6                  \n" + " name | Slipknot           \n" + "-RECORD 6------------------\n" + " id   | 7                  \n" + " name | Stone Sour         \n" + "-RECORD 7------------------\n" + " id   | 9                  \n" + " name | Black Pink         \n"
    val BASE_PATH: String = "\\"
    val FILE_NAME: String = "lista.csv"
    val spark = new SparkServices().connect
    val pl = spark.read.format("csv")
      .option("header", "true")
      .option("inferschema", "true")
      .load(BASE_PATH.concat(FILE_NAME)).collect()
    pl.foreach(p => println(p)

    );

  }
}
