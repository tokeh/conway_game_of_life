package com.tokeh.gameoflife.model

import com.tokeh.gameoflife.model.impl.BitMatrix

trait GridFactory {
  def buildGrid(cells: BitMatrix): Grid
}
