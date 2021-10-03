package com.scalaspark.repositories

import com.scalaspark.config.{H2JDBCConfiguration, SparkConfiguration}
import com.scalaspark.enums.{FormatEnums, TableEnums}
import com.scalaspark.models.PlayList
import com.scalaspark.services.{PlayListServicesImpl, SparkServices}
import org.junit.Assert.assertTrue
import org.junit.{After, Before, Test}

import java.util

class PlayListRepositoryImplTest {

  private var spark: SparkServices = null
  private var playListServices: PlayListServicesImpl = null

  @Before
  def setUp(): Unit = {
    new SparkConfiguration().init()
    this.spark = new SparkServices

    val dataBase = ""
    val table = TableEnums.play_list.toString
    val format = FormatEnums.ORC.toString

    val repository = new PlayListRepositoryImpl(this.spark, dataBase, table, format)
    playListServices = new PlayListServicesImpl(this.spark, repository)
  }

  @After
  def cleanUp(): Unit = {
    H2JDBCConfiguration.deleteTable()
  }

  @Test def mustSaveAPlayList(): Unit = {
    val playList: PlayList = new PlayList(1, "the best Northlane")
    val playList2: PlayList = new PlayList(2, "the best Stone Sour")

    val playLists: util.ArrayList[PlayList] = new util.ArrayList[PlayList]()
    playLists.add(playList)
    playLists.add(playList2)

    playListServices.save(playLists)
    val playListSaveds = playListServices.findAll
    assertTrue(playListSaveds.size == 2)
  }

  @Test def mustFindAllPlayListOfString(): Unit = {
    val playList: PlayList = new PlayList(1, "the best Northlane")
    val playList2: PlayList = new PlayList(2, "the best Stone Sour")

    val playLists: util.ArrayList[PlayList] = new util.ArrayList[PlayList]()
    playLists.add(playList)
    playLists.add(playList2)

    playListServices.save(playLists)

    val playListSaveds = playListServices.findAllPlayList
    assertTrue(playListSaveds.size == 2)
  }

}