package com.tokeh.gameoflife.controller.impl

import java.util.{Observable, Observer}

import com.tokeh.gameoflife.controller.Controller
import com.tokeh.gameoflife.model.OriginalWorld
import com.tokeh.gameoflife.model.impl.FiguresRegistry
import org.scalatest.{FlatSpec, Matchers}

class GridControllerImplSpec extends FlatSpec with Matchers {

  "Modifying state" should "trigger an update to all observers" in {
    testObserverCalled((controller: Controller) => controller.stepOneGeneration())
    testObserverCalled((controller: Controller) => controller.spawnFigure(FiguresRegistry.get("Glider").get, 0, 0))
    testObserverCalled((controller: Controller) => controller.killCells())
    testObserverCalled((controller: Controller) => controller.setGridSize(2, 2))
  }

  def testObserverCalled(command: Controller => Unit): Unit = {
    val controller = new ControllerImpl()
    val world = new OriginalWorld{}
    controller.configureStepper(world.rules, world.name)
    var observerCalled = false

    controller.addObserver(new Observer {
      override def update(o: Observable, arg: scala.Any): Unit = {
        observerCalled = true
      }
    })

    command(controller)

    observerCalled should be(true)
  }

  "Getters and Setters" should "return correct values" in {
    val controller = new ControllerImpl()
    val world = new OriginalWorld{}
    controller.configureStepper(world.rules, world.name)
    controller.gameIsRunning should be(true)
    controller.quitGame()
    controller.gameIsRunning should be(false)

    controller.stepperName shouldNot be(empty)

    controller.stepOneGeneration()
    controller.steppedGenerations should be(1)

    controller.setCellLiving(0, 0)
    controller.grid.get(0, 0) should be(true)

    controller.setCellDead(0, 0)
    controller.grid.get(0, 0) should be(false)
  }

}
