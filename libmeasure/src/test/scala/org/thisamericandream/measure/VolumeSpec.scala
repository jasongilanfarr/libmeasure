/* ******************************************************* *
 * Copyright 2012 - Jason Gilanfarr - All Rights Reserved  *
 * ******************************************************* */
package org.thisamericandream.measure

import org.scalatest.FunSpec

/**
 * Test of the Volumes.
 */
class VolumeSpec extends FunSpec {
  case class FakeVolume extends Volume { val value: Number = 0; val symbol = "fake" }
  describe("Volumes") {
    describe("when converted") {
      describe("between the same units") {
        it("should be the same") {
          // test borders and middle: Teaspoon, Cup, Gallon
          assert(Teaspoon(1).as(classOf[Teaspoon]).getClass === classOf[Teaspoon])
          assert(Teaspoon(1).as(classOf[Teaspoon]).value == 1)
          assert(Cup(1).as(classOf[Cup]).getClass === classOf[Cup])
          assert(Cup(1).as(classOf[Cup]).value == 1)
          assert(Gallon(1).as(classOf[Gallon]).getClass === classOf[Gallon])
          assert(Gallon(1).as(classOf[Gallon]).value == 1)
        }
      }
      // test all of the conversions from teaspoon all the way to gallon.
      describe("between teaspoon and ") {
        it ("a tablespoon should be a factor of 3") {
          assert(Teaspoon(3).as(classOf[Tablespoon]).value === 1)
          assert(Tablespoon(1).as(classOf[Teaspoon]).value === 3.0)
        }
        it("a fluid ounce should be a factor of 6") {
          assert(Teaspoon(3).as(classOf[FluidOunce]).value === 0.5)
          assert(FluidOunce(1).as(classOf[Teaspoon]).value === 6.0)
        }
        it("a cup should be a factor of 48") {
          assert(Teaspoon(48).as(classOf[Cup]).value === 1)
          assert(Cup(0.5).as(classOf[Teaspoon]).value === 24)
        }
        it("a pint should be a factor of 96") {
          assert(Teaspoon(96).as(classOf[Pint]).value === 1)
          assert(Pint(0.5).as(classOf[Teaspoon]).value === 48)
        }
        it("a quart should be a factor of 192") {
          assert(Teaspoon(192).as(classOf[Quart]).value === 1)
          assert(Quart(0.5).as(classOf[Teaspoon]).value === 96)
        }
        it("a gallon should be a factor of 768") {
          assert(Teaspoon(768).as(classOf[Gallon]).value === 1)
          assert(Gallon(0.5).as(classOf[Teaspoon]).value == 384)
        }
        it("some other fake class should throw UnsupportedOperationException") {
          intercept[UnsupportedOperationException] {
            Gallon(0.5).as(classOf[FakeVolume])
          }
        }
      }
      describe("between two fake volumes") {
        it("should throw UnsupportedOperationException") {
          intercept[UnsupportedOperationException] {
            FakeVolume().as(classOf[FakeVolume])
          }
        }
      }
    }
  }
}
