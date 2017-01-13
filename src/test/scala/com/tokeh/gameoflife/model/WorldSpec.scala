package com.tokeh.gameoflife.model

import com.tokeh.gameoflife.GameOfLife
import com.tokeh.gameoflife.model.impl.{GridBuilder, OriginalWorldRules}
import org.scalatest.{FeatureSpec, GivenWhenThen}

import scala.language.postfixOps

class WorldSpec extends FeatureSpec with GivenWhenThen {
  info("Each world has to provide two functions.")
  info("One function has to return the name of the world when invoked.")
  info("The second function has to calculate the new state of a given cell.")
  info("The new state of a cell is calculated based on the number of living neighbours.")
  info("Original World (23/3) dead cells with 2 or 3 living neighbours are brought to life.")
  info("Living cells with exactly 3 neighbours are killed.")

  feature("Name of world") {
    scenario("A world should have a name") {
      Given("a world with the name 'Original World (23/3)'")
        val world = new OriginalWorldRules
        assert(world.isInstanceOf[World])
        assert(world != null)

      When("the world's name is requested")
        val nameFunction = world.name

      Then("the world should return a function that gives out the world's name")
      assert(nameFunction.isInstanceOf[() => String])
      assert(nameFunction() equals "Original World (23/3)")
    }
  }

  feature("Calculation of cell state") {
    scenario("Calculation when all cells are dead") {
      Given("a world with a 2, 2 grid and all cells are dead")
        val worldRules = new GameOfLife with OriginalWorld
        var world = worldRules.rules
        val grid = createGrid(2, 2)

      When("the new state of these cells is calculated")
        val stateOfCellOne = world.nextStateOfCell(grid, 0, 0)
        val stateOfCellTwo = world.nextStateOfCell(grid, 0, 1)
        val stateOfCellThree = world.nextStateOfCell(grid, 1, 0)
        val stateOfCellFour = world.nextStateOfCell(grid, 1, 1)

      Then("the new state of that cell should be dead")
        assert(!stateOfCellOne)
        assert(!stateOfCellTwo)
        assert(!stateOfCellThree)
        assert(!stateOfCellFour)
    }

    scenario("Calculation when all cells are alive") {
      Given("a world with a 2, 2 grid and all cells are alive")
        val world: Rules = new OriginalWorldRules
        val newGrid = GridBuilder start(2, 2)

        newGrid(0)(0) = true
        newGrid(0)(1) = true
        newGrid(1)(0) = true
        newGrid(1)(1) = true

        val grid = newGrid.build

      When("the new state of these cells is calculated")
        val stateOfCellOne = world.nextStateOfCell(grid, 0, 0)
        val stateOfCellTwo = world.nextStateOfCell(grid, 0, 1)
        val stateOfCellThree = world.nextStateOfCell(grid, 1, 0)
        val stateOfCellFour = world.nextStateOfCell(grid, 1, 1)

      Then("the new state of that cell should be alive")
        assert(stateOfCellOne)
        assert(stateOfCellTwo)
        assert(stateOfCellThree)
        assert(stateOfCellFour)
    }
  }
}