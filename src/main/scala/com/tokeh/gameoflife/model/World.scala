package com.tokeh.gameoflife.model

trait World {
  def name: () => String
  def nextStateOfCell: (Grid, Int, Int) => Boolean
}
