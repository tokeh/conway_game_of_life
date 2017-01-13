package com.tokeh.gameoflife

import com.tokeh.gameoflife.model.impl.OriginalWorldNormal

trait OriginalWorldRules extends Rules {
  override def world = new OriginalWorldNormal
}
