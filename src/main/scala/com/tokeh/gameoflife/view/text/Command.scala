package com.tokeh.gameoflife.view.text

abstract class Command {
  def handle(textView: TextView, cmd: TextCommand): Unit
}
