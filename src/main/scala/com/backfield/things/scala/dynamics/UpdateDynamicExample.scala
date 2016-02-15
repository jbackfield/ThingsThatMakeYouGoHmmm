package com.backfield.things.scala.dynamics

import com.backfield.explain.{ExplainCode, Example}

import scala.language.dynamics

object UpdateDynamicExample extends Example {

  var done: Boolean = false

  override def name: String = "Dynamic.updateDynamic"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Let's create a meta object containing a map where keys are fields")
    ExplainCode.explain[Boolean]("class Foo extends Dynamic {\n" +
      "    var fields = Map[String, Any]()\n" +
      "    def selectDynamic(name : String) : Option[Any] = fields.get(name)\n" +
      "    def updateDynamic(name : String)(value : Any) : Unit = {\n" +
      "        fields = fields + (name -> value)\n" +
      "    }\n" +
      "}", true, (_ : Boolean) => {
      class Foo extends Dynamic {
        var fields = Map[String, Any]()
        def selectDynamic(name : String) : Option[Any] = fields.get(name)
        def updateDynamic(name : String)(value : Any) : Unit = {
          fields = fields + (name -> value)
        }
      }
      println("Now let's create a foo")
      ExplainCode.explain[Foo](new Foo(), (foo : Foo) => {
        println("And let's get bar")
        ExplainCode.explain[Any]("foo.bar", foo.bar, (a : Any) => {
          println(s"Because it's not been set, we get $a")
        }, ec)
        println("So let's set bar first")
        ExplainCode.explain[Boolean]("foo.bar = \"Our Value\"", true, (_ : Boolean) => {
          foo.bar = "Our Value"
          println("Now when we call it")
          ExplainCode.explain[Any]("foo.bar", foo.bar, (a : Any) => {
            println(s"We get our value back $a")
          }, ec)
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
