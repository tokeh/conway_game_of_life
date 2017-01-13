package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.Figure
import com.tokeh.gameoflife.model.impl.FigureDSL._

object FiguresRegistry {

  private val figures = Map[String, Figure](
    ("Glider", "Glider" withCoordinates (0,1) :: (1,2) :: (2,0) :: (2,1) :: (2,2) :: Nil),
    ("r-Pentomino", "r-Pentomino" withCoordinates (0,1) :: (0,2) :: (1,0) :: (1,1) :: (2,1) :: Nil)
  )

  def all: Iterable[Figure] = figures.values

  def get(name: String): Option[Figure] = figures.get(name)
}
