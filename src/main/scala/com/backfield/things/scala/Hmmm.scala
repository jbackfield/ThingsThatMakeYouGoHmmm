package com.backfield.things.scala

import com.backfield.explain.ExplainCode
import com.backfield.things.scala.caseclasses.{CopyConstructor, ApplyConstructor}

object Hmmm {

  def main(args : Array[String]) : Unit = {
    ExplainCode.entryPoints = List(
      ApplyConstructor,
      CopyConstructor
    )
    println(s"You executed ${Console.RED}${ExplainCode.iterate().length}${Console.RESET} commands")
  }

}
