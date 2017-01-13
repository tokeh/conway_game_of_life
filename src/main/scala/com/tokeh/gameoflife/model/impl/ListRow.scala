package com.tokeh.gameoflife.model.impl

class ListRow() {
  private var fieldList: List[Field] = List[Field]()

  def addField(field: Field): Unit = fieldList = fieldList :+ field
  def getList: List[Field] = fieldList

  def step(grid: NewGrid, f: (NewGrid, Field) => Field): ListRow = {
    val list = new ListRow

    for (field <- fieldList) {
      list.addField(f(grid, field))
    }
    list
  }
}
