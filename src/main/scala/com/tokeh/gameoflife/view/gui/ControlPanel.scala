package com.tokeh.gameoflife.view.gui

import java.util.{Observable, Observer}

import com.tokeh.gameoflife.controller.Controller

import scala.swing._
import scala.swing.event.ActionEvent


class ControlPanel(val controller: Controller) extends FlowPanel with Observer {

  private val StepNGenerationsTextFieldColumns = 3

  private val stepNGenerationsBtn = new Button("step >")
  private val stopStepNGenerationsBtn = new Button("stop")
  private val setGridSizeLabel = new Label("grid size: ")
  private val resetGridBtn = new Button("clear")
  private val stepNGenerationsField = new TextField(String.valueOf(1), StepNGenerationsTextFieldColumns)
  private val rowsField = new TextField(2)
  private val columnsField = new TextField(2)
  private val stepNGenerationsReactor = new StepNGenerationsReactor(controller, stepNGenerationsField)
  private val resetGridReactor = new Reactor {
    reactions += {
      case _:ActionEvent => controller.killCells()
    }
  }
  private val stopNGenerationsReactor =new Reactor {
    reactions += {
      case _:ActionEvent => stepNGenerationsReactor.stopIfRunning()
    }
  }
  private val rowsFieldReactor = new SetGridSizeReactor(rowsField, columnsField, controller)
  private val columnsFieldReactor = new SetGridSizeReactor(rowsField, columnsField, controller)

  rowsFieldReactor.listenTo(rowsField)
  columnsFieldReactor.listenTo(columnsField)
  resetGridReactor.listenTo(resetGridBtn)
  stepNGenerationsReactor.listenTo(stepNGenerationsBtn)
  stopNGenerationsReactor.listenTo(stopStepNGenerationsBtn)

  contents += resetGridBtn
  contents += stepNGenerationsBtn
  contents += stepNGenerationsField
  contents += stopStepNGenerationsBtn
  contents += setGridSizeLabel
  contents += rowsField
  contents += new Label("x")
  contents += columnsField

  controller.addObserver(this)
  updateRowsAndColumnsFields()

  override def update(o: Observable, arg: scala.Any): Unit = {
    updateRowsAndColumnsFields()
  }

  private def updateRowsAndColumnsFields(): Unit = {
    val rowsText = String.valueOf(controller.grid.numberOfRows)
    val columnsText = String.valueOf(controller.grid.numberOfColumns)
    rowsField.text = rowsText
    columnsField.text = columnsText
  }
}
