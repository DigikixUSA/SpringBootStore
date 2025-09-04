package com.example.demo.domain;

import com.example.demo.validators.ValidDeletePart;
import com.example.demo.validators.ValidMinMax;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Entity
@ValidDeletePart
@ValidMinMax
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;
    @Min(value = 0, message = "Price value must be positive")
    double price;

    @Min(value = 0, message = "Minimum value must be positive")
    int min;
    @Min(value = 0, message = "Maximum value must be positive")
    int max;
    @Min(value = 0, message = "Inventory value must be greater than min")
    @Max(value = 100, message = "Inventory value must be less than max")
    int inv;


    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
        //System.out.println("blank constructor used");
    }

    public Part(String name, double price, int inv) {
        //System.out.println("name,price,inv constructoir used");
        this.name = name;
        this.price = price;

        setMin(0);
        setMax(255);
        this.setInv(inv);
    }

    public Part(long id, String name, double price, int inv) {
        //System.out.println("id,name,price,inv constructor used");
        this.id = id;
        this.name = name;
        this.price = price;

        setMin(0);
        setMax(255);
        this.setInv(inv);
    }
    public Part(long id, String name, double price, int inv, int min, int max) {
        //System.out.println("id,name,price,inv,min,max constructor used");
        this.id = id;
        this.name = name;
        this.price = price;

        setMin(min);
        setMax(max);
        this.setInv(inv);
    }


    public int getMax() {
        return max;
    }

    public void setMax(@Min(value = 0, message = "Maximum value must be positive") int max) {
        //System.out.println("max set to:"+max);
        this.max = max;
        //if (this.inv > this.max) this.inv=this.max;
    }
    public int getMin() {
        return min;
    }

    public void setMin(@Min(value = 0, message = "Minimum value must be positive") int min) {

        this.min = min;
        //if (this.inv < min) this.inv=this.min;
    }

    public boolean invValid(){
        if (inv >= min && inv <= max)
        {
            return true;
        }
        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv2) {
        //System.out.println("max="+max+" inv2="+inv2+" min="+min);
        //if (inv2 < min) inv2 = min;
        //if (max >0) {
        //    if (inv2 > max) inv2 = max;
        //}

        this.inv = inv2;
        //System.out.println("*** this.inv now equals:"+this.inv);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
