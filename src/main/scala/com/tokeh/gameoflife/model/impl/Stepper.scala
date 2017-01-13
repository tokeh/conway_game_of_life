package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.Grid

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object Stepper {
  private var nameFunction: () => String = _
  private var average: Long = 0
  private var times = 0

  def step(grid: Grid, nextStateOfCell: (Grid, Int, Int) => Boolean): Grid = {

    var futures = Vector[Future[Unit]]()
    val newGrid = GridBuilder start grid

    val start = System.nanoTime()

    for (row <- 0 until grid.numberOfRows) {
      for (column <- 0 until grid.numberOfColumns) {
        futures = futures :+ Future { newGrid.set(row, column, nextStateOfCell(grid, row, column)) }
        //newGrid.set(row, column, nextStateOfCell(grid, row, column))
      }
    }

    Await.ready(Future.sequence(futures), Duration.Inf)
    println(futures.length)

    // 20 x 20, 500mal, ohne: 272ms, mit: 1481ms
    // 50 x 50, 500mal, ohne: 1387ms, mit: 5639ms
    // 80 x 80, 500mal, ohne: 2834ms, mit: 894ms

    val end = System.nanoTime()
    average = ((average * times) + ((end - start) / 1000)) / (times + 1)
    times += 1
    println("Average: " + average + " ms, times: " + times + ", current time: " + ((end - start) / 1000))

    newGrid.build
  }

  def name: String = {
    nameFunction()
  }

  def name_(nameFunc:() => String): Unit = nameFunction = nameFunc
}
