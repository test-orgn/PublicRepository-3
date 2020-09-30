package nl.hu.dp.ovchip.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ov_chipkaart_product")
public class LinkOvProduct implements Serializable
{
    @Id
    @JoinColumn(name = "kaart_nummer")
    @ManyToOne
    public OvEntity ovEntity;

    @Id
    @JoinColumn(name = "product_nummer")
    @ManyToOne
    public ProductEntity productEntity;

    @Override
    public String toString()
    {
        return "Koppeltabel {OV:" + ovEntity.id + ", Product:" + productEntity.id + "}";
    }
}