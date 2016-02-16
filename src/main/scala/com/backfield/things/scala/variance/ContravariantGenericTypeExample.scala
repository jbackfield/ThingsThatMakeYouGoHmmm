package com.backfield.things.scala.variance

import com.backfield.explain.{ExplainCode, Example}

object ContravariantGenericTypeExample extends Example {

  var done: Boolean = false

  override def name: String = "Contravariant Generic Types"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Let's create a class which has a contravariant generic")
    ExplainCode.explain[Boolean]("class Foo[-A](value : A)", true, (_ : Boolean) => {
      class Foo[-A](input : A)
      println("Now we create an instance")
      ExplainCode.explain[Foo[CharSequence]](new Foo("bar" : CharSequence), (foo : Foo[CharSequence]) => {
        println("But we can now coerce the type to a string...")
        ExplainCode.explain[Foo[String]](foo : Foo[String], (food : Foo[String]) => {
          println("This works... Kind of, notice you don't have access to the value?")
        }, ec)
      }, ec)
    }, ec)
    println("So what happens if we want access to value?")
    ExplainCode.explain[Boolean]("class Foo[-A](val value : A)", true, (_ : Boolean) => {
      println("Doesn't work: contravariant type A occurs in covariant position in type => A of value value")
    }, ec)
    this.done = true
  }

}
