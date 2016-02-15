package com.backfield.things.scala

import com.backfield.explain.ExplainCode
import com.backfield.things.scala.cake._
import com.backfield.things.scala.caseclasses._
import com.backfield.things.scala.continuations._
import com.backfield.things.scala.dynamics._
import com.backfield.things.scala.implicits._
import com.backfield.things.scala.macros._
import com.backfield.things.scala.parameters._
import com.backfield.things.scala.patternmatching._
import com.backfield.things.scala.streams._
import com.backfield.things.scala.structuraltyping._
import com.backfield.things.scala.variance._

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
      ImplicitClass,
      MultiParameterFunctionExample,
      FunctionCurryingExample,
      CovariantReturnTypeExample,
      CovariantGenericTypeExample,
      CakePatternExample,
      StructuralTypingExample,
      StreamCreationExample,
      StreamConsumeExample,
      StreamEndExample,
      SelectDynamicExample,
      UpdateDynamicExample,
      ApplyDynamicExample,
      IntroToContinuationsExample,
      MacroExample
    )
    println(s"You executed ${Console.RED}${ExplainCode.iterate().length}${Console.RESET} commands")
  }

}
