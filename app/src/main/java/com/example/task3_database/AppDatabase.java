package com.example.task3_database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class, Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();
    public abstract ProductDao productDao();
}
