package com.tokeh.gameoflife.view.gui

import java.util.{Observable, Observer}

import com.tokeh.gameoflife.controller.Controller

import scala.swing.event.{MouseDragged, MouseEvent, MouseReleased}
import scala.swing.{Color, Dimension, _}


class GridDrawingPanel(val controller: Controller, val onResize: () => Unit) extends Panel with Observer {

  val CellSize = 30

  private val livingCellColor = new Color(0, 176, 255)
  private val cellBorderColor = new Color(40, 40, 40)
  private var graphics: Graphics2D = _
  private val BorderWith = 1

  background = new Color(0, 0, 0)

  controller.addObserver(this)
  listenTo(mouse.clicks)
  listenTo(mouse.moves)

  reactions += {
    case e: MouseReleased => livingCellReaction(e)
    case e: MouseDragged => livingCellReaction(e)
  }

  setSizeAndRepaint()

  private def livingCellReaction(e: MouseEvent): Unit = {
    val row = e.point.y / CellSize
    val column = e.point.x / CellSize
    controller.setCellLiving(row, column)
  }

  def setSizeAndRepaint(): Unit = {
    preferredSize = new Dimension(controller.grid.numberOfColumns * CellSize, controller.grid.numberOfRows * CellSize)
    repaint()
    onResize()
  }

  override def update(o: Observable, arg: scala.Any): Unit = {
    setSizeAndRepaint()
  }

  override def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    graphics = g
    drawGrid()
  }

  private def drawGrid(): Unit = {
    val grid = controller.grid

    var x = 0
    var y = 0

    for (i <- 0 until grid.numberOfRows) {
      x = 0
      for (j <- 0 until grid.numberOfColumns) {
        drawCell(x, y, grid(i)(j))
        x += CellSize
      }
      y += CellSize
    }
  }

  private def drawCell(x: Int, y: Int, alive: Boolean): Unit = {
    drawCellBorder(x, y)

    if (alive) {
      drawLivingCell(x, y)
    }
  }

  private def drawCellBorder(x: Int, y: Int): Unit = {
    graphics.setColor(cellBorderColor)
    graphics.drawRect(x, y, CellSize, CellSize)
  }

  private def drawLivingCell(x: Int, y: Int): Unit = {
    graphics.setColor(livingCellColor)
    val startX = x + BorderWith
    val startY = y + BorderWith
    val size = CellSize - BorderWith
    graphics.fillRect(startX, startY, size, size)
  }

}
