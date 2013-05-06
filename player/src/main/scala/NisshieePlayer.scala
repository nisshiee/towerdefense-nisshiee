package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

class NisshieePlayer(name: String) extends Player[Int](name) {

  def init: Int = 0

  def action: ((Snapshot, Int)) => ((Seq[Command], Int)) = {
    case (snapshot, turn) =>
      List(Command(Point(turn, 1), WeakTower)) -> (turn + 1)
  }
}
