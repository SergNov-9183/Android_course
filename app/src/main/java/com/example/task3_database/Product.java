package com.example.task3_database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity()
public class Product implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "category_id")
    private String category_id;

    @ColumnInfo(name = "price")
    private String price;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_id() { return category_id; }
    public void setCategory_id(String category_id) { this.category_id = category_id; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
}
