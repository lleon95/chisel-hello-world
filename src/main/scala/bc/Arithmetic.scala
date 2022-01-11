// See README.md for license details.

package fal_arithmetic

import chisel3._
import chisel3.experimental.BaseModule

trait MyAdder {
    def in1: UInt
    def in2: UInt
    def out: UInt
}

class Adder extends RawModule with MyAdder {
    val in1 = IO(Input(UInt()))
    val in2 = IO(Input(UInt()))
    val out = IO(Output(UInt()))
    out := in1 + in2
}

class Subs extends RawModule with MyAdder {
    val in1 = IO(Input(UInt()))
    val in2 = IO(Input(UInt()))
    val out = IO(Output(UInt()))
    out := in1 - in2
}

class FalBinaryCounter[T <: BaseModule with MyAdder, A] (w: Int, op: => T = new Adder) extends Module {
  val io = IO(new Bundle {
    val clr = Input(Bool())
    val out = Output(UInt(w.W))
  })

  val operator = Module(op)
  val cur  = Reg(UInt(w.W))

  cur := operator.out
  operator.in1 := cur
  operator.in2 := 2.U

  when (io.clr) {
    io.out := 0.U
    cur := 0.U
  }.otherwise {
    io.out := cur
  }
}
