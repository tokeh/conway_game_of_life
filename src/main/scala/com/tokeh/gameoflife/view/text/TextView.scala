package com.tokeh.gameoflife.view.text

import java.util.{Observable, Observer}

import com.tokeh.gameoflife.controller.Controller

class TextView(val controller: Controller) extends Observer {

  private val scanner = new java.util.Scanner(System.in)
  private val commandsChain = new CommandsChain

  controller.addObserver(this)
  redraw()

  def readAndInterpretInLoopFromInputStream(): Unit = {
    while (controller.gameIsRunning) {
      readAndInterpretFromInputStream()
    }
  }

  def readAndInterpretFromInputStream(): Unit = {
    if (scanner.hasNextLine) {
      val line = scanner.nextLine()
      interpretLine(line)
    }
  }

  def readAndInterpretFromArgument(argument: String): Unit = {
    interpretLine(argument)
  }

  private def interpretLine(line: String): Unit = {
    val trimmed = line.trim
    val cmd = if (trimmed.isEmpty) "" else trimmed.split(" ")(0)
    val args: Array[String] = if (trimmed.isEmpty) Array[String]()
    else for (a <- trimmed.split(" ").drop(1)) yield a

    commandsChain.handle(this, TextCommand(cmd, args))
  }


  def quit(): Unit = controller.quitGame()

  override def update(o: Observable, arg: scala.Any): Unit = redraw()


  private def redraw(): Unit = {
    val grid = controller.grid

    drawLineBreak()
    drawGenerationStrategy()
    drawLineBreak()
    drawHorizontalBorder()

    for (i <- 0 until grid.numberOfRows) {
      drawVerticalBorder()
      for (j <- 0 until grid.numberOfColumns) {
        drawCell(grid(i)(j))
      }
      drawVerticalBorder()
      drawLineBreak()
    }

    drawHorizontalBorder()
    drawLineBreak()
    drawAvailableCommands()
  }

  private def drawVerticalBorder(): Unit = writeOut("|")

  private def drawHorizontalBorder(): Unit = {
    val length = controller.grid.numberOfColumns
    drawVerticalBorder()
    for (i <- 0 until length) {
      writeOut("-")
    }
    drawVerticalBorder()
    drawLineBreak()
  }

  private def drawAvailableCommands(): Unit = {
    writeOut("Commands: ")
    drawLineBreak()
    commandsChain.getAllCommandDescriptions foreach(cmd => {
      writeOut(cmd)
      drawLineBreak()
    })
  }

  private def drawGenerationStrategy(): Unit = {
    writeOut("Generation World: ")
    writeOut(controller.stepperName)
  }

  private def drawCell(alive: Boolean): Unit = writeOut(if (alive) "#" else " ")

  private def writeOut(text: String): Unit = print(text)

  private def drawLineBreak(): Unit = writeOut(System.lineSeparator)

}
