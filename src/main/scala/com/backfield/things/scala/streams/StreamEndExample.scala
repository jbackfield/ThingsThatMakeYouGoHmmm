package com.backfield.things.scala.streams

import java.io.{InputStreamReader, BufferedReader, FileReader}

import com.backfield.explain.{ExplainCode, Example}

object StreamEndExample extends Example {

  var done: Boolean = false

  override def name: String = "Ending Streams"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Boolean]("def streamDef(br : BufferedReader, nexts : List[Int] = List()) : Stream[Int] = nexts match {\n" +
      "    case List() => {\n" +
      "        val line = br.readLine()\n" +
      "        if(line != null) {\n" +
      "            val ints = line.split(\",\").map(_.toInt).toList\n" +
      "            ints.head #:: streamDef(br, ints.tail)\n" +
      "        } else {\n" +
      "            br.close()\n" +
      "            Stream.empty\n" +
      "        }\n" +
      "    }\n" +
      "    case x :: xs => {\n" +
      "        x #:: streamDef(br, xs)\n" +
      "    }\n" +
      "}", true, (_ : Boolean) => {
      def streamDef(br : BufferedReader, nexts : List[Int] = List()) : Stream[Int] = nexts match {
        case List() => {
          val line = br.readLine()
          if(line != null) {
            val ints = line.split(",").map(_.toInt).toList
            ints.head #:: streamDef(br, ints.tail)
          } else {
            br.close()
            Stream.empty
          }
        }
        case x :: xs => {
          x #:: streamDef(br, xs)
        }
      }
      println("Now let's define our entry point into this stream")
      ExplainCode.explain[Stream[Int]]("streamDef(\n" +
        "    new BufferedReader(\n" +
        "        new InputStreamReader(\n" +
        "            this.getClass.getClassLoader.getResourceAsStream(\"testfile\")\n" +
        "        )\n" +
        "    )\n" +
        ")", streamDef(new BufferedReader(new InputStreamReader(this.getClass.getClassLoader.getResourceAsStream("testfile")))), (stream : Stream[Int]) => {
        println("So now we can convert the entire stream to a list")
        ExplainCode.explain[List[Int]](stream.toList, (list : List[Int]) => {
          println(s"This results in list $list")
        }, ec)
        println("But we can also only take a certain number from the stream itself")
        ExplainCode.explain[List[Int]](stream.take(5).toList, (list : List[Int]) => {
          println(s"This results in list $list")
        }, ec)
      }, ec)
    }, ec)
    this.done = true
  }

}
