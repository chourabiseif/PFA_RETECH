package com.retech.pfa.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CountryStock")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryStock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long id;

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
}
