package com.backfield.things.scala.implicits

import com.backfield.explain.{ExplainCode, Example}

import scala.language.implicitConversions

object ImplicitClass extends Example {

  var done: Boolean = false

  override def name: String = "Implicit Class"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("We start by defining an implicit class")
    ExplainCode.explain[Boolean]("implicit class Foo(str : String) {\n" +
      "  def asException : Exception = new Exception(str)\n" +
      "}",
      true,
      (_ : Boolean) => {
        implicit class Foo(str : String) {
          def asException : Exception = new Exception(str)
        }
        println("Now we can treat the string as a Foo")
        ExplainCode.explain[Boolean]("\"Example Exception\".asException", false, (_ : Boolean) => {}, ec)
        println("Again, the string gets wrapped, but this time with the Foo constructor")
        ExplainCode.explain[Exception]("Example Exception".asException, (ex : Exception) => {
          println(s"Of course we now have that exception $ex")
        }, ec)
      },
      ec
    )
    this.done = true
  }

}
