package sifive.blocks.pio.regmap

import chisel3._

case class pioParams(
  addrWidth: Int = 12,
  dataWidth: Int = 32,
  pioWidth: Int = 32
)