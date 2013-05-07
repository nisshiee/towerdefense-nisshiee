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

  def farNeighbors(goal: Point) = {
    import scala.math._
    val xdiff = goal.x - underlying.x
    val ydiff = goal.y - underlying.y
    val absdiff = abs(xdiff) - abs(ydiff)
    val steps: List[(Int, Int)] = (xdiff >= 0, ydiff >= 0, absdiff >= 0) match {
      case (true, true, true) =>
        List((-1, 0), (0, -1), (0, 1), (1, 0))
      case (true, true, false) =>
        List((0, -1), (-1, 0), (1, 0), (0, 1))
      case (true, false, true) =>
        List((-1, 0), (0, 1), (0, -1), (1, 0))
      case (true, false, false) =>
        List((0, 1), (-1, 0), (1, 0), (0, -1))
      case (false, true, true) =>
        List((1, 0), (0, -1), (0, 1), (-1, 0))
      case (false, true, false) =>
        List((0, -1), (1, 0), (-1, 0), (0, 1))
      case (false, false, true) =>
        List((1, 0), (0, 1), (0, -1), (-1, 0))
      case (false, false, false) =>
        List((0, 1), (1, 0), (-1, 0), (0, -1))
    }
    steps map (underlying +)
  }
}
