package nl.hu.dp.ovchip.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "product")
public class ProductEntity implements Serializable
{
    @Id
    @Column(name = "product_nummer")
    private int id;

    private String naam, beschrijving;
    private float prijs;

    @ManyToMany
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = @JoinColumn(name = "kaart_nummer"),
            inverseJoinColumns = @JoinColumn(name = "product_nummer"))
    private List<OvEntity> ovEntities = new ArrayList<>();

    public ProductEntity()
    {

    }

    @Override
    public String toString()
    {
        return "Product {" + id + ", " + naam + ", " + beschrijving + ", " + prijs + "}";
    }

    public ProductEntity(int id, String naam, String beschrijving, float prijs, List<OvEntity> ovEntities)
    {
        this.id = id;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovEntities = ovEntities;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public List<OvEntity> getOvEntities()
    {
        return ovEntities;
    }

    public void setOvEntities(List<OvEntity> ovEntities)
    {
        this.ovEntities = ovEntities;
    }
}