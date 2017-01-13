package com.tokeh.gameoflife.view.gui

import java.util.{Timer, TimerTask}

import com.tokeh.gameoflife.controller.Controller

import scala.swing.{Reactor, TextField}
import scala.swing.event.ActionEvent


class StepNGenerationsReactor(val controller: Controller, val textField: TextField) extends Reactor {

  private var numberOfGenerations = 0
  private val GenerationsDelay = 100
  private var generationsTimer: Timer = null


  reactions += {
    case _:ActionEvent =>
      setNumberOfGenerations()
      stopIfRunning()
      startGenerationsTimerWithThread()
  }

  private def isGenerationsTimerRunning: Boolean = {
    generationsTimer != null
  }

  private def cancelGenerationsTimer(): Unit = {
    generationsTimer.cancel()
    generationsTimer = null
  }

  private def startGenerationsTimerWithThread(): Unit = {
    val generationsTask = new GenerationsTimerTask()
    generationsTimer = new Timer(true)
    generationsTimer.scheduleAtFixedRate(generationsTask, 0, GenerationsDelay)
  }

  class GenerationsTimerTask extends TimerTask {
    private var generationsCount = 0

    override def run(): Unit = {
      if (generationsCount >= numberOfGenerations) {
        cancelGenerationsTimer()
      } else {
        controller.stepOneGeneration()
        generationsCount = generationsCount + 1
      }
    }
  }

  def stopIfRunning(): Unit = {
    if (isGenerationsTimerRunning) {
      cancelGenerationsTimer()
    }
  }

  private def setNumberOfGenerations(): Unit = {
    try {
      numberOfGenerations = textField.text.toInt
    } catch {
      case _:Exception =>
    }
  }

}
