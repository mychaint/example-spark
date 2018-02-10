package com.mychaint.example

import org.apache.spark.rdd.RDD

class WordCount {
  def process(rdd: RDD[String]): RDD[String] = {
    rdd.map(s => s) 
  }
}
