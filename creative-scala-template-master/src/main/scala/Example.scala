import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DFrame._
import doodle.backend.StandardInterpreter._

// To use this example, open the SBT console and type:
//
// Example.image.draw
// object Example {
//   val image = circle(10).fillColor(Color.red) on circle(20) on circle(30)

//   def main(args: Array[String]): Unit = {
//     image.draw
//   }
// }

// we can then run this code by writing at the console
// `:paste src/main/scala/Example.scala`

// circle(100) fillColor Color.paleGoldenrod lineColor Color.indianRed

/*
examples of bad code that will throw error on compile when launching console:

circle(100) fillColor Color.paleGoldenrod lineColor Color.indianRed

error returned: expected class or object definition

In Scala all code in a file must be written inside an object or class. 
We can easily define an object by wrapping an expression like the below.

**/
// object Example {
//   (circle(100) fillColor Color.paleGoldenrod lineColor Color.indianRed).draw
// }

/*
4.3.4 The Top-Level
Code at the top-level is code that doesn’t have any other code wrapped around:
Scala will compile without having to wrap it in an object.

Object literals vs val declarations:
val definitions & expressions aren’t allowed at the top-level. 
Object literals, however, are.

The Scala console doesn’t make this top-level distinction (we can think of 
everything written in the console being wrapped in some object)

but.
for compiled files, we can put a val definition inside an object literal
can access it in dot format (ObjectName.varName)

therefore: 
an object literal defined var is only visible in the scope of its object

a name is visible from the point it is declared to the end of the nearest 
enclosing braces 

"lexical scoping" = nested scopes


object One {
  val a = 1

  object Two {
    val a = 3
    val b = 2
  }

  object Answer {
    val answer = a + Two.b
  }
}

scala> One.Answer.answer
res2: Int = 3

object One {
  val a = 5
  val b = 2
  
  object Answer {
    val a = 1
    val answer = a + b
  }
}

again 3. (Answer.a is the "a" in this scope)


object One {
  val a = 1
  
  object Two {
    val b = 2
  }
  
  val answer = a + b
}

This code doesn’t compile as b is not in scope where answer is declared.

object One {
  val a = b - 1
  val b = a + 1
  
  val answer = a + b
}

This code doesn’t work. Here a and b are defined in terms of each other which 
leads to a circular dependency that can’t be resolved.


**/

