/* ******************************************************* *
 * Copyright 2012 - Jason Gilanfarr - All Rights Reserved  *
 * ******************************************************* */
package org.thisamericandream.measure

import org.scalatest.FunSpec

/**
 *
 */
class TempuratureSpec extends FunSpec {
  case class FakeTempurature extends Tempurature {
    val value: Number = 0
    val symbol = "fake tempurature"
    def as(typeName: Class[_ <: Tempurature]) : Tempurature = {
      throw new UnsupportedOperationException
    }
  }
  
  describe("Tempuratures") {
    describe("when converted") {
      describe("between the same units") {
        it("should be the same") {
          assert(Fahrenheit(0).as(classOf[Fahrenheit]).getClass === classOf[Fahrenheit])
          assert(Fahrenheit(0).as(classOf[Fahrenheit]).value === 0)
          assert(Kelvin(0).as(classOf[Kelvin]).getClass === classOf[Kelvin])
          assert(Kelvin(0).as(classOf[Kelvin]).value === 0)
          assert(Celcius(0).as(classOf[Celcius]).getClass === classOf[Celcius])
          assert(Celcius(0).as(classOf[Celcius]).value === 0)
        }
      }
      describe("between different units") {
        it("should convert 0 celcius to 32 f") {
          assert(Celcius(0).as(classOf[Fahrenheit]).value === 32)
        }
        it("should convert 212 f to 100 celcius") {
          assert(Fahrenheit(212).as(classOf[Celcius]).value === 100)
        }
        it("should convert 0 kelvin to -273.15 celcius") {
          assert(Kelvin(0).as(classOf[Celcius]).value == -273.15)
        }
      }
    }
  }
}