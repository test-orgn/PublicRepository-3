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
    public int id;

    public int klasse;
    public LocalDate geldig_tot;
    public float saldo;

    @ManyToOne
    @JoinColumn(name = "reiziger_id")
    public ReizigerEntity reizigerEntity;

    @OneToMany(mappedBy = "ovEntity")
    public List<LinkOvProduct> productEntities = new ArrayList<>();

    @Override
    public String toString()
    {
        String result = "";
        result += "OV-Chipkaart {" + id + " klasse " + klasse + " saldo " + saldo + " geldig tot " + geldig_tot;

        //for(ProductEntity product : productEntities)
        {
            //result += (product == null) ? "" : (", " + product);
        }

        result += "}";

        return result;
    }
}