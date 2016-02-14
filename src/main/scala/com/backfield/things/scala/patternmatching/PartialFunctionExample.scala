package com.backfield.things.scala.patternmatching

import com.backfield.explain.{ExplainCode, Example}

/**
  * Created by jbackfield on 1/1/16.
  */
object PartialFunctionExample extends Example {

  var done = false

  override def name: String = "Partial Function"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[PartialFunction[TestCaseClass, Int]](
      "val pf = {\n" +
        "    case TestCaseClass(\"Bar\", 1234) => 100\n" +
        "    case TestCaseClass(\"Bar\", _) => 60\n" +
        "    case TestCaseClass(_, 1234) => 50\n" +
        "}", {
        case TestCaseClass(_, 1234) => 100
        case TestCaseClass("Bar", _) => 60
        case TestCaseClass(_, 1234) => 50
      }: PartialFunction[TestCaseClass, Int]
      , (pf: PartialFunction[TestCaseClass, Int]) => {
        println("So let's try an example")
        ExplainCode.explain[Boolean](pf.isDefinedAt(new TestCaseClass("Bar", 1234)), (res : Boolean) => {
          println(s"Because the PartialFunction has a case that matches, the result is $res")
        }, ec)
        ExplainCode.pause
        println("Even if we only provide one valid value")
        ExplainCode.explain[Boolean](pf.isDefinedAt(new TestCaseClass("Foo", 1234)), (res : Boolean) => {
          println(s"Any of the case statements will result in $res")
        }, ec)
        ExplainCode.pause
        println("But if we don't have a valid match")
        ExplainCode.explain[Boolean](pf.isDefinedAt(new TestCaseClass("Foo", 5678)), (res : Boolean) => {
          println(s"Will result in $res")
        }, ec)
        ExplainCode.pause
        println("We can also chain to our original partial function")
        ExplainCode.explain[PartialFunction[TestCaseClass, Int]](
          "val pf2 = pf.orElse {\n" +
            "    case TestCaseClass(\"Foo\", _) => 40\n" +
            "}",
          pf.orElse {
            case TestCaseClass("Foo", _) => 40
          } : PartialFunction[TestCaseClass, Int], (pf2 : PartialFunction[TestCaseClass, Int]) => {
            ExplainCode.explain[Boolean](pf2.isDefinedAt(new TestCaseClass("Bar", 1234)), (res : Boolean) => {
              println(s"We'll still register $res because we kept the original set of partial function matches")
            }, ec)
            ExplainCode.pause
            println("But we can now try using our new partial function match")
            ExplainCode.explain[Boolean](pf2.isDefinedAt(new TestCaseClass("Foo", 5678)), (res : Boolean) => {
              println(s"And since it does match we get $res")
            }, ec)
          },
          ec
        )
        ExplainCode.pause
        println("We can also lift the PartialFunction into a function itself")
        ExplainCode.explain[(TestCaseClass => Option[Int])](pf.lift, (f : TestCaseClass => Option[Int]) => {
          ExplainCode.explain[Option[Int]](f(new TestCaseClass("Bar", 1234)), (res : Option[Int]) => {
            println(s"Since the partial function exists at that value, we get $res")
          }, ec)
          ExplainCode.explain[Option[Int]](f.apply(new TestCaseClass("Foo", 5678)), (res : Option[Int]) => {
            println(s"Since the partial function does not exist at that value we got $res")
          }, ec)
        }, ec)
      }, ec)
    this.done = true
  }

}
