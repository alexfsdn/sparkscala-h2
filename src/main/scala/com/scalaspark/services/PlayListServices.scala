package com.scalaspark.services

import java.util
import com.scalaspark.models.PlayList

trait PlayListServices {

  def save(playLists: util.ArrayList[PlayList]);

  def findById(id: Long): PlayList

  def findAll(): util.List[PlayList]

  def findAllPlayList(): util.List[String]

  def exportCsv(playLists: util.ArrayList[PlayList], path: String, fileName: String);

}
