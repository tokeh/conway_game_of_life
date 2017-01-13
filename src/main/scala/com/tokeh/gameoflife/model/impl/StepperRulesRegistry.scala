package com.tokeh.gameoflife.model.impl

import com.tokeh.gameoflife.model.World

object StepperRulesRegistry {

  val map: Map[String, World] = Map[String, World](("Original World (23/3)", OriginalWorldNormal()))
}
