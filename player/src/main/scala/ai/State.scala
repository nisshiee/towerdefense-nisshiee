package org.nisshiee.towerdefense

import org.nisshiee.towerdefensescala._

sealed trait State
case object Init extends State
case class Weak(walls: List[Point], l: Labyrinth) extends State
case object Observe extends State
