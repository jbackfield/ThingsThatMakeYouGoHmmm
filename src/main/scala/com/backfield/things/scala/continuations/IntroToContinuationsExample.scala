package com.backfield.things.scala.continuations

import com.backfield.explain.{ExplainCode, Example}

import scala.util.continuations._

object IntroToContinuationsExample extends Example {

  var done: Boolean = false

  override def name: String = "Intro to Continuations"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Int]("reset {\n" +
      "    shift { k : (Int=>Int) =>\n" +
      "        k(k(k(7)))\n" +
      "    } + 1\n" +
      "} * 2", reset {
      shift { k : (Int=>Int) => k(k(k(7))) } + 1
    } * 2, (result : Int) => {
      println(s"Which is the equivalent of (((7+1)+1)+1)*2 = $result")
    }, ec)

    ExplainCode.explain[Int]("def foo() = {\n" +
      "    1 + shift { k : (Int=>Int) =>\n" +
      "        k(k(k(7)))\n" +
      "    }\n" +
      "}\n" +
      "def bar() = {\n" +
      "    foo() * 2\n" +
      "}\n" +
      "def baz() = {\n" +
      "    reset(bar())\n" +
      "}\n" +
      "baz()", {
        def foo() = {
          1 + shift { k : (Int => Int) => k(k(k(7))) }
        }
        def bar() = {
          foo() * 2
        }
        def baz() = {
          reset(bar())
        }
        baz()
      }, (result : Int) => {
      println(s"Which is the equivalent of ((((((7+1)*2)+1)*2)+1)*2) = $result")
    }, ec)

    ExplainCode.explain[Int]("def foo() = {\n" +
      "    shift { k : (Int=>Int) =>\n" +
      "        k(k(7))\n" +
      "    } + 1" +
      "}\n" +
      "reset(2 * foo() * 2)", {
      def foo() = {
        shift { k : (Int=>Int) =>
          k(k(7))
        } + 1
      }
      reset(2 * foo() * 2)
    }, (result : Int) => {
      println(s"Which is the equivalent of ((((7+1)*2*2)+1)*2*2) = $result")
    }, ec)

    ExplainCode.explain[Int]("def foo() = {\n" +
      "    shift { k : (Int=>Int) =>\n" +
      "        k(k(7))\n" +
      "    } * 2" +
      "}\n" +
      "reset(foo() + 1)", {
      def foo() = {
        shift { k : (Int=>Int) =>
          k(k(7))
        } * 2
      }
      reset(foo() + 1)
    }, (result : Int) => {
      println(s"Which is the equivalent of (((7*2)+1)*2)+1 = $result")
    }, ec)

    ExplainCode.explain[Int]("def foo() = {\n" +
      "    shift { k : (Int=>Int) =>\n" +
      "        k(k(7))\n" +
      "    } * 2" +
      "}\n" +
      "reset(2 * foo() + 1)", {
      def foo() = {
        shift { k : (Int=>Int) =>
          k(k(7))
        } * 2
      }
      reset(2 * foo() + 1)
    }, (result : Int) => {
      println(s"Which is the equivalent of ((2*((2*(7*2))+1)*2)+1) = $result")
    }, ec)

  }

}
