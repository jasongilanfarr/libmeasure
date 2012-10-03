/* ******************************************************* *
 * Copyright 2012 - Jason Gilanfarr - All Rights Reserved  *
 * ******************************************************* */

package org.thisamericandream.measure

/** Quantity representing a tempurature */
trait Tempurature extends Quantity[Tempurature]

/** Standard Unit for Kelvin Degrees */
case class Kelvin(val value: Number) extends Tempurature with SI {
  val symbol = "°K"

  def as(typeName: Class[_ <: Tempurature]): Tempurature = {
    typeName match {
      case i if i == classOf[Celcius] =>
        Celcius(value.doubleValue() - 273.15)
      case i if i == classOf[Fahrenheit] =>
        Fahrenheit(value.doubleValue() * 9 / 5 - 459.67)
      case i if i == classOf[Kelvin] =>
        this
      case _ => throw new UnsupportedOperationException
    }
  }
}

/** Standard Unit for Celcius Degrees */
case class Celcius(val value: Number) extends Tempurature with SI {
  val symbol = "°C"
  def as(typeName: Class[_ <: Tempurature]): Tempurature = {
    typeName match {
      case i if i == classOf[Celcius] =>
        this
      case i if i == classOf[Fahrenheit] =>
        Fahrenheit(value.doubleValue() * 9 / 5 + 32)
      case i if i == classOf[Kelvin] =>
        Kelvin(value.doubleValue + 273.15)
      case _ => throw new UnsupportedOperationException
    }
  }
}

/** Standard Unit for Fahrenheit Degrees */ 
case class Fahrenheit(val value: Number) extends Tempurature with USCustomary {
  def symbol = "°F"
  def as(typeName: Class[_ <: Tempurature]): Tempurature = {
    typeName match {
      case i if i == classOf[Celcius] =>
        Celcius((value.doubleValue() - 32) * 5 / 9)
      case i if i == classOf[Fahrenheit] =>
        this
      case i if i == classOf[Kelvin] =>
        Kelvin((value.doubleValue() + 459.67) * 5 / 9)
      case _ => throw new UnsupportedOperationException
    }
  }
}
