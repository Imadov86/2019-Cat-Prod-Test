package fetian.org.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document
@Data

public class Category {
    @Id
    private String id;
    private String name;
    @DBRef //Dans un Document de Category charger uniquement les id des produit (eviter les documents embarqué  des produit)
    private Collection<Product> products =new ArrayList<>();

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Category(String id, String name, Collection<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Collection<Product> getProducts() {
        return products;
    }
}

