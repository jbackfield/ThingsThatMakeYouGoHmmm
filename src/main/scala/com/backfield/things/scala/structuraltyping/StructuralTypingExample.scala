package com.backfield.things.scala.structuraltyping

import com.backfield.explain.{ExplainCode, Example}
import scala.language.reflectiveCalls

object StructuralTypingExample extends Example {

  var done: Boolean = false

  override def name: String = "Structural Typing (Reflective Calls)"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("We'll define a method which takes an abstract type")
    ExplainCode.explain[Boolean]("def foo(bar : { def baz() : Int }) : Int = bar.baz() * 2", true, (_:Boolean) => {
      def foo(bar : { def baz() : Int }) : Int = bar.baz() * 2
      println("Now, we can declare a simple class")
      ExplainCode.explain[Boolean]("class Test {\n" +
        "    def baz() : Int = 21\n" +
        "}", true, (_:Boolean) => {
        class Test {
          def baz() : Int = 21
        }
        println("And send it over to foo")
        ExplainCode.explain[Int](foo(new Test()), (rtn : Int) => {
          println(s"Which gives us the expected answer $rtn, no need for interfaces!")
        }, ec)
      }, ec)
    }, ec)
    ExplainCode.pause
    println("We can take this further and define a method taking an abstract type which has two methods")
    ExplainCode.explain[Boolean]("def foo(bar : { def baz() : Int; def sha() : Int }) : Int = bar.baz() + bar.sha()", true, (_:Boolean) => {
      def foo(bar : { def baz() : Int; def sha() : Int }) : Int = bar.baz() + bar.sha()
      ExplainCode.explain[Boolean]("class Test {\n" +
        "    def baz() : Int = 7\n" +
        "    def sha() : Int = 21\n" +
        "}", true, (_:Boolean) => {
        class Test {
          def baz() : Int = 7
          def sha() : Int = 21
        }
        ExplainCode.explain[Int](foo(new Test()), (rtn : Int) => {
          println(s"Again giving us the expected answer $rtn")
        }, ec)
      }, ec)
      ExplainCode.pause
      println("Let's define an object instead")
      ExplainCode.explain[Boolean]("object Test {\n" +
        "    def baz() : Int = 7\n" +
        "    def sha() : Int = 21\n" +
        "}", true, (_:Boolean) => {
        object Test {
          def baz() : Int = 7
          def sha() : Int = 21
        }
        println("And send *that* object over to foo")
        ExplainCode.explain[Int](foo(Test), (rtn : Int) => {
          println(s"Once more giving us the expected answer $rtn")
        }, ec)
      }, ec)
      ExplainCode.pause
      println("But what if didn't want to name our object at all?")
      ExplainCode.explain[Int]("foo(new {\n" +
        "    def baz() : Int = 7\n" +
        "    def sha() : Int = 21\n" +
        "})",foo(new { def baz() : Int = 7; def sha() : Int = 21 }), (rtn : Int) => {
        println(s"Again, with the expected answer $rtn")
      }, ec)
    }, ec)
    this.done = true
  }

}
