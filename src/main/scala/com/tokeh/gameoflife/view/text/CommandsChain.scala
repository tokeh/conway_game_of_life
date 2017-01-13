package com.tokeh.gameoflife.view.text

import com.tokeh.gameoflife.controller.Controller

class CommandsChain {

  private val delayBetweenFramesMs = 100

  def getAllCommandDescriptions: List[String] = {
    "q: quit game" ::
      "c: clear" ::
      "s [x] [y]: set grid size" ::
      "n: step 1 generation" ::
      "n [x]: step x generations" ::
      "t [x] [y]: toggle cell" ::
      Nil
  }


  def handle(textView: TextView, cmd: TextCommand): Unit = {
    val controller = textView.controller

    cmd match {
      case TextCommand("q", Array()) => textView.quit()
      case TextCommand("c", Array()) => controller.killCells()
      case TextCommand("s", Array(_, _)) => controller.setGridSize(toInt(cmd.args(0)), toInt(cmd.args(1)))
      case TextCommand("n", Array()) => controller.stepOneGeneration()
      case TextCommand("n", Array(_)) => stepNGenerations(controller, cmd)
      case TextCommand("t", Array(_, _)) => toggleCell(controller, cmd)
    }
  }

  private def stepNGenerations(controller: Controller, cmd: TextCommand): Unit = {
    val frames = toInt(cmd.args(0))
    for (i <- 0 until frames) {
      controller.stepOneGeneration()
      sleepBetweenGenerations()
    }
  }

  private def sleepBetweenGenerations(): Unit = {
    try {
      Thread.sleep(delayBetweenFramesMs)
    } catch {
      case e: InterruptedException => Thread.currentThread().interrupt()
    }
  }

  private def toggleCell(controller: Controller, cmd: TextCommand): Unit = {
    val i = toInt(cmd.args(0))
    val j = toInt(cmd.args(1))

    if (controller.grid(i)(j)) {
      controller.setCellDead(i, j)
    } else {
      controller.setCellLiving(i, j)
    }
  }

  private def toInt(number: String): Int = {
    try {
      number.toInt
    } catch {
      case e: Exception => -1
    }
  }


}
