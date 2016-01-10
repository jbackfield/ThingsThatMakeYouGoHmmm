package com.backfield.things.scala.implicits

import com.backfield.explain.{ExplainCode, Example}

object ImplicitParameters extends Example {

  var done = false

  override def name: String = "Implicit Parameters"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Let's define a function that has an implicit parameter")
    ExplainCode.explain[Boolean]("def f(implicit in : String) : Int = in.length", true, (_ : Boolean) => {
      def f(implicit in : String) : Int = in.length
      println("Then define an implicit value")
      ExplainCode.explain[String]("implicit val foo = \"foo\"", "foo", (input : String) => {
        implicit val foo = input
        ExplainCode.explain[Int]("f", 1, (_ : Int) => {
          println("Our implicit variable foo actually gets substituted in")
          ExplainCode.explain[Int](f, (res : Int) => {
            println(s"And results in $res")
          }, ec)
        }, ec)
        ExplainCode.pause
        println("But we can also override the implicit by passing in a direct value")
        ExplainCode.explain[Int](f("blah"), (input : Int) => {
          println(s"Which will result in $input")
        }, ec)
      }, ec)
    }, ec)

    ExplainCode.pause
    println("If you have multiple parameters, implicit must be the first and applies to all in the set")
    ExplainCode.explain[Boolean]("def f(implicit in : String, offset : Int) : Int = in.length - offset", true, (_ : Boolean) => {
      def f(implicit in : String, offset : Int) : Int = in.length - offset
      println("Now let's define an implicit String")
      ExplainCode.explain[String]("implicit val foo = \"foo\"", "foo", (input : String) => {
        implicit val foo = input
        println("If we wanted to call our method now, we would have to pass both parameters in")
        ExplainCode.explain[Int](f(foo, 1), (res : Int) => {
          println(s"This call works fine with a result of $res")
        }, ec)
        println("If we define an implicit Int")
        ExplainCode.explain[Int]("implicit val i = 1234", 1234, (input : Int) => {
          implicit val i = input
          println("Now we can execute with our implicit parameter set")
          ExplainCode.explain[Int]("f", f, (res : Int) => {
            println("Again the fields are substituted at compile time")
            ExplainCode.explain[Int](f, (res : Int) => {
              println(s"And executes with the result $res")
            }, ec)
          }, ec)
        }, ec)
      }, ec)
    }, ec)
    done = true
  }

}
