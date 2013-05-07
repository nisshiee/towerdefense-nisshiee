package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

class RichField(val underlying: Field) extends AnyVal {

  import RichField._

  def start =
    underlying._tiles collectFirst startPoint getOrElse Point(0, 0)

  def goal =
    underlying._tiles collectFirst goalPoint getOrElse defaultGoal(underlying)
}

object RichField {

  val startPoint: PartialFunction[(Point, Tile), Point] = {
    case (p, Start) => p
  }

  val goalPoint: PartialFunction[(Point, Tile), Point] = {
    case (p, Goal) => p
  }

  def defaultGoal(f: Field) =
    Point(f.width - 1, f.height - 1)
}
