package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaart
{
    private int kaart_nummer, klasse, reiziger_id;
    private LocalDate geldig_tot;
    private float saldo;

    private Reiziger reiziger;
    private List<Product> producten = new ArrayList<>();

    public OVChipkaart(int kaart_nummer, int klasse, int reiziger_id, LocalDate geldig_tot, float saldo)
    {
        this.kaart_nummer = kaart_nummer;
        this.klasse = klasse;
        this.reiziger_id = reiziger_id;
        this.geldig_tot = geldig_tot;
        this.saldo = saldo;
    }

    public void addProduct(Product product)
    {
        if(!producten.contains(product))
        {
            producten.add(product);
        }
    }

    public void removeProduct(Product product)
    {
        if(producten.contains(product))
        {
            producten.remove(product);
        }
    }

    public List<Product> getProducten()
    {
        return producten;
    }

    @Override
    public String toString()
    {
        return "OV-Chipkaart {" + kaart_nummer + " klasse " + klasse + " saldo " + saldo + " geldig tot " + geldig_tot + "}";
    }

    public int getKaart_nummer()
    {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer)
    {
        this.kaart_nummer = kaart_nummer;
    }

    public int getKlasse()
    {
        return klasse;
    }

    public void setKlasse(int klasse)
    {
        this.klasse = klasse;
    }

    public int getReiziger_id()
    {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id)
    {
        this.reiziger_id = reiziger_id;
    }

    public LocalDate getGeldig_tot()
    {
        return geldig_tot;
    }

    public void setGeldig_tot(LocalDate geldig_tot)
    {
        this.geldig_tot = geldig_tot;
    }

    public float getSaldo()
    {
        return saldo;
    }

    public void setSaldo(float saldo)
    {
        this.saldo = saldo;
    }

    public Reiziger getReiziger()
    {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger)
    {
        this.reiziger = reiziger;
    }
}