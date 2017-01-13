package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.{Grid, GridFactory}


class GridFactoryImpl extends GridFactory {
  override def buildGrid(cells: BitMatrix): Grid = {
    new GridImpl(cells)
  }
}
