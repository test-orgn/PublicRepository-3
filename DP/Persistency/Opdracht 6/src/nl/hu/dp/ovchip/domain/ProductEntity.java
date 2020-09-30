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
    public int id;

    public String naam, beschrijving;
    public float prijs;

    @OneToMany(mappedBy = "productEntity")
    List<LinkOvProduct> ovEntities = new ArrayList<>();

    @Override
    public String toString()
    {
        return "Product {" + id + ", " + naam + ", " + beschrijving + ", " + prijs + "}";
    }
}