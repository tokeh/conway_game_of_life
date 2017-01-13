package com.tokeh.gameoflife.view.gui

import com.tokeh.gameoflife.controller.Controller

import scala.swing.{BorderPanel, Frame, Reactor}

class SwingView(val controller: Controller) extends Frame with Reactor {

  private val gridPanel = new GridDrawingPanel(controller, () => this.pack())
  private val controlPanel = new ControlPanel(controller)
  private val statusPanel = new StatusPanel(controller)

  menuBar = new MyMenuBar(controller, gridPanel)
  resizable = false
  visible = true
  title = "Game of Life"

  contents = new BorderPanel() {
    add(controlPanel, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(statusPanel, BorderPanel.Position.South)
  }

  peer.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE)
  pack()
}
