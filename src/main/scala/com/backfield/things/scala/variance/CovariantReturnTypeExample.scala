package com.backfield.things.scala.variance

import com.backfield.explain.{ExplainCode, Example}

object CovariantReturnTypeExample extends Example {

  var done: Boolean = false

  override def name: String = "Covariant Return Type"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Let's define a trait")
    ExplainCode.explain[Boolean]("trait MyTrait {\n" +
      "    def myMethod() : CharSequence\n" +
      "}", true, (_ : Boolean) => {
      trait MyTrait { def myMethod() : CharSequence }
      println("Now we can define an implementing class")
      ExplainCode.explain[Boolean]("class MyClass extends MyTrait {\n" +
        "    override def myMethod() : CharSequence = \"Foo Bar\"\n" +
        "}", true, (_ : Boolean) => {}, ec)
      ExplainCode.pause
      println("But we can use covariance to specify a more specific return type")
      ExplainCode.explain[Boolean]("class MyClass extends MyTrait {\n" +
        "    override def myMethod() : String = \"Foo Bar\"\n" +
        "}", true, (_ : Boolean) => {
        class MyClass extends MyTrait { override def myMethod() : String = "Foo Bar" }
      }, ec)
    }, ec)
    this.done = true
  }

}
