package com.tokeh.gameoflife.view.gui

import com.tokeh.gameoflife.controller.Controller

import scala.swing._

class SetGridSizeReactor(val rowsField: TextField, val columnsField: TextField,
                         val controller: Controller) extends Reactor {


  reactions += {
    case _:event.EditDone =>
      val rows = getCheckedNumberOfField(rowsField)
      val columns = getCheckedNumberOfField(columnsField)
      controller.setGridSize(rows, columns)
  }

  private def getCheckedNumberOfField(field: TextField): Int = {
    try {
      field.text.toInt
    } catch {
      case e: Exception => 1
    }
  }

}
