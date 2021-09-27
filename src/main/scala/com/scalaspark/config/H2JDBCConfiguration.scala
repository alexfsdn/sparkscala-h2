package com.scalaspark.config


import java.sql.{Connection, DriverManager, SQLException}

object H2JDBCConfiguration {
  private val jdbcURL = "jdbc:h2:~/testscala"
  private val jdbcUsername = "sa"
  private val jdbcPassword = ""

  private val createTableSQL = "create table play_list (ID int, NAME varchar(50));"
  private val dropTableSQL = "drop table if exists play_list;"
  private val deleteTableSQL = "delete from play_list;"

  def getConnection: Connection = {
    try {
      val connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)
      connection
    } catch {
      case e: SQLException =>
        e.printStackTrace()
        null
    }
  }

  def dropTable(): Unit = {
    System.out.println(dropTableSQL)
    try {
      val connection = H2JDBCConfiguration.getConnection
      val statement = connection.createStatement
      statement.execute(dropTableSQL)
    } catch {
      case e: SQLException =>
        H2JDBCConfiguration.printSQLException(e)
    }
  }

  def deleteTable(): Unit = {
    System.out.println(deleteTableSQL)
    try {
      val connection = H2JDBCConfiguration.getConnection
      val statement = connection.createStatement
      statement.execute(deleteTableSQL)
    } catch {
      case e: SQLException =>
        H2JDBCConfiguration.printSQLException(e)
    }
  }


  @throws[SQLException]
  def createTable(): Unit = {
    System.out.println(createTableSQL)
    try {
      val connection = H2JDBCConfiguration.getConnection
      val statement = connection.createStatement
      statement.execute(dropTableSQL)
      statement.execute(createTableSQL)
    } catch {
      case e: SQLException =>
        H2JDBCConfiguration.printSQLException(e)
    }
  }

  def printSQLException(ex: SQLException): Unit = {
    import scala.collection.JavaConversions._
    for (e <- ex) {
      if (e.isInstanceOf[SQLException]) {
        e.printStackTrace(System.err)
        System.err.println("SQLState: " + e.asInstanceOf[SQLException].getSQLState)
        System.err.println("Error Code: " + e.asInstanceOf[SQLException].getErrorCode)
        System.err.println("Message: " + e.getMessage)
        var t = ex.getCause
        while ( {
          t != null
        }) {
          System.out.println("Cause: " + t)
          t = t.getCause
        }
      }
    }
  }
}
