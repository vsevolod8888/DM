package com.example.dailymaksan;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
@Database(entities = {FoodsItems.class}, version = 1)    //связующий класс. Связывает интерфейс где я описываю запросы с самой
public abstract class AppDataBase extends RoomDatabase {  //с классом foodsItems  //{FoodsItems.class, след таблица}

    public abstract  FoodsItemDao foodsitemDao();      // добавляем снизу дао, если есть еще таблицы

}
