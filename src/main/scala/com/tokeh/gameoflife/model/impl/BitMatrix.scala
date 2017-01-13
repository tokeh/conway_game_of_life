package com.tokeh.gameoflife.model.impl

import scala.collection.mutable

class BitMatrix(val rows: Int, val columns: Int) {

  private val matrix = new mutable.BitSet(rows * columns)

  def apply(row: Int): BitRow = new BitRow(row)

  require(rows >= 1 && columns >= 1)

  class BitRow(val row: Int) {
    def apply(column: Int): Boolean = matrix(row * columns + column)

    def update(column: Int, value: Boolean): Unit = matrix(row * columns + column) = value
  }

  def set(row: Int, column: Int, value: Boolean): Unit = {
    this.synchronized{ matrix(row * columns + column) = value }
  }

  def get(row: Int, column: Int): Boolean = this.synchronized{ matrix(row * columns + column) }

}
