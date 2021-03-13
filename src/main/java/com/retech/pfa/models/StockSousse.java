package com.retech.pfa.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "StockSousse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockSousse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;

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
}
