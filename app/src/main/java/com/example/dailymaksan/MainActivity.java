package com.example.dailymaksan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Dao;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton addpozition;
    int DialogId = 1;
    RecyclerView recyclerView;
    MyRecycleViewAdapter adapter;
    FoodsItemDao m ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addpozition = findViewById(R.id.floatingActionButton);       // круглая кнопка "+" снизу
       addpozition.setOnClickListener(this);
       recyclerView = findViewById(R.id.recycleview);
        LinearLayoutManager llm = new LinearLayoutManager(this); //LinearLayoutManager настраивает отображение обычным списком
        recyclerView.setLayoutManager(llm);                              //присваиваем
        m = App.getInstance().getDatabase().foodsitemDao();

        adapter = new MyRecycleViewAdapter(m.getAll(), new MyClickInterface() {
            @Override
            public void onPlusClick(Integer count, Integer id) { // count это информация из эдит текста
                FoodsItems item = m.getById(id);
                item.amount = item.amount+count;
                m.insert(item);
                adapter.setItems(m.getAll());
                reloadItems();
            }

            @Override
            public void onMinusclick(Integer count, Integer id) {
                FoodsItems item = m.getById(id);
                item.amount = item.amount-count;
                m.insert(item);
                adapter.setItems(m.getAll());
                reloadItems();

            }

            @Override
            public void onDelClick(Integer pozition) {
                m.delete((int)pozition);
                adapter.setItems(m.getAll());
                reloadItems();

            }
        });
        recyclerView.setAdapter(adapter);




    }

    @Override
    public void onClick(View v) {
        showDialog(DialogId);


    }
    protected Dialog onCreateDialog(int id) {                                  // В onCreateDialog мы создаем диалог. Для этого используется класс AlertDialog.Builder
        if(id ==DialogId){
            AlertDialog.Builder adb = new AlertDialog.Builder(this);   //

            adb.setTitle("");               // заголовок                       // Мы указываем заголовок

            adb.setMessage("Введите название позиции");      // сообщение      //Мы указываем текст сообщения
            final EditText input = new EditText(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            adb.setView(input);

            // кнопка положительного ответа
            adb.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    m.insert(new FoodsItems(input.getText().toString(),0));  ///добавляем в ArrayList foods объекты класса FoodsItems
                    adapter.setItems(m.getAll());
                    reloadItems();
                }
            });                      // и кнопки
            // кнопка нейтрального ответа
            adb.setNeutralButton(R.string.cancel, myClickListener);
            // создаем диалог
            return adb.create();}

        return super.onCreateDialog(id);
    }
    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {                // ???
        public void onClick(DialogInterface dialog, int which) {  //int which это ID нажатой кнопки
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:

                    break;
                // нейтральная кнопка
                case Dialog.BUTTON_NEUTRAL:
                    break;
            }
        }
    };
    void reloadItems(){
        adapter.notifyDataSetChanged();

    }

    //myClickListener реализует интерфейс DialogInterface.OnClickListener и в нашем случае является общим для всех кнопок.
    //В нем мы проверяем, какая кнопка была нажата:
    //


}





