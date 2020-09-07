package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reiziger
{
    private int id;
    private String voorletters, tussenvoegsel, achternaam;
    private LocalDate geboortedatum;

    private Adres adres;
    private List<OVChipkaart> ovKaarten = new ArrayList<>();

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboortedatum)
    {
        this.id = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
    }

    @Override
    public String toString()
    {
        String result = "";
        result += "Reiziger {#" + id + ": " + voorletters + ". " + (tussenvoegsel == null ? "" : tussenvoegsel + " ") +
                achternaam + " " + geboortedatum + (adres == null ? "" : (", " + adres));

        for(OVChipkaart ovKaart : ovKaarten)
        {
            result += (ovKaart == null) ? "" : (", " + ovKaart);
        }

        result += "}";

        return result;
    }

    public String getName()
    {
        return voorletters + " " + (tussenvoegsel == null ? "" : tussenvoegsel + " ") + achternaam;
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

    public Adres getAdres()
    {
        return adres;
    }

    public void setAdres(Adres adres)
    {
        this.adres = adres;
    }

    public OVChipkaart getOvKaart(int kaartnummer)
    {
        for (OVChipkaart ovKaart : ovKaarten)
        {
            if(ovKaart.getKaart_nummer() == kaartnummer)
            {
                return ovKaart;
            }
        }

        return null;
    }

    public void addOvKaart(OVChipkaart kaart)
    {
        if(!ovKaarten.contains(kaart))
        {
            ovKaarten.add(kaart);
        }
    }

    public List<OVChipkaart> getOvKaarten()
    {
        return ovKaarten;
    }
}