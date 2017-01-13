package com.tokeh.gameoflife

import com.tokeh.gameoflife.model.OriginalWorld

object Runner extends App {
  val game = new GameOfLife with OriginalWorld
  game.run()
}
