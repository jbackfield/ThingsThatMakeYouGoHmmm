package com.backfield.things.scala.patternmatching

import com.backfield.explain.{ExplainCode, Example}

object GuardExample extends Example {

  var done = false

  override def name: String = "Guards Within Pattern Matching"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[TestCaseClass](new TestCaseClass("Foo", 1234), (tcc : TestCaseClass) => {
      println(s"TestCaseClass: $tcc")
      ExplainCode.explain[String]("tcc match {\n " +
        "    case TestCaseClass(\"Foo\", _) => \"Success\"\n" +
        "    case _ => \"Unknown\"\n" +
        "}", tcc match {
        case TestCaseClass("Foo", _) => "Success"
        case _ => "Unknown"
      }, (res : String) => {
        println(s"Value matching allows match given specific values: '$res'")
      }, ec)
      ExplainCode.explain[String]("tcc match {\n " +
        "    case TestCaseClass(field1, _) if field1.isEmpty => \"Success\"\n" +
        "    case _ => \"Unknown\"\n" +
        "}", tcc match {
        case TestCaseClass(field1, _) if field1.isEmpty => "Success"
        case _ => "Unknown"
      }, (res : String) => {
        println(s"But we can even extract from a case class and then do if statements on those fields: '$res'")
      }, ec)
      ExplainCode.explain[Int]("val externalField = 1230", 1230, (externalField : Int) => {
        ExplainCode.explain[String]("tcc match {\n" +
          "    case TestCaseClass(_, field2) if field2 > externalField => \"Success\"\n" +
          "    case _ => \"Unknown\"\n" +
          "}", tcc match {
          case TestCaseClass(_, field2) if field2 > externalField => "Success"
          case _ => "Unknown"
        }, (res : String) => {
          println(s"We can also use variables within scope: '$res'")
        }, ec)
      }, ec)
    }, ec)

    this.done = true
  }

}
