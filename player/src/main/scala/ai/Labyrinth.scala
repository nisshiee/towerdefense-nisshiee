package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

case class Labyrinth (
   route: List[Point]
  ,wall: List[Point]
)

object Labyrinth {

  case class RLabyrinth (
     route: List[Point]
    ,wall: List[Point]
  )

  def longest(f: Field): Option[Labyrinth] =
    longestrec(RLabyrinth(Nil, Nil), f.start)(f) map reverse

  def longestrec(l: RLabyrinth, c: Point)(implicit f: Field): Option[RLabyrinth] =
    f.tile(c) match {
      case None => None
      case Some(Goal) => Some(l.copy(route = c :: l.route))
      case _ =>
        c.neighbors.toStream filter notfixed(l) map { next =>
          val walls = c.neighbors filter { p =>
            p != next && p.isIn(f) && notfixed(l)(p)
          }
          if (walls forall buildable)
            longestrec (
               RLabyrinth(c :: l.route, walls ++ l.wall)
              ,next
            )
          else
            None
        } collectFirst {
          case Some(l) => l
        }
    }

  def notfixed(l: RLabyrinth)(p: Point) =
    !l.route.contains(p) && !l.wall.contains(p)

  def buildable(p: Point)(implicit f: Field) = f.tile(p) map {
    case Plain => true
    case _ => false
  } getOrElse false

  def reverse: RLabyrinth => Labyrinth = {
    case RLabyrinth(r, w) => Labyrinth(r.reverse, w.reverse)
  }
}
