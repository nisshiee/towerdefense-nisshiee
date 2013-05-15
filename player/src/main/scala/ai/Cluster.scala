package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

case class Cluster(points: List[Point])

object Cluster {

  def apply(p: Point)(implicit f: Field): Cluster = {
    def rec(acc: Set[Point], p: Point): Set[Point] =
      (acc contains p, f.tile(p)) match {
        case (true, _) => acc
        case (false, Some(Block)) => p.neighbors.foldLeft(acc + p)(rec)
        case (false, _) => acc
      }

    f.tile(p) match {
      case Some(Block) => Cluster(rec(Set(), p).toList)
      case Some(_) => Cluster(List(p))
      case _ => Cluster(Nil)
    }
  }

  implicit class RichCluster(val underlying: Cluster) extends AnyVal {

    def surround(implicit f: Field): List[Point] =
      underlying.points.toSet flatMap { p: Point =>
        p.neighbors.toSet
      } filter (_.isIn(f)) toList
  }
}
