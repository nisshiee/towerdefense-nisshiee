package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

class RichField(val underlying: Field) extends AnyVal {

  import RichField._

  def start =
    underlying._tiles collectFirst startPoint getOrElse Point(0, 0)

  def goal =
    underlying._tiles collectFirst goalPoint getOrElse Point(19, 9)
}

object RichField {

  val startPoint: PartialFunction[(Point, Tile), Point] = {
    case (p, Start) => p
  }

  val goalPoint: PartialFunction[(Point, Tile), Point] = {
    case (p, Goal) => p
  }
}
