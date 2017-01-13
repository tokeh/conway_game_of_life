package com.tokeh.gameoflife

import com.tokeh.gameoflife.model.impl.GridBuilder

package object model {
  def createGrid(rows: Int, columns: Int): Grid = GridBuilder.start(rows, columns).build

  def copyGridAsConstructible(grid: Grid): GridBuilder.Constructable = GridBuilder.copy(grid)

  def copyGridAsConstructible(grid: Grid, newRows: Int, newColumns: Int): GridBuilder.Constructable = {
    GridBuilder.copy(grid, newRows, newColumns)
  }

  def stepOneGeneration(grid: Grid, nextState: (Grid, Int, Int) => Boolean, stepper: (Grid, (Grid, Int, Int) => Boolean) => Grid): Grid = {
    stepper(grid, nextState)
  }

  def changeCell(grid: Grid, row: Int, column: Int, value: Boolean): Grid = {
    val changedGrid = copyGridAsConstructible(grid)
    changedGrid(row)(column) = value
    changedGrid.build
  }

  def killAllCells(grid: Grid): Grid = GridBuilder.start(grid).build

  def spawnFigure(grid: Grid, figureCoords: List[(Int, Int)], row: Int, column: Int): Grid = {
    val newGrid = copyGridAsConstructible(grid)
    // clear first position
    newGrid(row)(column) =  false
    // coordinates
    for (coord <- figureCoords) {
      val i = coord._1
      val j = coord._2
      newGrid(row + i)(column + j) = true
    }
    newGrid.build
  }

}