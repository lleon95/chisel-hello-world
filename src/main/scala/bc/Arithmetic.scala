// See README.md for license details.

package fal_arithmetic

import chisel3._
import chisel3.experimental.BaseModule

trait MyAdder {
    def in1: UInt
    def in2: UInt
    def out: UInt
}

class Adder(w: Int) extends RawModule with MyAdder {
    val in1 = IO(Input(UInt(w.W)))
    val in2 = IO(Input(UInt(w.W)))
    val out = IO(Output(UInt(w.W)))
    out := in1 + in2
}

class Subs(w: Int) extends RawModule with MyAdder {
    val in1 = IO(Input(UInt(w.W)))
    val in2 = IO(Input(UInt(w.W)))
    val out = IO(Output(UInt(w.W)))
    out := in1 - in2
}

class FalBinaryCounter[T <: BaseModule with MyAdder] (w: Int, op: => T) extends Module {
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
