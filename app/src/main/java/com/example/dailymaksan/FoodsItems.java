package com.example.dailymaksan;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
//Аннотацией Entity помечаем объект, который мы хотим хранить в базе данных.
public class FoodsItems {            //В качестве имени таблицы будет использовано имя класса. А поля таблицы будут созданы в соответствии с полями класса.

    @PrimaryKey(autoGenerate = true)
    //Аннотацией PrimaryKey мы помечаем поле, которое будет ключом в таблице
    public Integer id;               //autoGenerate = true id+1 при создании нового объекта

    public String pozitionName;
    public Integer amount;           //позиции, ктоторые должны сохраняться в базе данных

    public FoodsItems(String pozitionName, Integer amount) {
        this.pozitionName = pozitionName;
        this.amount = amount;
    }
}
