package com.tokeh.gameoflife.view.gui

import java.util.{Observable, Observer}

import com.tokeh.gameoflife.controller.Controller

import scala.swing._

class StatusPanel(val controller: Controller) extends FlowPanel with Observer {

  private val status = new Label

  controller.addObserver(this)
  update(null, null)

  contents += status

  override def update(o: Observable, arg: scala.Any): Unit = {
    status.text = "Generation Strategy: " + controller.stepperName
  }
}
