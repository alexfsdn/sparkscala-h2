package com.scalaspark.repositories

import com.scalaspark.enums.PlayListEnums
import com.scalaspark.models.PlayList
import com.scalaspark.services.SparkServices
import org.apache.spark.sql.Encoders

import java.util

class PlayListRepositoryImpl(spark: SparkServices, dataBase: String, table: String, format: String) extends PlayListRepository {

  override def save(playLists: util.ArrayList[PlayList]): Unit = {
    val dataset = spark.connect.createDataFrame(playLists, classOf[PlayList])
    dataset.write.format(format).insertInto(dataBase.concat(table))
  }

  override def findById(id: Long): PlayList = {
    val playList = spark.connect.sql("select " + PlayListEnums.ID.toString + ", " + PlayListEnums.NAME.toString + " from " + dataBase.concat(table) + " where " + PlayListEnums.ID + " = " + id).dropDuplicates.as(Encoders.bean(classOf[PlayList])).first
    playList
  }

  override def findAll(): util.List[PlayList] = {
    val playLists = spark.connect.sql("select " + PlayListEnums.ID + ", " + PlayListEnums.NAME + " from " + dataBase.concat(table)).dropDuplicates.as(Encoders.bean(classOf[PlayList])).collectAsList
    playLists
  }

  override def findAllPlayList(): util.List[String] = {
    val names = spark.connect.sql("select " + PlayListEnums.NAME + " from " + dataBase.concat(table)).dropDuplicates.as(Encoders.bean(classOf[String])).collectAsList()
    names
  }
}
