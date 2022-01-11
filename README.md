Chisel Project Template
=======================

Run tests:

```bash
sbt test
```

Run verilog generation:

```bash
BW=8
sbt "runMain fal_arithmetic.MakeVerilog $BW"
```

It will take the generic Operators. It will adjust the design to be in the same datawidth
