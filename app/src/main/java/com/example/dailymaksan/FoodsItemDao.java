package com.example.dailymaksan;

import androidx.room.Dao;
import androidx.room.Delete;   //в этом интерфейсе указываем запросы для базы данных
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//В объекте Dao мы будем описывать методы для работы с базой данных. Нам нужны будут методы для получения списка FoodsItem и для добавления/изменения/удаления FoodsItem
@Dao
public interface FoodsItemDao {   //отсортировать по алфавиту по столбику pozitionname в правильном порядке(ASC)
    @Query("SELECT * FROM FoodsItems ORDER  BY pozitionName ASC")    //имя таблицы FoodsItems, SELECT используется для получения данных из определённой таблицы
    List<FoodsItems> getAll();
    @Query("SELECT * FROM foodsitems WHERE id = :id") //id, которая передается в 14 строчке.Достаю один объект по переданной id
    FoodsItems getById(long id);
    @Insert(onConflict = OnConflictStrategy.REPLACE) //если будет с такой же самой id, то оно заменит
    void insert(FoodsItems foodsitems);  //вставки Названия методов могут быть любыми. Главное - аннотации.

    @Update
    void update(FoodsItems foodsitems);  //обновления

    @Query("DELETE FROM foodsitems WHERE id = :id")
    void delete(long id );    //удаления передаем id, по которой удаляем
}
