package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.World

case class OriginalWorld() {
  def name: String = "Original World (23/3)"

  def nextStateOfCell: (NewGrid, Field) => Field = {
    (grid: NewGrid, field: Field) => {

      if (field.isAlive) {
        Field(field.row, field.column, livingRule(numberOfLivingNeighbours(grid, field)))
      } else {
        Field(field.row, field.column, killingRule(numberOfLivingNeighbours(grid, field)))
      }
    }
  }

  private def livingRule: (Int) => Boolean = {
    (livingNeighbours: Int) => livingNeighbours == 2 || livingNeighbours == 3
  }

  private def killingRule: (Int) => Boolean = {
    (livingNeighbours: Int) => livingNeighbours == 3
  }

  private def numberOfLivingNeighbours: (NewGrid, Field) => Int = {
    (grid: NewGrid, field: Field) => {
      var livingNeighbors = 0

      for (i <- field.row - 1 to field.row + 1; if grid.isRowIndexInBound(i)) {
        livingNeighbors += countLivingNeighborsInRow(grid, i, field.row, field.column)
      }

      livingNeighbors}
  }

  private def countLivingNeighborsInRow(grid: NewGrid, currentRow: Int, cellRow: Int, cellColumn: Int): Int = {
    var livingNeighborsInRow = 0

    for (j <- cellColumn - 1 to cellColumn + 1
         if grid.isColumnIndexInBound(j)
           && (currentRow != cellRow || j != cellColumn)
           && grid.listMatrix.getValue(currentRow, j)) {
      livingNeighborsInRow += 1
    }

    livingNeighborsInRow
  }
}
