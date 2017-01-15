package com.tokeh.gameoflife.model

import com.tokeh.gameoflife.model.impl.FiguresRegistry
import org.scalatest.{FlatSpec, Matchers}

class FiguresSpec extends FlatSpec with Matchers {

  "A GliderFigure" should "have the name \"Glider\"" in {
    FiguresRegistry.get("Glider").get.name should be("Glider")
  }

  it should "have correct coordinates" in {
    val expectedCoords = List(
      (0, 1),
      (1, 2),
      (2, 0),
      (2, 1),
      (2, 2)
    )

    FiguresRegistry.get("Glider").get.coordinates should be(expectedCoords)
  }

  "A RPentominoFigure" should "have the name \"r-Pentomino\"" in {
    FiguresRegistry.get("r-Pentomino").get.name should be("r-Pentomino")
  }

  it should "have correct coordinates" in {
    val expectedCoords = List(
      (0, 1),
      (0, 2),
      (1, 0),
      (1, 1),
      (2, 1)
    )

    FiguresRegistry.get("r-Pentomino").get.coordinates should be(expectedCoords)
  }

  "Figures Registry" should "return a list of 2 figures" in {
    val listIt = FiguresRegistry.all
    val list:List[Figure] = listIt.toList
    list.size should be(2)
  }
}
