package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

class NisshieePlayer(name: String) extends Player[List[Point]](name) {

  def init: List[Point] = Nil

  def action: ((Snapshot, List[Point])) => (Seq[Command], List[Point]) = {
    case (_, w :: Nil) => (Command(w, WeakTower) :: Nil, Nil)
    case (ss, w1 :: w2 :: ws) if ss.level >= 30 =>
      (Command(w1, WeakTower) :: Command(w2, WeakTower) :: Nil, ws)
    case (_, w :: ws) => (Command(w, WeakTower) :: Nil, ws)
    case (ss, Nil) if ss.level == 1 => {
      println("turn 1")
      Labyrinth.longest(ss.field) match {
        case Some(Labyrinth(_, w :: ws)) => {
          println(w :: ws)
          (Command(w, WeakTower) :: Nil, ws)
        }
        case _ => {
          println("can't find route")
          (Nil, Nil)
        }
      }
    }
    case _ => (Nil, Nil)
  }

//  def minimize(f: Field) = f.copy (
//     _width = 5
//    ,_height = 5
//    ,_tiles = f._tiles + (Point(4, 4) -> Goal)
//  )
}
