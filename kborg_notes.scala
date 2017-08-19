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

// creating & manipulating colors

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


Saturation and lightness are both normalized to between 0.0 and 1.0. We can 
convert a Double to a normalized value with the .normalized method.
*/

(circle(50) fillColor Color.hsl(0.degrees, 0.8.normalized, 0.6.normalized)).draw
// A pastel red

/*
The effectiveness of a composition often depends as much on the relationships 
between colors as the actual colors used. Colors have several methods that allow 
us to create a new color from an existing one. The most commonly used ones are:
- spin, which rotates the hue by an Angle;
- saturate and desaturate, which respectively add and subtract a Normalised 
value from the color; and
- lighten and darken, which respecitvely add and subtract a Normalised value 
from the lightness.

**/

((circle(100) fillColor Color.red) beside 
  (circle(100) fillColor Color.red.spin(15.degrees)) beside
    (circle(100) fillColor Color.red.spin(30.degrees))).lineWidth(5.0).draw
// Three circles, starting with Color.red and spinning by 15 degrees for each 
// successive circle


(((circle(20) fillColor (Color.red darken 0.2.normalized))
  beside (circle(20) fillColor Color.red)
  beside (circle(20) fillColor (Color.red lighten 0.2.normalized))) above
((rectangle(40,40) fillColor (Color.red desaturate 0.6.normalized)) 
  beside (rectangle(40,40) fillColor (Color.red desaturate 0.3.normalized))
  beside (rectangle(40,40) fillColor Color.red))).draw
// The top three circles show the effect of changing lightness, and the bottom 
// three squares show the effect of changing saturation.

/*
The methods Color.rgba and Color.hsla have a fourth parameter that is a 
Normalized alpha value.
**/

((circle(40) fillColor (Color.red.alpha(0.5.normalized))) beside
 (circle(40) fillColor (Color.blue.alpha(0.5.normalized))) on
 (circle(40) fillColor (Color.green.alpha(0.5.normalized)))).draw

// Circles with alpha of 0.5 showing transparency


// Exercises:

// Create three triangles, arranged in a triangle, with complementary colors. 
// Complementary colors are colors that are similar in hue.

// ?? that's not what "complementary" means in color theory. /shrug

(triangle(60.0, 60.0) fillColor Color.darkSlateBlue beside 
    triangle(60.0, 60.0) fillColor Color.darkSlateBlue.spin(15.degrees) below 
    triangle(60.0, 60.0) fillColor Color.darkSlateBlue.spin(-15.0.degrees)).draw

// below is solution code that fine-tunes other attributes not specified in 
// excercise

((triangle(40, 40)
       lineWidth 6.0
       lineColor Color.darkSlateBlue
       fillColor (Color.darkSlateBlue lighten 0.3.normalized saturate 
        0.2.normalized spin 10.degrees)) above
  ((triangle(40, 40)
      lineWidth 6.0
      lineColor (Color.darkSlateBlue spin (-30.degrees))
      fillColor (Color.darkSlateBlue lighten 0.3.normalized saturate 
        0.2.normalized spin (-20.degrees))) beside
     (triangle(40, 40)
        lineWidth 6.0
        lineColor (Color.darkSlateBlue spin (30.degrees))
        fillColor (Color.darkSlateBlue lighten 0.3.normalized saturate 
            0.2.normalized spin (40.degrees))))).draw