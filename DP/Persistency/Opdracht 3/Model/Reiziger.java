package Model;

import java.time.LocalDate;

public class Reiziger
{
    private int id;
    private String voorletters, tussenvoegsel, achternaam;
    private LocalDate geboortedatum;

    private Adres adres;

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
        return "Reiziger {#" + id + ": " + voorletters + ". " + (tussenvoegsel == null ? "" : tussenvoegsel + " ") +
                achternaam + " " + geboortedatum + (adres == null ? "" : ", " + adres) + "}";
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
}