package com.tokeh.gameoflife.model

import com.tokeh.gameoflife.model.impl.OriginalWorldRules

trait OriginalWorld extends World {
  override def name = "Original World (23/3)"
  override def rules = new OriginalWorldRules
}
