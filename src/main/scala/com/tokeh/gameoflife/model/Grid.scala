package com.tokeh.gameoflife.model

trait Grid {

  def numberOfRows: Int

  def numberOfColumns: Int

  def apply(row: Int): GridRow

  def get(row: Int, column: Int): Boolean

  trait GridRow {
    def apply(column: Int): Boolean
  }

}
