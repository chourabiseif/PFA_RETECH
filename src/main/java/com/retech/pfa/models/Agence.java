package com.retech.pfa.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="agences" )
//Lombok annotations
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String nom;
    @NonNull
    private String adresse;
    @NonNull
    private String telephone;

    /*public Agence() {
    }

    public Agence(Long id, String nom, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    code replaced with lombok*/
}
