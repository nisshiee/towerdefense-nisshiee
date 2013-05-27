package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

object Tactic {

  val basic: ((Snapshot, State)) => (Seq[Command], State) = { x =>
    (init orElse weak)
      .applyOrElse(x, default)
  }

  val init: PartialFunction[(Snapshot, State), (Seq[Command], State)] = {
    case (ss, Init) => Labyrinth.longest(ss.field) match {
      case Some(l @ Labyrinth(_, w1 :: w2 :: ws)) =>
        (Command(w1, WeakTower) :: Command(w2, WeakTower) :: Nil, Weak(ws, l))
      case _ => (Nil, Observe)
    }
  }

  val weak: PartialFunction[(Snapshot, State), (Seq[Command], State)] = {
    case (_, Weak(Nil, _)) => (Nil, Observe)
    case (_, Weak(w :: Nil, l)) => (Command(w, WeakTower) :: Nil, Weak(Nil, l))
    case (_, Weak(w1 :: w2 :: ws, l)) =>
      (Command(w1, WeakTower) :: Command(w2, WeakTower) :: Nil, Weak(ws, l))
  }

  val default: ((Snapshot, State)) => (Seq[Command], State) = { _ => (Nil, Observe) }
}
