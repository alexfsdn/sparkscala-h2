package com.scalaspark.services

import java.util
import com.scalaspark.models.PlayList

trait PlayListServices {

  def save(playLists: util.ArrayList[PlayList]);

  def exportCsv(playLists: util.ArrayList[PlayList], path: String, fileName: String);

  def findById(id: Long): PlayList

  def findAll(): util.ArrayList[PlayList]

  def findAllPlayList(): util.ArrayList[String]
}
