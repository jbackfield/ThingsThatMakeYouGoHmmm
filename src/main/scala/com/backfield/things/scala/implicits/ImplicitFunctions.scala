package com.backfield.things.scala.implicits

import com.backfield.explain.{ExplainCode, Example}

import scala.language.implicitConversions
/**
  * Created by jbackfield on 1/9/16.
  */
object ImplicitFunctions extends Example {

  var done: Boolean = false

  override def name: String = "Implicit Functions"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Boolean](
      "implicit def stringToList(str : String) : List[Int] = (0 until str.toInt).toList",
      true,
      (_ : Boolean) => {
        implicit def stringToList(str : String) : List[Int] = (0 until str.toInt).toList
        println("Now we should be able to use a string '4' and coerce it into a list")
        ExplainCode.explain[List[Int]]("\"4\" : List[Int]", "4", (_ : List[Int]) => {
          println("Since the compiler knows how to convert from a string into a List[Int], it uses that implicit method.")
        }, ec)
        ExplainCode.explain[List[Int]]("4" : List[Int], (input : List[Int]) => {
          println(s"And how we now have a list: $input")
        }, ec)
      },
      ec
    )
    println("Now we define a class")
    ExplainCode.explain[Boolean]("class Foo(str : String) {\n" +
      "  def asException = new Exception(str)\n" +
      "}",
      true,
      (_ : Boolean) => {
        class Foo(str : String) {
          def asException = new Exception(str)
        }
        println("Now we create an implicit to create a Foo object")
        ExplainCode.explain[Boolean](
          "implicit def stringToFoo(str : String) : Foo = new Foo(str)",
          true,
          (_ : Boolean) => {
            implicit def stringToFoo(str : String) : Foo = new Foo(str)
            println("Finally, we can use our implicit to call .asException")
            ExplainCode.explain[Exception]("\"Exception\".asException", "Exception".asException, (ex : Exception) => {}, ec)
            println("This actually gets converted to")
            ExplainCode.explain[Exception]("Exception".asException, (ex : Exception) => {
              println(s"Which then returns the exception $ex")
            }, ec)
          },
          ec
        )
      },
      ec
    )

    done = true
  }

}
