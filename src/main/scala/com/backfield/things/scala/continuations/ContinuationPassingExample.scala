package com.backfield.things.scala.continuations

import com.backfield.explain.{ExplainCode, Example}

import scala.util.Random
import scala.util.continuations._

object ContinuationPassingExample extends Example {

  var done: Boolean = false

  override def name: String = "Continuation Passing"

  var ec = ExplainCode()

  override def execute() : Unit = {
    println("We're going to capture k now, so let's define a variable")
    ExplainCode.explain[Boolean]("var cont : (Int=>(Int, Int, Int)) = null", true, (_ : Boolean) => {
      var cont : (Int => (Int,Int,Int)) = null
      println("So now we invoke our reset to store into cont")
      ExplainCode.explain[Boolean]("reset {\n" +
        "    val rand = Random.nextInt(10)\n" +
        "    var step = 0\n" +
        "    val in = shift { k: (Int=>(Int,Int,Int)) => cont = k }; step = step + 1; (in, rand, step)\n" +
        "}", true, (_ : Boolean) => {
        reset {
          val rand = Random.nextInt(10)
          var step = 0
          val in = shift { k: (Int => (Int, Int, Int)) => cont = k }; step = step + 1; (in, rand, step)
        }
        ExplainCode.explain[(Int,Int,Int)](cont(1), (res : (Int,Int,Int)) => {
          println(s"in: ${res._1}, rand: ${res._2}, step: ${res._3}")
        }, ec)
        ExplainCode.explain[(Int,Int,Int)](cont(2), (res : (Int,Int,Int)) => {
          println(s"in: ${res._1}, rand: ${res._2}, step: ${res._3}")
        }, ec)
        ExplainCode.explain[(Int,Int,Int)](cont(3), (res : (Int,Int,Int)) => {
          println(s"in: ${res._1}, rand: ${res._2}, step: ${res._3}")
        }, ec)
      }, ec)
      ExplainCode.pause
      println("We can create another one and store it back into cont as well")
      ExplainCode.explain[Boolean]("reset {\n" +
        "    val rand = Random.nextInt(10)\n" +
        "    val step = 0\n" +
        "    val in = shift { k: (Int=>(Int,Int,Int)) => cont = k }; step = step + 1; (in, rand, step)\n" +
        "}", true, (_ : Boolean) => {
        reset {
          val rand = Random.nextInt(10)
          var step = 0
          val in = shift { k: (Int => (Int, Int, Int)) => cont = k }; step = step + 1; (in, rand, step)
        }
        println("Notice that the rand has now changed")
        ExplainCode.explain[(Int,Int,Int)](cont(1), (res : (Int,Int,Int)) => {
          println(s"in: ${res._1}, rand: ${res._2}, step: ${res._3}")
        }, ec)
        ExplainCode.explain[(Int,Int,Int)](cont(2), (res : (Int,Int,Int)) => {
          println(s"in: ${res._1}, rand: ${res._2}, step: ${res._3}")
        }, ec)
        ExplainCode.explain[(Int,Int,Int)](cont(3), (res : (Int,Int,Int)) => {
          println(s"in: ${res._1}, rand: ${res._2}, step: ${res._3}")
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
