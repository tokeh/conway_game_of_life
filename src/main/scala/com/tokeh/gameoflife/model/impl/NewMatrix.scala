package com.tokeh.gameoflife.model.impl

class NewMatrix(rows: Int, columns: Int) {

  private var listMatrix = List[ListRow]()
  private val isAlive = false

  def numberOfRows: Int = rows
  def numberOfColumns: Int = columns

  require(rows >= 1 && columns >= 1)

  for(i <- 0 to rows) {
    val rowList = new ListRow()

    for (j <- 0 to columns) {
      rowList addField Field(i, j, isAlive)
    }

    listMatrix = listMatrix :+ rowList
  }

  def getValue(row: Int, column: Int): Boolean = listMatrix(row).getList(column).isAlive

  def addRow(row: ListRow): Unit = listMatrix = listMatrix :+ row

  def matrixAsLists: List[ListRow] = listMatrix

  def step(grid: NewGrid, f: (NewGrid, Field) => Field): NewMatrix = {
    val matrix = new NewMatrix(rows, columns)

    for (row: ListRow <- matrixAsLists) {
      matrix.addRow(row.step(grid, f))
    }

    matrix
  }
}
