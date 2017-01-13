package com.tokeh.gameoflife

import com.tokeh.gameoflife.model.World

trait Rules {
  def world: World
  def run()
}
