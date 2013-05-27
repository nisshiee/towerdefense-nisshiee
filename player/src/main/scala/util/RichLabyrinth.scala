package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

class RichLabyrinth(val self: Labyrinth) extends AnyVal {

  def targetSize(p: Point): Int = List (
     Point(-1, -1)
    ,Point(0, -1)
    ,Point(1, -1)
    ,Point(-1, 0)
    ,Point(1, 0)
    ,Point(-1, 1)
    ,Point(0, 1)
    ,Point(1, 1)
  ) map (p +) filter (self.route contains) size
}
