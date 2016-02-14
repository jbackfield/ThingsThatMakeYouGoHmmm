package com.backfield.things.scala.macros

import com.backfield.explain.{SampleMacros, ExplainCode, Example}

object MacroExample extends Example {

  var done: Boolean = false

  override def name: String = "Sample Macros"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Int]("val foo = 1", 1, (foo : Int) => {
      ExplainCode.explain[Boolean]("SampleMacros.test(foo == 2)", true, (_ : Boolean) => {
        SampleMacros.test(foo == 2)
      }, ec)
      ExplainCode.explain[Int]("val bar = 2", 2, (bar : Int) => {
        ExplainCode.explain[Boolean]("SampleMacros.test(foo == 2 && bar == 2)", true, (_ : Boolean) => {
          SampleMacros.test(foo == 2 && bar == 2)
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
