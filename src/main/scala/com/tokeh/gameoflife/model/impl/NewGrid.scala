package com.tokeh.gameoflife.model.impl

class NewGrid(rows: Int, columns: Int) {
  var listMatrix = new NewMatrix(rows, columns)

  def numberOfRows: Int = rows

  def numberOfColumns: Int = columns

  def isRowIndexInBound(i: Int): Boolean = i >= 0 && i < rows

  def isColumnIndexInBound(j: Int): Boolean = j >= 0 && j < columns

  def step(grid: NewGrid, f: (NewGrid, Field) => Field): NewGrid = {
    val newMatrix = listMatrix.step(grid, f)

    grid.listMatrix = newMatrix
    grid
  }
}
