package com.backfield.things.scala.dynamics

import com.backfield.explain.{ExplainCode, Example}

import scala.language.dynamics

object ApplyDynamicExample extends Example {

  var done: Boolean = false

  override def name: String = "Dynamic.applyDynamic"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Let's create an object who has dynamic functions")
    ExplainCode.explain[Boolean]("class Foo extends Dynamic {\n" +
      "    var methods = Map[String, List[Any] => Any]()\n" +
      "    def updateDynamic(name : String)(value : Any) : Unit = value match {\n" +
      "        case asFunction : (List[Any] => Any) => methods = methods + (name -> asFunction)\n" +
      "    }\n" +
      "    def applyDynamic(name : String)(args : Any*) : Any = methods.get(name) match {\n" +
      "        case Some(method) => method(args.toList)\n" +
      "        case _ => throw new NoSuchMethodException(s\"Method $name has not been defined\")\n" +
      "    }\n" +
      "}", true, (_ : Boolean) => {
      class Foo extends Dynamic {
        var methods = Map[String, List[Any] => Any]()
        def updateDynamic(name : String)(value : Any) : Unit = value match {
          case asFunction : (List[Any] => Any) => methods = methods + (name -> asFunction)
        }
        def applyDynamic(name : String)(args : Any*) : Any = methods.get(name) match {
          case Some(method) => method(args.toList)
          case _ => throw new NoSuchMethodException(s"Method $name has not been defined")
        }
      }
      println("We create an instance of Foo")
      ExplainCode.explain[Foo](new Foo(), (foo : Foo) => {
        println("And now, we can create a new method called bar")
        ExplainCode.explain[Boolean]("foo.bar = (args : List[Any]) => { args.mkString(\",\") }", true, (_ : Boolean) => {
          foo.bar = (args : List[Any]) => { args.mkString(",") }
          println("Now, we can call our new method with arguments which will print each one")
          ExplainCode.explain[Any]("foo.bar(\"Sha\", \"Bang\")", foo.bar("Sha", "Bang"), (s : Any) => {
            println(s"Which gives us a comma delimited value $s")
          }, ec)
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
