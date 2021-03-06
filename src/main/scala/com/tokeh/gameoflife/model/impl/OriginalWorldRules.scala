package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.{Grid, Rules}

class OriginalWorldRules() extends Rules {

  override def nextStateOfCell: (Grid, Int, Int) => Boolean = {
    (grid: Grid, row: Int, column: Int) => {

      if (grid.get(row, column)) {
        aliveRule(numberOfLivingNeighbours(grid, row, column))
      } else {
        deadRule(numberOfLivingNeighbours(grid, row, column))
      }
    }
  }

  private def aliveRule: (Int) => Boolean = {
    (livingNeighbours: Int) => livingNeighbours == 2 || livingNeighbours == 3
  }

  private def deadRule: (Int) => Boolean = {
    (livingNeighbours: Int) => livingNeighbours == 3
  }

  private def numberOfLivingNeighbours: (Grid, Int, Int) => Int = {
    (grid: Grid, row: Int, column: Int) => {
      var livingNeighbors = 0

      for (i <- row - 1 to row + 1; if isRowIndexInBound(i, grid)) {
        livingNeighbors += countLivingNeighborsInRow(grid, i, row, column)
      }

      livingNeighbors}
  }

  private def countLivingNeighborsInRow(grid: Grid, currentRow: Int, cellRow: Int, cellColumn: Int): Int = {
    var livingNeighborsInRow = 0

    for (j <- cellColumn - 1 to cellColumn + 1
         if isColumnIndexInBound(j, grid)
           && (currentRow != cellRow || j != cellColumn)
           && grid.get(currentRow, j)) {
      livingNeighborsInRow += 1
    }

    livingNeighborsInRow
  }

  private def isRowIndexInBound(i: Int, grid: Grid): Boolean = i >= 0 && i < grid.numberOfRows

  private def isColumnIndexInBound(j: Int, grid: Grid): Boolean = j >= 0 && j < grid.numberOfColumns
}
