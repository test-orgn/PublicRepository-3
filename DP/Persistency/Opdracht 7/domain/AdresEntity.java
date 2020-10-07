package nl.hu.dp.ovchip.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "adres")
public class AdresEntity implements Serializable
{
    @Id
    @Column(name = "adres_id")
    private int id;

    private String postcode, huisnummer, straat, woonplaats;

    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private ReizigerEntity reizigerEntity;

    public AdresEntity()
    {

    }

    @Override
    public String toString()
    {
        return "Adres {#" + id + " " + straat + " " + huisnummer + " " + postcode + " " + woonplaats + "}";
    }

    public AdresEntity(int id, String postcode, String huisnummer, String straat, String woonplaats, ReizigerEntity reizigerEntity)
    {
        this.id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reizigerEntity = reizigerEntity;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getHuisnummer()
    {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer)
    {
        this.huisnummer = huisnummer;
    }

    public String getStraat()
    {
        return straat;
    }

    public void setStraat(String straat)
    {
        this.straat = straat;
    }

    public String getWoonplaats()
    {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats)
    {
        this.woonplaats = woonplaats;
    }

    public ReizigerEntity getReizigerEntity()
    {
        return reizigerEntity;
    }

    public void setReizigerEntity(ReizigerEntity reizigerEntity)
    {
        this.reizigerEntity = reizigerEntity;
    }
}
