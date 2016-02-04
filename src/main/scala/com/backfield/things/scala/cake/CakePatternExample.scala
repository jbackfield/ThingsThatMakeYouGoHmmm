package com.backfield.things.scala.cake

import com.backfield.explain.{ExplainCode, Example}

object CakePatternExample extends Example {

  var done: Boolean = false

  override def name: String = "Cake Pattern"

  val ec = ExplainCode()

  override def execute(): Unit = {
    println("Typesafe dependency injection")
    ExplainCode.pause
    println("We'll start with a model class User")
    ExplainCode.explain[Boolean]("case class User(id : Long, fname : String, lname : String) {}", true, (_:Boolean) => {
      case class User(id : Long, fname : String, lname : String) {}
      println("Now we define a trait for our DAO")
      ExplainCode.explain[Boolean]("trait UserDAO {\n" +
        "    def getUsers : List[User]\n" +
        "}", true, (_: Boolean) => {
        trait UserDAO {
          def getUsers : List[User]
        }
        println("Let's define a mock UserDAO implementation")
        ExplainCode.explain[Boolean]("trait MockUserDAO extends UserDAO {\n" +
          "    override def getUsers : List[User] = List(User(1, \"Josh\", \"Backfield\"))\n" +
          "}", true, (_: Boolean) => {
          trait MockUserDAO extends UserDAO {
            override def getUsers : List[User] = List(User(1, "Josh", "Backfield"))
          }
          println("Now we can define a class that 'requires' a UserDAO be mixed in")
          ExplainCode.explain[Boolean]("class UserController {\n" +
            "    this : UserDAO =>\n" +
            "    def listUsers : List[User] = this.getUsers\n" +
            "}", true, (_:Boolean) => {
            class UserController {
              this : UserDAO =>
              def listUsers : List[User] = this.getUsers
            }
            println("Now when we instantiate our controller, it will require us to mixin a UserDAO of some kind")
            ExplainCode.explain[UserController]("new UserController with MockUserDAO", new UserController with MockUserDAO, (uc : UserController) => {
              println(s"Now, invoking the controllers listUsers results in ${uc.listUsers}")
            }, ec)
          }, ec)
        }, ec)
      }, ec)

    }, ec)
    this.done = true
  }
}
