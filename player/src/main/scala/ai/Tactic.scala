package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

object Tactic {

  val basic: ((Snapshot, State)) => (Seq[Command], State) = { x =>
    (init orElse weak1 orElse weak2)
      .applyOrElse(x, default)
  }

  val init: PartialFunction[(Snapshot, State), (Seq[Command], State)] = {
    case (ss, Init) => Labyrinth.longest(ss.field) match {
      case Some(l @ Labyrinth(_, w :: ws)) => (Command(w, WeakTower) :: Nil, Weak1(ws, l))
      case _ => (Nil, Observe)
    }
  }

  val weak1: PartialFunction[(Snapshot, State), (Seq[Command], State)] = {
    case (_, Weak1(Nil, _)) => (Nil, Observe)
    case (_, Weak1(w :: Nil, _)) => (Command(w, WeakTower) :: Nil, Observe)
    case (ss, Weak1(w :: ws, l)) if ss.level >= 29 => (Command(w, WeakTower) :: Nil, Weak2(ws, l))
    case (ss, Weak1(w :: ws, l)) => (Command(w, WeakTower) :: Nil, Weak1(ws, l))
  }

  val weak2: PartialFunction[(Snapshot, State), (Seq[Command], State)] = {
    case (_, Weak2(Nil, _)) => (Nil, Observe)
    case (_, Weak2(w :: Nil, _)) => (Command(w, WeakTower) :: Nil, Observe)
    case (_, Weak2(w1 :: w2 :: Nil, _)) =>
      (Command(w1, WeakTower) :: Command(w2, WeakTower) :: Nil, Observe)
    case (_, Weak2(w1 :: w2 :: ws, l)) =>
      (Command(w1, WeakTower) :: Command(w2, WeakTower) :: Nil, Weak2(ws, l))
  }

  val default: ((Snapshot, State)) => (Seq[Command], State) = { _ => (Nil, Observe) }
}
