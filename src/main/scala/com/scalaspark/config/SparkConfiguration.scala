package com.scalaspark.config

import com.scalaspark.services.SparkServices

import java.util.Properties

class SparkConfiguration {
  private val jdbcURL = "jdbc:h2:~/test"
  private val jdbcUsername = "sa"
  private val jdbcPassword = ""
  private val table = "play_list"

  def init(): Unit = {
    val spark = new SparkServices().connect
    //PRIMEIRO CRIAMOS A TABELA NA MEMÓRIA H2
    new H2JDBCConfiguration().createTable()
    //AQUI EM FRENTE VAMOS FAZER COM QUE O SPARK ENCHERGUE
    //E ENTÃO LOGO EM SEGUIDA PODEREMOS CRIAR NOSSA TABELA
    //COM O SPARK
    //E PRÓPRIO H2 É QUEM IRÁ DROPAR A TABELA UTILIZADA
    //PELO SPARK
    val connectionProperties = new Properties()
    connectionProperties.put("user", jdbcUsername)
    connectionProperties.put("password", jdbcPassword)
    val jdbcH2DF = spark.read.jdbc(jdbcURL, table, connectionProperties)
    jdbcH2DF.createOrReplaceTempView(table)
  }

}
