package com.scalaspark.repositories

import com.scalaspark.enums.PlayListEnums
import com.scalaspark.models.PlayList
import com.scalaspark.services.SparkServices
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.functions.col

import java.util
import scala.collection.mutable.ListBuffer

class PlayListRepositoryImpl(sparkServices: SparkServices, dataBase: String, table: String, format: String) extends PlayListRepository {

  override def save(playLists: util.ArrayList[PlayList]): Unit = {
    val spark = sparkServices.connect
    import spark.implicits._

    val playLs = spark.createDataset(playLists)
    playLs.show()
    playLs.select(col(PlayListEnums.ID.toString), col(PlayListEnums.NAME.toString))
      .write.format(format).mode("append").insertInto(dataBase.concat(table))
  }

  override def findById(id: Long): PlayList = {
    val spark = sparkServices.connect
    val playList = spark.sql("select " + PlayListEnums.ID.toString + ", " + PlayListEnums.NAME.toString + " from " + dataBase.concat(table) + " where " + PlayListEnums.ID + " = " + id).dropDuplicates
    playList.as(Encoders.bean(classOf[PlayList])).first()

  }

  override def findAll(): List[PlayList] = {
    val spark = sparkServices.connect
    val playLists = spark.sql("select " + PlayListEnums.ID.toString + ", " + PlayListEnums.NAME.toString + " from " + dataBase.concat(table)).dropDuplicates.collect()
    val lista = new ListBuffer[PlayList]()
    playLists.foreach( p =>
      lista.append(new PlayList(p.getInt(0), p.getString(1)))
    );
    lista.toList
  }

  override def findAllPlayList(): List[String] = {
    val spark = sparkServices.connect
    val names = spark.sql("select " + PlayListEnums.NAME + " from " + dataBase.concat(table)).dropDuplicates.collect()
    val lista = new ListBuffer[String]()
    names.foreach( p =>
      lista.append(p.getString(0))
    );
    lista.toList
  }
}