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
object Example {
  (circle(100) fillColor Color.paleGoldenrod lineColor Color.indianRed).draw
}