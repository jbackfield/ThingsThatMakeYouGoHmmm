package com.backfield.things.scala.caseclasses

import com.backfield.explain.ExplainCode
import com.backfield.things.scala.Examples

object ApplyConstructor extends Examples {

  var done = false

  override val name = "Apply Constructor"

  override def execute() : Unit = {
    ExplainCode.explain("TestCaseClass(\"Foo\", 1234)")(TestCaseClass("Foo", 1234), true, (_ : TestCaseClass) => {
      println("Becomes an apply")
      ExplainCode.explain(TestCaseClass("Foo", 1234), true, (tcc : TestCaseClass) => {
        println(s"Field 1: ${tcc.field1}")
        println(s"Field 2: ${tcc.field2}")
        println(s"TestCaseClass.toString: $tcc")
      })
    })
    done = true
  }

}
