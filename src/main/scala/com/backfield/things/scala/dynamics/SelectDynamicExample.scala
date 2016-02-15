package com.backfield.things.scala.dynamics

import com.backfield.explain.{ExplainCode, Example}

import scala.language.dynamics

object SelectDynamicExample extends Example {

  var done: Boolean = false

  override def name: String = "Dynamic.selectDynamic"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Let's start with accessing a basic field")
    ExplainCode.explain[Boolean]("class Foo extends Dynamic {\n" +
      "    def selectDynamic(name : String) : Any = {\n" +
      "        s\"Requested $name\"\n" +
      "    }\n" +
      "}", true, (_ : Boolean) => {
      class Foo extends Dynamic {
        def selectDynamic(name : String) : Any = {
          s"Requested $name"
        }
      }
      println("Now we can request a field bar even though it doesn't exist")
      ExplainCode.explain[Any]("new Foo().bar", new Foo().bar, (_ : Any) => {
        println("Behind the scenes though, the compiler converts it")
        ExplainCode.explain[Any](new Foo().bar, (a : Any) => {
          println(s"Which results in $a")
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
