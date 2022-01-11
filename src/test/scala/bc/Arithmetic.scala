package fal_arithmetic

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3.stage.ChiselStage

// INFO: https://www.chisel-lang.org/chiseltest/

class FalBinaryCounterTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "BinaryCounter"
  
  it should "count down" in {
    test(new FalBinaryCounter(4, new Subs)) { dut =>
      println("Counter clearing...")

      dut.io.clr.poke(true.B)
      dut.clock.step(3)
      dut.io.out.expect(0.U)
      dut.io.clr.poke(false.B)
      dut.clock.step(4)

      for (iter <- 0 until 24) {
        dut.clock.step(1)
        println("Val FAL: " + dut.io.out.peek().litValue)
      }
    }
  }
  it should "count up" in {
    test(new FalBinaryCounter(4, new Adder)) { dut =>
      println("Counter clearing...")

      dut.io.clr.poke(true.B)
      dut.clock.step(3)
      dut.io.out.expect(0.U)
      dut.io.clr.poke(false.B)
      dut.clock.step(4)

      for (iter <- 0 until 24) {
        dut.clock.step(1)
        println("Val FAL: " + dut.io.out.peek().litValue)
      }
    }
  }

}
