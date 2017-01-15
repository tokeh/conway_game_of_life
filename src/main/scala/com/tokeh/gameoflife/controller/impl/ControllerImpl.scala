package com.tokeh.gameoflife.controller.impl

import com.tokeh.gameoflife.controller.Controller
import com.tokeh.gameoflife.model
import com.tokeh.gameoflife.model.impl.Stepper
import com.tokeh.gameoflife.model.{Figure, _}

class ControllerImpl extends Controller {

  var grid: Grid = createGrid(10, 20)
  var steppedGenerations = 0
  var gameIsRunning = true
  var rules: Rules = _

  def configureStepper(rulesFunction: Rules, name: String): Unit = {
    rules = rulesFunction
    Stepper.name = name
  }

  override def setGridSize(rows: Int, columns: Int): Unit = {
    grid = copyGridAsConstructible(grid, rows, columns).build
    setChangedAndNotify()
  }

  override def stepOneGeneration(): Unit = {
    grid = model.stepOneGeneration(grid, rules.nextStateOfCell, Stepper.step)
    steppedGenerations += 1
    setChangedAndNotify()
  }

  override def setCellLiving(row: Int, column: Int): Unit = {
    grid = changeCell(grid, row, column, value = true)
    setChangedAndNotify()
  }

  override def setCellDead(row: Int, column: Int): Unit = {
    grid = changeCell(grid, row, column, value = false)
    setChangedAndNotify()
  }

  override def killCells(): Unit = {
    grid = model killAllCells grid
    setChangedAndNotify()
  }

  override def spawnFigure(figure: Figure, row: Int, column: Int): Unit = {
    grid = model.spawnFigure(grid, figure.coordinates, row, column)
    setChangedAndNotify()
  }

  override def stepperName: String = Stepper.name

  private def setChangedAndNotify(): Unit = {
    setChanged()
    notifyObservers()
  }

  override def quitGame(): Unit = gameIsRunning = false
}
