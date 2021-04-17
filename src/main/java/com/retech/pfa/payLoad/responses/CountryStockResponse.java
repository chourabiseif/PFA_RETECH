package com.retech.pfa.payLoad.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryStockResponse {
    private String CountryCode;
    private String CountryName;
    private String PlantCode;
    private String PlantName;
    private String CustomerCode;
    private String CustomerName;
    private String DivisionCode;
    // declared string cause in excel file its a number converted to string
    private String MaterialCode;
    private String MaterialName;
    private String StoreCode;
    private String StoreName;
    private long TransitQty;
    private long ActualSQty;
    // model and brand and color from bom
    private List<String> Models;
}
