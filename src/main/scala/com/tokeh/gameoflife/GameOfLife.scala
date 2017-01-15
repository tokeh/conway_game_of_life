package com.tokeh.gameoflife

import com.google.inject.Guice
import com.tokeh.gameoflife.controller.Controller
import com.tokeh.gameoflife.model.World
import com.tokeh.gameoflife.view.gui.SwingView
import com.tokeh.gameoflife.view.text.TextView

class GameOfLife {
  world: World =>

  def run(): Unit = {

    val injector = Guice.createInjector(new BaseModule)
    import net.codingwell.scalaguice.InjectorExtensions._
    val controller = injector.instance[Controller]

    setStepperRules(controller)
    val textView = new TextView(controller)
    val swingView = new SwingView(controller)
    textView.readAndInterpretInLoopFromInputStream()
  }

  private def setStepperRules(controller: Controller) = {
    controller.configureStepper(world.rules, world.name)
  }
}
