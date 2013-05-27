package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

trait RichConversions {

  implicit def toRichPoint(p: Point) = new RichPoint(p)
  implicit def toRichField(f: Field) = new RichField(f)
  implicit def toRichLabyrinth(l: Labyrinth) = new RichLabyrinth(l)
}
