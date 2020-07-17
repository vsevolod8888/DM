package com.example.dailymaksan;

import android.app.Application;

import androidx.room.Room;

public class App extends Application {
    public static App instance;          ///этот класс запускается самый первый в приложении

    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database")
                .allowMainThreadQueries() //можно будет делать запросы на мейн потоки
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}
