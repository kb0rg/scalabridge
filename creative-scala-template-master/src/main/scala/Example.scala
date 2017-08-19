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


/*
4.4 Abstraction
names abstract over expressions

a name stands in a for an expression. An expression tells us how to construct a 
value. If that value has a name then we don’t need to know anything about how 
the value is constructed. The expression can have arbtirary complexity, but we 
don’t have to care about this complexity if we just use the name.

Whenever we have an expression we can substitute a name that refers to the 
same value.

val box =
  Image.rectangle(40, 40).
    lineWidth(5.0).
    lineColor(Color.royalBlue.spin(30.degrees)).
    fillColor(Color.royalBlue) 

box beside box beside box beside box beside box


Exercise: Streets Ahead
use abstraction to create a street scene
(repeating pattern of houses and trees over a street w/ patterned curb)
**/

val pavement =
    Image.rectangle(60, 5).lineWidth(0).fillColor(Color.yellow) beside 
    Image.rectangle(20, 5).lineWidth(0).fillColor(Color.black) above 
    Image.rectangle(80, 10).lineWidth(0).fillColor(Color.black)


val tree = 
    Image.circle(35).lineWidth(0).fillColor(Color.green) above
    Image.rectangle(15, 30).lineColor(Color.brown).fillColor(Color.brown)

val house =
    Image.triangle(80, 40).lineWidth(0).fillColor(Color.fireBrick) above
    (Image.rectangle(80, 20).lineWidth(0).fillColor(Color.red) above
        (Image.rectangle(30, 40).lineWidth(0).fillColor(Color.red) beside
            Image.rectangle(20, 40).lineWidth(0).fillColor(Color.black) beside
            Image.rectangle(30, 40).lineWidth(0).fillColor(Color.red)))

val block =
    (house beside tree) above
    (pavement beside pavement beside pavement)

val street = 
    block beside block beside block

