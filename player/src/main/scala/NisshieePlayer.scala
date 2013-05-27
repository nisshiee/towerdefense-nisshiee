package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

class NisshieePlayer(name: String) extends Player[State](name) {

  def init: State = Init

  def action: ((Snapshot, State)) => (Seq[Command], State) = Tactic.basic

}
