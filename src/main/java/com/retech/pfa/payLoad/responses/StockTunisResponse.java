package com.retech.pfa.payLoad.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockTunisResponse {

    private String CustomerCode;
    // declared string cause in excel file its a number converted to string
    private String SerialNo1;
    // declared string cause in excel file its a number converted to string
    private String SerialNo2;

    // declared string cause in excel file its a number converted to string
    private String MaterialCode;

    private String MaterialDesc;
    private long Quantity;
    private String EmployeeName;
    private String PartnerName;
    private String FirstName;
    private String StoreLocation;
    private String BinCode;
    private float Price;
    private Date GRNDate;
    private String WarrantyStatus;
    private  String Classification;
    // model and brand and color from bom
    private List<String> Models;
}
