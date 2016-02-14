package com.backfield.things.scala.streams

import com.backfield.explain.{ExplainCode, Example}

object StreamConsumeExample extends Example {

  var done: Boolean = false

  override def name: String = "Stream Consuming"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Let's start with our fibonacci sequence again")
    ExplainCode.explain[Boolean]("def fib(a : Int, b : Int) : Stream[Int] = a #:: fib(b, a + b)", true, (_ : Boolean) => {
      def fib(a : Int, b : Int) : Stream[Int] = a #:: fib(b, a + b)
      println("Now we declare a variable at a starting point for our stream")
      ExplainCode.explain[Stream[Int]](fib(0, 1), (stream : Stream[Int]) => {
        println(s"Now we have our stream $stream and we can take the first 10 elements")
        ExplainCode.explain[List[Int]](stream.take(10).toList, (list : List[Int]) => {
          println(s"Check out our list of 10 elements: $list")
        }, ec)
        println("But we can also map over the stream before its rendered")
        ExplainCode.explain[Stream[Int]]("stream.map(x => x * 2)", stream.map(_ * 2), (mappedStream :Stream[Int]) => {
          println(s"And of course we have a new mapped stream $mappedStream which we can get the first 10 elements of")
          ExplainCode.explain[List[Int]](mappedStream.take(10).toList, (list : List[Int]) => {
            println(s"Notice that we have a mapped stream $list")
          }, ec)
        }, ec)
        println("We need to be careful though, if there is no end to the stream we can end up with no return")
        println("stream.toList will never return because it has no ending.")
        println("Neither would stream.map(x => x * 2).toList")
        println("Or stream.foreach")
      }, ec)
    }, ec)
    this.done = true
  }

}
