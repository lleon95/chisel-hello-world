// See README.md for license details.

package bincounter

import chisel3._

/**
  * Compute GCD using subtraction method.
  * Subtracts the smaller from the larger until register y is zero.
  * value in register x is then the GCD
  */
class BinaryCounter (w: Int) extends Module {
  val io = IO(new Bundle {
    val clr = Input(Bool())
    val out = Output(UInt(w.W))
  })

  val cur  = Reg(UInt(w.W))

  when (io.clr) {
    io.out := 0.U
    cur := 0.U
  }.otherwise {
    io.out := cur
    cur := cur + 1.U
  }
}
