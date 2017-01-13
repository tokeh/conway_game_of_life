package com.tokeh.gameoflife.view.gui

import com.tokeh.gameoflife.controller.Controller

import scala.swing.MenuBar
import scala.swing.Menu


class MyMenuBar(val controller: Controller, val gridPanel: GridDrawingPanel) extends MenuBar {

  private val TitleFigures = "Figures"

  private val figureMenuBuilder = new FigureMenuBuilder(controller, gridPanel)

  contents += {
    new Menu(TitleFigures) {
      figureMenuBuilder.buildFiguresMenu foreach (contents += _)
    }
  }


}
