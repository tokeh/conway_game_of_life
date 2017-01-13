package com.tokeh.gameoflife.model

import com.tokeh.gameoflife.UnitSpec
import com.tokeh.gameoflife.model.impl.BitMatrix

class BitMatrixSpec extends UnitSpec {
  val matrix = new BitMatrix(2, 2)

  "A BitMatrix" should "have only false cells" in {
    for (i <- 0 until matrix.rows) {
      for (j <- 0 until matrix.columns) {
        matrix(i)(j) should be(false)
      }
    }
  }

  it should "be able to have false and true cells" in {
    matrix(0)(0) = true
    matrix(0)(0) should be(true)
    matrix(0)(0) = false
    matrix(0)(0) should be(false)
  }
}