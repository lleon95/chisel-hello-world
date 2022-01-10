package bincounter

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

// INFO: https://www.chisel-lang.org/chiseltest/

class BinaryCounterTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "BinaryCounter"

  it should "count" in {
    test(new BinaryCounter(4)) { dut =>
      println("Counter clearing...")

      dut.io.clr.poke(true.B)
      dut.clock.step(3)
      dut.io.out.expect(0.U)
      dut.io.clr.poke(false.B)
      dut.clock.step(4)
      dut.io.out.expect(4.U)

      for (iter <- 0 until 24) {
        dut.clock.step(1)
        println("Val : " + dut.io.out.peek().litValue)
      }
    }
  }
}
