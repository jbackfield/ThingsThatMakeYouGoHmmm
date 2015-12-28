package com.backfield.things.scala.patternmatching

import com.backfield.explain.{ExplainCode, Example}

object ValueBasedExample extends Example {

  var done = false

  override def name: String = "Value Based Pattern Matching"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[TestCaseClass](new TestCaseClass("Foo", 1234), (input : TestCaseClass) => {
      println(s"TestCaseClass: $input")
      ExplainCode.explain[String]("input match {\n" +
          "    case TestCaseClass(field1, field2) => s\"$field1 : $field2\"\n" +
          "    case _ => \"Unknown\"\n" +
          "}",
        input match {
          case TestCaseClass(field1, field2) => s"$field1 : $field2"
          case _ => "Unknown"
        }, (res : String) => {
          println(s"Our pattern match succeeded '$res'")
      }, ec)
      ExplainCode.explain[String]("input match {\n" +
        "    case TestCaseClass(field1, 1234) => s\"Success $field1\"\n" +
        "    case _ => \"Unknown\"\n" +
        "}",
        input match {
          case TestCaseClass(field1, 1234) => s"Success $field1"
          case _ => "Unknown"
        }, (res : String) => {
          println(s"Since our input number was 1234, our match worked: '$res'")
        }, ec)
      ExplainCode.explain[String]("input match {\n" +
        "    case TestCaseClass(field1, 5678) => s\"Success $field1\"\n" +
        "    case _ => \"Unknown\"\n" +
        "}",
        input match {
          case TestCaseClass(field1, 5678) => s"Success $field1"
          case _ => "Unknown"
        }, (res : String) => {
          println(s"Notice that since the match wanted 5678, it failed so we go to the default case: '$res'")
      }, ec)
      ExplainCode.explain[String]("input match {\n" +
        "    case TestCaseClass(_, 1234) => \"Success\"\n" +
        "    case _ => \"Unknown\"\n" +
        "}",
        input match {
          case TestCaseClass(_, 1234) => "Success"
          case _ => "Unknown"
        }, (res : String) => {
          println(s"We match and it doesn't matter at all what field1, only that field2 was 1234: '$res'")
      }, ec)
    }, ec)
    done = true
  }

}
