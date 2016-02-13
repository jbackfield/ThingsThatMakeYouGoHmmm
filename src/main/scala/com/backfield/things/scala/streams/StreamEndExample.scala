package com.backfield.things.scala.streams

import java.io.{InputStreamReader, BufferedReader, FileReader}

import com.backfield.explain.{ExplainCode, Example}

object StreamEndExample extends Example {

  var done: Boolean = false

  override def name: String = "Ending Streams"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[Stream[Int]]("def stream(br : BufferedReader, nexts : List[Int] = List()) : Stream[Int] = nexts match {\n" +
      "    case List() => {\n" +
      "        val line = br.readLine()\n" +
      "        if(line != null) {\n" +
      "            val ints = line.split(\",\").map(_.toInt).toList\n" +
      "            ints.head #:: stream(br, ints.tail)\n" +
      "        } else {\n" +
      "            br.close()\n" +
      "            Stream.empty\n" +
      "        }\n" +
      "    }\n" +
      "    case x :: xs => {\n" +
      "        x #:: stream(br, xs)\n" +
      "    }\n" +
      "}\n" +
      "stream(\n" +
      "    new BufferedReader(\n" +
      "        new InputStreamReader(\n" +
      "            this.getClass.getClassLoader.getResourceAsStream(\"testfile\")\n" +
      "        )\n" +
      "    )\n" +
      ")", {
      def stream(br : BufferedReader, nexts : List[Int] = List()) : Stream[Int] = nexts match {
        case List() => {
          val line = br.readLine()
          if(line != null) {
            val ints = line.split(",").map(_.toInt).toList
            ints.head #:: stream(br, ints.tail)
          } else {
            br.close()
            Stream.empty
          }
        }
        case x :: xs => {
          x #:: stream(br, xs)
        }
      }
      stream(new BufferedReader(new InputStreamReader(this.getClass.getClassLoader.getResourceAsStream("testfile"))))
    }, (stream : Stream[Int]) => {
      println("So now we can do a foreach on this stream to see the list of ints")
      stream.foreach(println)
      println("Notice that we've ended the stream based on when there is nothing left in the buffered reader")
    }, ec)
  }

}
