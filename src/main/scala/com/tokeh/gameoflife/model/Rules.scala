package com.tokeh.gameoflife.model

trait Rules {
  def nextStateOfCell: (Grid, Int, Int) => Boolean
}
