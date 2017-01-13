package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.Grid

case class GridImpl(cellsState: BitMatrix) extends Grid {
  private val cells = new BitMatrix(cellsState.rows, cellsState.columns)

  for (i <- 0 until cells.rows) {
    for (j <- 0 until cells.columns) {
      cells(i)(j) = cellsState(i)(j)
    }
  }

  private def isRowIndexInBound(i: Int): Boolean = i >= 0 && i < cellsState.rows

  private def isColumnIndexInBound(j: Int): Boolean = j >= 0 && j < cellsState.columns

  override def numberOfRows: Int = cells.rows

  override def numberOfColumns: Int = cells.columns

  override def apply(row: Int): GridRow = new GridRowImpl(row)

  override def get(row: Int, column: Int): Boolean = cells.get(row, column)

  class GridRowImpl(val row: Int) extends GridRow {
    def apply(column: Int): Boolean = cells(row)(column)
  }
}
