package com.tokeh.gameoflife

object Runner extends App {
  val game = new GameOfLife with OriginalWorldRules
  game.run()
}
