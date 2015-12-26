package com.backfield.things.scala

import com.backfield.things.scala.caseclasses.{CopyConstructor, ApplyConstructor}

import scala.io.{StdIn, Source}
import scala.language.reflectiveCalls

object Hmmm {

  var entryPoints : List[Examples] = ApplyConstructor :: CopyConstructor :: Nil

  def iterate(commands : List[Int] = List()) : List[Int] = {
    print("Choice: ")
    val input = StdIn.readInt()
    if(entryPoints.length < input) {
      println(s"Invalid choice $input")
      iterate(commands)
    } else if(input == -1) {
      println("Goodbye")
      commands
    } else if(input == 0) {
      var i = 1
      entryPoints.foreach { ep =>
        val done = if(ep.done) { s"${Console.GREEN}√${Console.RESET}" } else { s"${Console.RED}-${Console.RESET}" }
        println(s"$i. ${ep.name}...[$done]")
        i = i + 1
      }
      iterate(commands)
    } else {
      val entryPoint = entryPoints(input - 1)
      println(s"${Console.GREEN}${entryPoint.name}${Console.RESET}")
      entryPoint.execute()
      iterate(input :: commands)
    }
  }

  def main(args : Array[String]) : Unit = {
    println(s"You executed ${Console.RED}${iterate().length}${Console.RESET} commands")
  }

}
