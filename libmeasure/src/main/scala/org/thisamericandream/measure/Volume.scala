/* ******************************************************* *
 * Copyright 2012 - Jason Gilanfarr - All Rights Reserved  *
 * ******************************************************* */

package org.thisamericandream.measure

/**
 * Base trait for Volumes. There could be odd "volumes" such as "5 egg yolks"
 * which need to do their own conversions, so this can still be extended.
 */
trait Volume extends Quantity[Volume] {
  /**
   *  List of conversion multipliers/divisors by index of implemented us volumes
   *
   *  smallVolume->bigVolume: slice(indexOf(smallVolume, bigVolume)).foldLeft(value)(_ * _)
   *  bigVolume->smallVolume: slice(indexOf(bigVolume, smallVolume)).foldLeft(value)(_ * _)
   */
  private val usConversionMultipliers = Seq(
    3, // tspToTbsp
    2, // tbspToFloz
    8, // FlozToCup
    2, // CupToPint
    2, // PintToQuart
    4 // QuartToGallon
  )

  // Index of the classes of implemented us volumes
  private val usConversionSequence = IndexedSeq(
    classOf[Teaspoon],
    classOf[Tablespoon],
    classOf[FluidOunce],
    classOf[Cup],
    classOf[Pint],
    classOf[Quart],
    classOf[Gallon]
  ).asInstanceOf[IndexedSeq[Class[Volume]]]
  
  // by default, convert using the internal conversions.
  def as(typeName: Class[_ <: Volume]): Volume = convertUS(this, typeName)
    
  // converts known US units. Throws UnsupportedOperationException if the
  // units can not be converted.
  private def convertUS(from: Volume, to: Class[_ <: Volume]): Volume = {

    val startIndex = usConversionSequence.indexOf(from.getClass())
    val endIndex = usConversionSequence.indexOf(to)

    if (startIndex == -1 || endIndex == -1) {
      throw new UnsupportedOperationException
    }

    if (startIndex == endIndex) {
      return from
    } else if (startIndex < endIndex) {
      val newAmount = usConversionMultipliers.slice(startIndex, endIndex).foldLeft(from.value.doubleValue)((a, b) => a / b)
      from.getClass.getConstructor(classOf[Number]).newInstance(newAmount.asInstanceOf[Object])
    } else if (startIndex > endIndex) {
      val newAmount = usConversionMultipliers.slice(endIndex, startIndex).foldLeft(from.value.doubleValue)((a, b) => a * b)
      from.getClass.getConstructor(classOf[Number]).newInstance(newAmount.asInstanceOf[Object])
    } else {
      throw new UnsupportedOperationException
    }
  }
}

/** US Customary Teaspoon */
case class Teaspoon(val value: Number) extends Volume with USCustomary{
  val symbol: String = "tsp."
}

/** US Customary Tablespoon */
case class Tablespoon(val value: Number) extends Volume with USCustomary {
  val symbol = "tbsp."
}

/** US Customary Fluid Ounce */
case class FluidOunce(val value: Number) extends Volume with USCustomary {
  val symbol = "fl oz"
}

/** US Customary Cup */
case class Cup(val value: Number) extends Volume with USCustomary {
  val symbol = "c"
}

/** US Customary Pint */
case class Pint(val value: Number) extends Volume with USCustomary {
  val symbol = "pt"
}

/** US Customary Quart */
case class Quart(val value: Number) extends Volume with USCustomary {
  val symbol = "qt"
}

/** US Customary Gallon */
case class Gallon(val value: Number) extends Volume with USCustomary {
  val symbol = "gal"
}