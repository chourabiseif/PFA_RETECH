package com.retech.pfa.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="bom" )
//Lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Bom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value =AccessLevel.NONE)
    private long id;

    private long Code;

    private long SAP_Code;

    private String brand ;

    private String Model;

    private String Color;

    private String MaterialdescriptionChinese;

    private String MaterialdescriptionEnglish;

    private long BOMlevel;

    private String ComponentdescriptionChinese;

    private String ComponentdescriptionEnglish;

    private long UnitQuantity;

    private String BOMCategory;

    private String Procurementtype;

    private long MaterialGroupCode;

    private String MaterialGroupChinese;

}
