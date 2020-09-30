package nl.hu.dp.ovchip.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "adres")
public class AdresEntity implements Serializable
{
    @Id
    @Column(name = "adres_id")
    public int id;

    public String postcode, huisnummer, straat, woonplaats;

    @OneToOne
    @JoinColumn(name = "reiziger_id")
    public ReizigerEntity reizigerEntity;

    @Override
    public String toString()
    {
        return "Adres {#" + id + " " + straat + " " + huisnummer + " " + postcode + " " + woonplaats + "}";
    }
}
