package com.tokeh.gameoflife.model.impl

import com.google.inject.Guice
import com.tokeh.gameoflife.BaseModule
import com.tokeh.gameoflife.model.{Grid, GridFactory}

object GridBuilder {
  private val injector = Guice.createInjector(new BaseModule)

  def start(rows: Int, columns: Int): Constructable = new Constructable(rows, columns)

  def start(grid: Grid): Constructable = start(grid.numberOfRows, grid.numberOfColumns)

  def copy(grid: Grid): Constructable = copy(grid, grid.numberOfRows, grid.numberOfColumns)

  def copy(grid: Grid, newRows: Int, newColumns: Int): Constructable = {
    val newGrid = start(newRows, newColumns)
    val copyRowsUntil = math.min(newRows, grid.numberOfRows)
    val copyColumnsUntil = math.min(newColumns, grid.numberOfColumns)

    for (i <- 0 until copyRowsUntil) {
      for (j <- 0 until copyColumnsUntil) {
        newGrid(i)(j) = grid(i)(j)
      }
    }
    newGrid
  }

  class Constructable(gridRows: Int, gridColumns: Int) {

    private val cells = new BitMatrix(gridRows, gridColumns)

    def rows: Int = cells.rows

    def columns: Int = cells.columns

    def apply(row: Int): ConstructableRow = new ConstructableRow(row)

    def build: Grid = {
      import net.codingwell.scalaguice.InjectorExtensions._
      val factory = injector.instance[GridFactory]
      factory.buildGrid(cells)
    }

    def set(row: Int, column: Int, value: Boolean): Unit = {
      cells.set(row, column, value)
    }

    class ConstructableRow(val row: Int) {
      def apply(column: Int): Boolean = cells(row)(column)

      def update(column: Int, value: Boolean) : Unit = cells(row)(column) = value
    }

  }
}
