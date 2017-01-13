package com.tokeh.gameoflife.view.gui

import com.tokeh.gameoflife.controller.Controller
import com.tokeh.gameoflife.model.impl.FiguresRegistry

import scala.collection.mutable.ListBuffer
import scala.swing.MenuItem
import scala.swing.event.ActionEvent

class FigureMenuBuilder(val controller: Controller, val gridPanel: GridDrawingPanel) {

  def buildFiguresMenu(): List[MenuItem] = {
    var result = new ListBuffer[MenuItem]

    for (figure <- FiguresRegistry.all) {
      val item = new MenuItem(figure.name)
      item.reactions += {
        case _:ActionEvent =>
          new FigureSpawnOneTimeReactor(controller, gridPanel, figure)
      }
      item.listenTo(item)
      result += item
    }

    result.toList
  }

}
