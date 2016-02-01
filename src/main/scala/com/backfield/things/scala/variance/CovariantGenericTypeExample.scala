package com.backfield.things.scala.variance

import com.backfield.explain.{ExplainCode, Example}

object CovariantGenericTypeExample extends Example {

  var done: Boolean = false

  override def name: String = "Covariant Generic Types"

  val ec = ExplainCode()

  override def execute(): Unit = {
    ExplainCode.explain[List[String]](
      """val str : List[String] = List("abc","def","ghi","jkl","mno")""",
      List("abc","def","ghi","jkl","mno"),
      (str : List[String]) => {
        println("We can now coerce the type into a more generic typed generic")
        ExplainCode.explain[List[CharSequence]](
          str : List[CharSequence],
          (b : List[CharSequence]) => {
            println("Now, if we iterate over the list, we can see we still have a list of strings")
            b.foreach(n => println(n.getClass))
          }, ec)
      }, ec)
    println("If we look at Java, there is no way to convert Generic Types")
    ExplainCode.explain[Boolean](
      "import java.util.Optional;\n" +
        "import java.util.List;\n" +
        "import java.util.LinkedList;\n\n" +
        "public class Temp {\n" +
        "    public static void main(String[] args) {\n" +
        "        List<String> strs = new LinkedList<>();\n" +
        "        List<CharSequence> chars = (List<CharSequence>)strs;\n" +
        "    }\n" +
        "}",
        true, (_:Boolean) => {
          println("error: incompatible types: List<String> cannot be converted to List<CharSequence>")
        }, ec)

    done = true
  }


}
