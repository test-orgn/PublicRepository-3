package nl.hu.dp.ovchip.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "reiziger")
public class ReizigerEntity implements Serializable
{
    @Id
    @Column(name = "reiziger_id")
    private int id;

    private String voorletters, tussenvoegsel, achternaam;
    private LocalDate geboortedatum;

    @OneToOne(mappedBy = "reizigerEntity")
    private AdresEntity adresEntity;

    @OneToMany(mappedBy = "reizigerEntity")
    private List<OvEntity> ovEntities = new ArrayList<>();

    public ReizigerEntity()
    {

    }

    @Override
    public String toString()
    {
        String result = "";
        result += "Reiziger {#" + id + ": " + voorletters + ". " + (tussenvoegsel == null ? "" : tussenvoegsel + " ") +
                achternaam + " " + geboortedatum + (adresEntity == null ? "" : (", " + adresEntity));

        for(OvEntity ovKaart : ovEntities)
        {
            result += (ovKaart == null) ? "" : (", " + ovKaart);
        }

        result += "}";

        return result;
    }

    public ReizigerEntity(int id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum)
    {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getVoorletters()
    {
        return voorletters;
    }

    public void setVoorletters(String voorletters)
    {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel()
    {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel)
    {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam()
    {
        return achternaam;
    }

    public void setAchternaam(String achternaam)
    {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboortedatum()
    {
        return geboortedatum;
    }

    public void setGeboortedatum(LocalDate geboortedatum)
    {
        this.geboortedatum = geboortedatum;
    }

    public AdresEntity getAdresEntity()
    {
        return adresEntity;
    }

    public void setAdresEntity(AdresEntity adresEntity)
    {
        this.adresEntity = adresEntity;
    }

    public List<OvEntity> getOvEntities()
    {
        return ovEntities;
    }

    public void setOvEntities(List<OvEntity> ovEntities)
    {
        this.ovEntities = ovEntities;
    }
}