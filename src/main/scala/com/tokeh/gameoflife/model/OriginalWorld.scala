package com.tokeh.gameoflife.model

import com.tokeh.gameoflife.model.impl.OriginalWorldRules

trait OriginalWorld extends World {
  override def rules = new OriginalWorldRules
}
