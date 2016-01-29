package com.backfield.things.scala.parameters

import com.backfield.explain.{ExplainCode, Example}

object FunctionCurryingExample extends Example {

  var done: Boolean = false

  override def name: String = "Function Currying"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Boolean]("def add(a : Int)(b : Int) : Int = a + b", true, (_ : Boolean) => {
      def add(a : Int)(b : Int) : Int = a + b
      println("Let's say that we want to add 10 and a list of numbers")
      ExplainCode.explain[List[Int]]("List(1,2,3,4,5).map(n => add(10)(n))", List(1,2,3,4,5).map(n => add(10)(n)), (_ : List[Int]) => {
        println("Which gets expanded to this")
        ExplainCode.explain[List[Int]](List(1,2,3,4,5).map(n => add(10)(n)), (result : List[Int]) => {
          println(s"To equal $result")
        }, ec)
      }, ec)
      ExplainCode.pause
      println("But can we make it simpler?  Yes, we can curry an add function")
      ExplainCode.explain[Boolean]("val addTo10 = add(10) _", true, (_ : Boolean) => {
        val addTo10 = add(10) _
        println("Now we can just pass in the addTo10 variable to map")
        ExplainCode.explain[List[Int]]("List(1,2,3,4,5).map(addTo10)", List(1,2,3,4,5).map(addTo10), (result : List[Int]) => {
          println(s"Same result $result but we curried the original add(a)(b) method to create a partial method equal to add(10)(b)")
        }, ec)
        println("For completeness, what is the difference between curry and function placeholder?")
        ExplainCode.explain[List[Int]](List(1,2,3,4,5).map(addTo10), (_ : List[Int]) => {}, ec)
        ExplainCode.explain[List[Int]](List(1,2,3,4,5).map(add(10)), (_ : List[Int]) => {}, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
