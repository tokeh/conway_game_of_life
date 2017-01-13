package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.Figure

object FigureDSL {

  implicit class FigureSpec(val figureName: String) {
    def withCoordinates(figureCoordinates: List[(Int, Int)]): Figure = {
      new Figure {
        override def coordinates: List[(Int, Int)] = figureCoordinates
        override def name: String = figureName
      }
    }
  }
}

