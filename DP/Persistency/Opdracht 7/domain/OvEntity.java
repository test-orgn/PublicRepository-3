package nl.hu.dp.ovchip.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ov_chipkaart")
public class OvEntity implements Serializable
{
    @Id
    @Column(name = "kaart_nummer")
    private int id;

    private int klasse;
    private LocalDate geldig_tot;
    private float saldo;

    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    private ReizigerEntity reizigerEntity;

    @ManyToMany(mappedBy = "ovEntities")
    private List<ProductEntity> productEntities = new ArrayList<>();

    public OvEntity()
    {

    }

    @Override
    public String toString()
    {
        String result = "";
        result += "OV-Chipkaart {" + id + " klasse " + klasse + " saldo " + saldo + " geldig tot " + geldig_tot;

        for(ProductEntity product : productEntities)
        {
            result += (product == null) ? "" : (", " + product);
        }

        result += "}";

        return result;
    }

    public OvEntity(int id, int klasse, LocalDate geldig_tot, float saldo, ReizigerEntity reizigerEntity)
    {
        this.id = id;
        this.klasse = klasse;
        this.geldig_tot = geldig_tot;
        this.saldo = saldo;
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

    public int getKlasse()
    {
        return klasse;
    }

    public void setKlasse(int klasse)
    {
        this.klasse = klasse;
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

    public ReizigerEntity getReizigerEntity()
    {
        return reizigerEntity;
    }

    public void setReizigerEntity(ReizigerEntity reizigerEntity)
    {
        this.reizigerEntity = reizigerEntity;
    }

    public List<ProductEntity> getProductEntities()
    {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities)
    {
        this.productEntities = productEntities;
    }
}