package com.backfield.things.scala.caseclasses

import com.backfield.explain.{Example, ExplainCode}

object CopyConstructor extends Example {

  var done = false

  override val name = "Copy Constructor"

  val ec = ExplainCode()

  override def execute() : Unit = {
    ExplainCode.explain[TestCaseClass](new TestCaseClass("Foo", 1234), (tcc : TestCaseClass) => {
      println(s"TestCaseClass: $tcc")
      ExplainCode.explain[TestCaseClass]("tcc.copy(field1 = \"Bar\")", tcc.copy(field1 = "Bar"), (changed : TestCaseClass) => {
        println(s"Only Field1 Changed: $changed")
      }, ec)
      ExplainCode.explain[TestCaseClass]("tcc.copy(field2 = 5678)", tcc.copy(field2 = 5678), (changed : TestCaseClass) => {
        println(s"Only Field2 Changed: $changed")
      }, ec)
      ExplainCode.explain[TestCaseClass](tcc.copy("Bar", 5678), (changed : TestCaseClass) => {
        println(s"Both fields changed: $changed")
      }, ec)
    }, ec)
    done = true
  }

}
