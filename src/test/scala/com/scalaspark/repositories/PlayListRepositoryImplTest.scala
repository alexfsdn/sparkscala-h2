package com.scalaspark.repositories

import com.scalaspark.config.{H2JDBCConfiguration, SparkConfiguration}
import com.scalaspark.models.PlayList
import com.scalaspark.services.{PlayListServicesImpl, SparkServices}
import org.apache.spark.sql.{Dataset, Encoders}
import org.junit.Assert.assertTrue
import org.junit.{After, AfterClass, Before, BeforeClass, Test}

import java.util

class PlayListRepositoryImplTest {

  private var spark: SparkServices = null

  private val playListServices: PlayListServicesImpl = null

  @Before
  def setUp(): Unit = {
    new SparkConfiguration().init()
    this.spark = new SparkServices

  }

  @After
  def cleanUp(): Unit = {
    H2JDBCConfiguration.deleteTable()
  }

  @Test def mustSaveAPlayList(): Unit = {
    val playList: PlayList = new PlayList(1L, "the best Northlane")
    val playList2: PlayList = new PlayList(2L, "the best Stone Sour")

    val playLists: util.ArrayList[PlayList] = new util.ArrayList[PlayList]()
    playLists.add(playList)
    playLists.add(playList2)

    playListServices.save(playLists)
    val playListSaveds = playListServices.findAll
    assertTrue(playListSaveds.size == 2)
  }

  @Test def mustFindAllPlayListOfString(): Unit = {
    val playList: PlayList = new PlayList(1L, "the best Northlane")
    val playList2: PlayList = new PlayList(2L, "the best Stone Sour")

    val playLists: util.ArrayList[PlayList] = new util.ArrayList[PlayList]()
    playLists.add(playList)
    playLists.add(playList2)

    playListServices.save(playLists)

    val playListSaveds = playListServices.findAllPlayList
    assertTrue(playListSaveds.size == 2)
  }

  @Test def mustExportCsvT(): Unit = {
    val VALUE_EXPECTED: String = "-RECORD 0------------------\n" + " id   | 1                  \n" + " name | Northlane          \n" + "-RECORD 1------------------\n" + " id   | 2                  \n" + " name | Linkin Park        \n" + "-RECORD 2------------------\n" + " id   | 3                  \n" + " name | Momoland           \n" + "-RECORD 3------------------\n" + " id   | 4                  \n" + " name | ITZY               \n" + "-RECORD 4------------------\n" + " id   | 5                  \n" + " name | Fire From The Gods \n" + "-RECORD 5------------------\n" + " id   | 6                  \n" + " name | Slipknot           \n" + "-RECORD 6------------------\n" + " id   | 7                  \n" + " name | Stone Sour         \n" + "-RECORD 7------------------\n" + " id   | 9                  \n" + " name | Black Pink         \n"
    val BASE_PATH: String = "\\"
    val FILE_NAME: String = "lista.csv"
    val pl: Dataset[PlayList] = spark.connect.read.format("csv").option("header", "true").option("inferschema", "true").load(BASE_PATH.concat(FILE_NAME)).as(Encoders.bean(classOf[PlayList]))
  }

}