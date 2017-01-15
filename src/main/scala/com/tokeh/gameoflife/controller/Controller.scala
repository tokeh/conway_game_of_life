package com.tokeh.gameoflife.controller

import java.util.Observable

import com.tokeh.gameoflife.model.{Figure, Grid, Rules}

trait Controller extends Observable {

  def quitGame(): Unit

  def grid: Grid

  def gameIsRunning: Boolean

  def killCells(): Unit

  def setGridSize(rows: Int, columns: Int): Unit

  def stepOneGeneration(): Unit

  def setCellLiving(row: Int, column: Int): Unit

  def setCellDead(row: Int, column: Int): Unit

  def spawnFigure(figure: Figure, row: Int, column: Int): Unit

  def steppedGenerations: Int

  def stepperName: String

  def configureStepper(worldParam: Rules, name: String): Unit
}
