package com.scalaspark.services

import com.scalaspark.models.PlayList
import com.scalaspark.repositories.PlayListRepository

import java.util

class PlayListServicesImpl(sparkServices: SparkServices, playListRepository: PlayListRepository) extends PlayListServices {

  override def save(playLists: util.ArrayList[PlayList]): Unit = {
    playListRepository.save(playLists)
  }

  override def findById(id: Long): PlayList = {
    playListRepository.findById(id)
  }

  override def findAll(): List[PlayList] = {
    playListRepository.findAll()
  }

  override def findAllPlayList(): List[String] = {
    playListRepository.findAllPlayList()
  }

  override def exportCsv(playLists: util.ArrayList[PlayList], path: String, fileName: String): Unit = {
    val dataset = sparkServices.connect.createDataFrame(playLists, classOf[PlayList])
    dataset.write.csv(path.concat(fileName))
  }
}
