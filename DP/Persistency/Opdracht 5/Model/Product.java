package Model;

import java.util.ArrayList;
import java.util.List;

public class Product
{
    private int product_nummer;
    private String naam, beschrijving;
    private float prijs;

    private List<OVChipkaart> ovKaarten = new ArrayList<>();

    public Product(int product_nummer, String naam, String beschrijving, float prijs)
    {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    @Override
    public String toString()
    {
        return "Product {" + product_nummer + ", " + naam + ", " + beschrijving + ", " + prijs + "}";
    }

    public void addOvKaart(OVChipkaart kaart)
    {
        if(!ovKaarten.contains(kaart))
        {
            ovKaarten.add(kaart);
        }
    }

    public void removeOvKaart(OVChipkaart kaart)
    {
        if(ovKaarten.contains(kaart))
        {
            ovKaarten.remove(kaart);
        }
    }

    public List<OVChipkaart> getOvKaarten()
    {
        return ovKaarten;
    }

    public int getProduct_nummer()
    {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer)
    {
        this.product_nummer = product_nummer;
    }

    public String getNaam()
    {
        return naam;
    }

    public void setNaam(String naam)
    {
        this.naam = naam;
    }

    public String getBeschrijving()
    {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving)
    {
        this.beschrijving = beschrijving;
    }

    public float getPrijs()
    {
        return prijs;
    }

    public void setPrijs(float prijs)
    {
        this.prijs = prijs;
    }
}