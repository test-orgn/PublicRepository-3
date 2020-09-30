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
    public int id;

    public String voorletters, tussenvoegsel, achternaam;
    public LocalDate geboortedatum;

    @OneToOne(mappedBy = "reizigerEntity")
    public AdresEntity adresEntity;

    @OneToMany(mappedBy = "reizigerEntity")
    public List<OvEntity> ovEntities = new ArrayList<>();

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
}