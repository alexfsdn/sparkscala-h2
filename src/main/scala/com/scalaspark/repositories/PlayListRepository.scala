package com.scalaspark.repositories

import com.scalaspark.models.PlayList

import java.util

trait PlayListRepository {
  def save(playLists: util.ArrayList[PlayList]);

  def findById(id: Long): PlayList

  def findAll(): List[PlayList]

  def findAllPlayList(): List[String]

}
