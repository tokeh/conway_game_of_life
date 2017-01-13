package com.tokeh.gameoflife.model

trait World {
  def rules: Rules
  def run()
}
