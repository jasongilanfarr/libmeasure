/* ******************************************************* *
 * Copyright 2012 - Jason Gilanfarr - All Rights Reserved  *
 * ******************************************************* */

package org.thisamericandream.measure

/**
 * Represents a measurable quantity.
 */
trait Quantity[Q <: Quantity[Q]] {
  /** The value of the quantity, for example, 1 Teaspoon */
  def value: Number
  /** The common symbol for the measurement, e.g. tsp for teaspoon */
  def symbol: String
  /** Conversion from the current quantity to a new quantity and unit
   * This conversion is typesafe, so a volume can't be converted to a tempurature
   * for example. 
   */
  def as(typeName: Class[_ <: Q]): Q
}

/** trait noting a US Customary Unit: teaspoon, cup, gallon... */
trait USCustomary
/** trait noting a International Standard Unit: meter, liter... */
trait SI
