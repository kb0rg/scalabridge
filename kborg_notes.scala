/**
Notes from scala bridge 
http://www.scalabridge.org/creative-scala-v2.html
*/


// Basics: 
// 2 Expressions, Values, and Types

/*
In Scala all values are objects.  An object is a grouping of data and 
operations on that data.


A method call is an expression, and thus evaluates to an object.

Infix Operator Notation:
Any Scala expression written a.b(c) can also be written a b c.
Note that a b c d e is equivalent to a.b(c).d(e), not a.b(c, d, e).

 The type of an expression tells the compiler what methods exist on the value 
 it evaluates to. Our code won’t compile if we try to call to a method that 
 doesn’t exist.

 Types are a property of expressions and thus exist at compile-time (as we have 
    discussed before.) This means we can determine the type of expression even 
 if evaluating it results in an error at run-time.
*/

// Excercises:
// 3 Computing With Pictures

/*
If you run the examples from the SBT console within this repo they will 
just work. If not, you will need to start your code with the following 
imports to make Doodle available.
**/

import doodle.core._
import doodle.core.Image._
import doodle.syntax._
import doodle.jvm.Java2DCanvas._
import doodle.backend.StandardInterpreter._

Image.circle(10)
// res0: doodle.core.Image = Circle(10.0)
circle(10)
// res1: doodle.core.Image = Circle(10.0)
circle(10).draw
// opens window with circle
:type circle(10)
// doodle.core.Image
:type circle(10).draw
// Unit

/*
Unit is the type of expressions that have no interesting value to return. 
This is the case for .draw; we call it because we want something to appear on 
the screen, not because we have a use for the value it returns.
*/

// layout: doodle has methods for combining images

((circle(10) beside circle(10) beside circle(10)) on circle(30)).draw

// using scala standard colors:
(circle(10) fillColor Color.black on circle(20) fillColor Color.azure on
    circle(30) fillColor Color.white on circle(40) 
    fillColor Color.darkSeaGreen).draw

// creating colors

/*
Computers work with colors defined by mixing together different amounts of red, 
green, and blue. This “RGB” model is an additive model of color. Each red, 
green, or blue component can have a value between zero and 255. If all three 
components are set to the maximum of 255 we get pure white. If all components 
are zero we get black.

We can create our own RGB colors using the rgb method on the Color object. 
This method takes three parameters: the red, green, and blue components. These 
are numbers between 0 and 255, called an UnsignedByte2. There is no literal 
expression for UnsignedByte like there is for Int, so we must convert an Int to 
UnsignedByte. We can do this with the uByte method. An Int can take on more 
values that an UnsignedByte, so if the number is too small or too large to be 
represented as a UnsignedByte it will be converted to the closest values is the 
range 0 to 255. These examples illustrate the conversion.
*/

0.uByte
// res0: doodle.core.UnsignedByte = UnsignedByte(-128)

255.uByte
// res1: doodle.core.UnsignedByte = UnsignedByte(127)

128.uByte
// res2: doodle.core.UnsignedByte = UnsignedByte(0)

-100.uByte // Too small, is transformed to 0
// res3: doodle.core.UnsignedByte = UnsignedByte(-128)

1000.uByte // Too big, is transformed to 255
// res4: doodle.core.UnsignedByte = UnsignedByte(127)

// (Note that UnsignedByte is a feature of Doodle. It is not something provided by Scala.)

/*
The RGB color representation is not very easy to use. The hue-saturation-
lightness (HSL) format more closely correponds to how we perceive color. In 
this representation a color consists of:
- hue, which is an angle between 0 and 360 degrees giving a rotation around 
the color wheel.
- saturation, which is a number between 0 and 1 giving the intensity of the 
color from a drab gray to a pure color; and
- lightness between 0 and 1 giving the color a brightness varying from black 
to pure white.

We can construct a color in the HSL representation using the Color.hsl method. 
This method takes as parameters the hue, saturation, and lightness. The hue is 
an Angle. We can convert a Double to an Angle using the degrees (or radians) 
methods.

*/

Color.hsl(0.degrees, 0.8.normalized, 0.6.normalized) // A pastel red



