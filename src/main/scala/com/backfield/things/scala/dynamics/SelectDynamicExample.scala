package com.backfield.things.scala.dynamics

import com.backfield.explain.{ExplainCode, Example}

object SelectDynamicExample extends Example {

  var done: Boolean = false

  override def name: String = "Dynamic.selectDynamic"

  val ec = ExplainCode()

  override def execute(): Unit = {
    this.done = true
  }

}
