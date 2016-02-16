package com.backfield.things.scala.parameters

import com.backfield.explain.{ExplainCode, Example}

object MultiParameterFunctionExample extends Example {

  var done: Boolean = false

  override def name: String = "Multi-Parameter Functions"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Boolean]("def add(a : Int, b : Int) : Int = a + b", true, (_ : Boolean) => {
      def add(a : Int, b : Int) : Int = a + b
      ExplainCode.explain[Int](add(1, 2), (value : Int) => {
        println(s"Our value is of course $value")
      }, ec)
    }, ec)
    ExplainCode.pause
    println("But, we can rewrite this method to use multiple parameters")
    ExplainCode.explain[Boolean]("def add(a : Int)(b : Int) : Int = a + b", true, (_ : Boolean) => {
      def add(a : Int)(b : Int) : Int = a + b
      ExplainCode.explain[Int](add(1)(2), (value : Int) => {
        println(s" Again our value is $value")
      }, ec)
    }, ec)
    ExplainCode.pause
    println("Remember though, we can make a parameter set implicit?  Let's create an implicit step size calculator")
    ExplainCode.explain[Boolean]("def steps(count : Int)(implicit size : Int) : Int = count * size", true, (_ : Boolean) => {
      def steps(count : Int)(implicit size : Int) : Int = count * size
      println("We create our implicit variable to be used as the step size")
      ExplainCode.explain[Int]("implicit val s = 5", 5, (i : Int) => {
        implicit var s = i
        println("And we can now calculate, based on the count of steps, how large the step size is")
        ExplainCode.explain[Int]("steps(10)", steps(10), (_ : Int) => {
          println("But let's be sure that it's being expanded")
          ExplainCode.explain[Int](steps(10), (result : Int) => {
            println(s"And our size of the steps is $result")
          }, ec)
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
