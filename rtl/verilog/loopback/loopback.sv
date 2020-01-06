module loopback #(
  parameter pioWidth = 10
) (
  inout                       b_test,
  input        [pioWidth-1:0] odata,
  input        [pioWidth-1:0] oenable,
  output logic [pioWidth-1:0] idata
);

// assign idata = odata ^ oenable;
  
   assign b_test = oenable ? odata : 1'bz;
   assign idata = odata ^ oenable;

endmodule
