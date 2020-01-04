// Generated Code
// Please DO NOT EDIT
package sifive.blocks.pio.regmap.CSR.csrAddressBlock

import chisel3._

import freechips.rocketchip.amba.axi4.HasAXI4ControlRegMap
import freechips.rocketchip.config.Parameters
import freechips.rocketchip.diplomacy.LazyModuleImp
import freechips.rocketchip.regmapper._
import freechips.rocketchip.tilelink.HasTLControlRegMap

import sifive.blocks.pio.regmap.pioParams

class ODATARegisterBundle(
  val addrWidth: Int,
  val dataWidth: Int,
  val pioWidth: Int
) extends Bundle {
  val data = Output(UInt(pioWidth.W))
}

class OENABLERegisterBundle(
  val addrWidth: Int,
  val dataWidth: Int,
  val pioWidth: Int
) extends Bundle {
  val data = Output(UInt(pioWidth.W))
}

class IDATARegisterBundle(
  val addrWidth: Int,
  val dataWidth: Int,
  val pioWidth: Int
) extends Bundle {
  val data = Input(UInt(pioWidth.W))
}

class csrAddressBlockAddressBlockBundle(val params: pioParams) extends Bundle {
  val ODATA = new ODATARegisterBundle(
    addrWidth = params.addrWidth,
    dataWidth = params.dataWidth,
    pioWidth = params.pioWidth
  )
  val OENABLE = new OENABLERegisterBundle(
    addrWidth = params.addrWidth,
    dataWidth = params.dataWidth,
    pioWidth = params.pioWidth
  )
  val IDATA = new IDATARegisterBundle(
    addrWidth = params.addrWidth,
    dataWidth = params.dataWidth,
    pioWidth = params.pioWidth
  )
}

object csrAddressBlockRegRouterBase {
  def deviceTreeName: String = "pio-csrAddressBlock"
  def deviceTreeCompat: Seq[String] = Seq("sifive,pio-0.1.0")

  object resetValues {
    object ODATA {
      def data: UInt = 0.U
    }
    
    object OENABLE {
      def data: UInt = 0.U
    }
    
    object IDATA {
      def data: UInt = 0.U
    }
  }
}

abstract class csrAddressBlockRegRouter(
  busWidthBytes: Int,
  baseAddress: Long,
  componentParams: pioParams)(implicit p: Parameters)
  extends IORegisterRouter(
    RegisterRouterParams(
      name = csrAddressBlockRegRouterUser.deviceTreeName,
      compat = csrAddressBlockRegRouterUser.deviceTreeCompat,
      base = baseAddress,
      beatBytes = busWidthBytes),
    new csrAddressBlockAddressBlockBundle(componentParams)) {

  private def padFields(fields: (Int, RegField)*) = {
    var previousOffset = 0
    var previousField: Option[RegField] = None
  
    fields.flatMap { case (fieldOffset, field) =>
      val padWidth = fieldOffset - previousOffset
      require(padWidth >= 0,
        if (previousField.isDefined) {
          s"register fields at $previousOffset and $fieldOffset are overlapping"
        } else {
          s"register field $field has a negative offset"
        })
  
      previousOffset = fieldOffset
      previousField = Some(field)
  
      if (padWidth > 0) {
        Seq(RegField(padWidth), field)
      } else {
        Seq(field)
      }
    }
  }

  lazy val module = new LazyModuleImp(this) {
    val resetValue = Wire(port.cloneType.asOutput)
    resetValue.ODATA.data := csrAddressBlockRegRouterUser.resetValues.ODATA.data
    resetValue.OENABLE.data := csrAddressBlockRegRouterUser.resetValues.OENABLE.data
    resetValue.IDATA.data := csrAddressBlockRegRouterUser.resetValues.IDATA.data

    val register = RegInit(resetValue)
    port.ODATA.data := register.ODATA.data
    port.OENABLE.data := register.OENABLE.data
    register.IDATA.data := port.IDATA.data

    val mapping = {
      val addrWidth = componentParams.addrWidth
      val dataWidth = componentParams.dataWidth
      val pioWidth = componentParams.pioWidth
      Seq(
        0 -> RegFieldGroup("ODATA", Some("This drives the output data pins."), padFields(
          0 -> RegField(pioWidth, register.ODATA.data, RegFieldDesc("data", "")))),
        4 -> RegFieldGroup("OENABLE", Some("This determines whether the pin is an input or an output. If the data direction bit is a 1, then the pin is an input."), padFields(
          0 -> RegField(pioWidth, register.OENABLE.data, RegFieldDesc("data", "")))),
        8 -> RegFieldGroup("IDATA", Some("This is driven by the input data pins."), padFields(
          0 -> RegField.r(pioWidth, register.IDATA.data, RegFieldDesc("data", "")))))
    }

    regmap(mapping:_*)
  }
}

class csrAddressBlockTLRegMap(busWidthBytes: Int, baseAddress: Long, componentParams: pioParams)(implicit p: Parameters)
  extends csrAddressBlockRegRouter(busWidthBytes, baseAddress, componentParams) with HasTLControlRegMap

class csrAddressBlockAXI4RegMapAXI4RegMap(busWidthBytes: Int, baseAddress: Long, componentParams: pioParams)(implicit p: Parameters)
  extends csrAddressBlockRegRouter(busWidthBytes, baseAddress, componentParams) with HasAXI4ControlRegMap