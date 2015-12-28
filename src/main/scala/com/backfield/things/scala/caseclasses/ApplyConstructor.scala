package com.backfield.things.scala.caseclasses

import com.backfield.explain.{Example, ExplainCode}

object ApplyConstructor extends Example {

  var done = false

  override val name = "Apply Constructor"

  val ec = ExplainCode()

  override def execute() : Unit = {
    ExplainCode.explain("TestCaseClass(\"Foo\", 1234)", TestCaseClass("Foo", 1234), (_ : TestCaseClass) => {
      println("Becomes an apply")
      ExplainCode.explain(TestCaseClass("Foo", 1234), (tcc : TestCaseClass) => {
        println(s"Field 1: ${tcc.field1}")
        println(s"Field 2: ${tcc.field2}")
        println(s"TestCaseClass.toString: $tcc")
      },
      ec)
    }, ec)
    done = true
  }

}
