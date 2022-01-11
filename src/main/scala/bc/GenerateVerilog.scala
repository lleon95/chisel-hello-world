package fal_arithmetic

import chisel3.stage.ChiselStage

object MakeVerilog extends App {
  val bw = args(0).toInt
  (new ChiselStage).emitVerilog(new FalBinaryCounter(bw, new Adder))
}
