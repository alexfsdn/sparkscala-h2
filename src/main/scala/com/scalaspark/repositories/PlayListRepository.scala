package com.scalaspark.repositories

import com.scalaspark.models.PlayList

import java.util

trait PlayListRepository {
  def save(playLists: util.ArrayList[PlayList]);

  def findById(id: Long): PlayList

  def findAll(): util.List[PlayList]

  def findAllPlayList(): util.List[String]

}
