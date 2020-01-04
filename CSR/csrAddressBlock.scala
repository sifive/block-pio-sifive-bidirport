package sifive.blocks.pio.regmap.CSR.csrAddressBlock

import chisel3._

object csrAddressBlockRegRouterUser {
  def deviceTreeName: String = csrAddressBlockRegRouterBase.deviceTreeName
  def deviceTreeCompat: Seq[String] = csrAddressBlockRegRouterBase.deviceTreeCompat

  object resetValues {
    object ODATA {
      def data: UInt =
        csrAddressBlockRegRouterBase.resetValues.ODATA.data
    }
    
    object OENABLE {
      def data: UInt =
        csrAddressBlockRegRouterBase.resetValues.OENABLE.data
    }
    
    object IDATA {
      def data: UInt =
        csrAddressBlockRegRouterBase.resetValues.IDATA.data
    }
  }
}