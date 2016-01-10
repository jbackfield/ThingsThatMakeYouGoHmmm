package com.backfield.things.scala

import com.backfield.explain.ExplainCode
import com.backfield.things.scala.caseclasses._
import com.backfield.things.scala.implicits._
import com.backfield.things.scala.patternmatching._

object Hmmm {

  def main(args : Array[String]) : Unit = {
    ExplainCode.entryPoints = List(
      ApplyConstructor,
      CopyConstructor,
      ValueBasedExample,
      GuardExample,
      PartialFunctionExample,
      ImplicitParameters,
      ImplicitFunctions,
      ImplicitClass
    )
    println(s"You executed ${Console.RED}${ExplainCode.iterate().length}${Console.RESET} commands")
  }

}
