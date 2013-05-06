package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

class RichPoint(val underlying: Point) extends AnyVal {

  def +(p: Point) =
    Point(underlying.x + p.x, underlying.y + p.y)

  def +(p: (Int, Int)) = p match {
    case (x, y) => Point(underlying.x + x, underlying.y + y)
  }

  def neighbors = List (
     underlying + (-1, 0)
    ,underlying + (0, -1)
    ,underlying + (0, 1)
    ,underlying + (1, 0)
  )
}
