package com.tokeh.gameoflife.model

trait Rules {
  def name: () => String
  def nextStateOfCell: (Grid, Int, Int) => Boolean
}
