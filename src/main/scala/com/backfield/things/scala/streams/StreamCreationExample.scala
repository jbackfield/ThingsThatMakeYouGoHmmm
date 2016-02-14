package com.backfield.things.scala.streams

import com.backfield.explain.{ExplainCode, Example}

object StreamCreationExample extends Example {

  var done: Boolean = false

  override def name: String = "Stream Creation"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Stream[Int]]("val 1 #:: 2 #:: 3 #:: Stream.Empty", 1 #:: 2 #:: 3 #:: Stream.Empty, (stream : Stream[Int]) => {
      println(s"We now have a stream $stream")
      ExplainCode.explain[String](stream.mkString(","), (str : String) => {
        println(s"This results in a comma delimited string $str")
      }, ec)
    }, ec)
    println("Let's do the fibonacci sequence")
    ExplainCode.explain[Boolean]("def fib(a : Int, b : Int) : Stream[Int] = a #:: fib(b, a + b)", true, (_ : Boolean) => {
      def fib(a : Int, b : Int) : Stream[Int] = a #:: fib(b, a + b)
      println("Notice that we declare what our current element is 'a'")
      println("We then declare how to continue our stream fib(b, a + b)")
      ExplainCode.pause
      println("Now we declare a variable at a starting point for our stream")
      ExplainCode.explain[Stream[Int]](fib(0, 1), (stream : Stream[Int]) => {
        println(s"Now we have our stream $stream and we can take the first 10 elements")
        ExplainCode.explain[List[Int]](stream.take(10).toList, (list : List[Int]) => {
          println(s"Check out our list of 10 elements: $list")
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
