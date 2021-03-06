package com.tokeh.gameoflife.model

import com.tokeh.gameoflife.model.impl.FiguresRegistry
import org.scalatest.{FlatSpec, Matchers}
class FigureSpawnSpec extends FlatSpec with Matchers {

  "spawnFigure(1,1)" should "spawn a figure at coordinates (1,1)" in {
    val grid = createGrid(10, 10)
    val figure = FiguresRegistry.get("Glider").get

    spawnFigure(grid, figure.coordinates, 1, 1)

    for (coord <- figure.coordinates) (i: Int, j: Int) =>
      grid(i + 1)(j + 1) should be(true)
  }
}