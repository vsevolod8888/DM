package com.example.dailymaksan;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder>{
                                                 //адаптер, это Класс, который берёт ArrayList Fooditem-ов и отображает их во
   List<FoodsItems> itemslist;    // view холдерах(в трех text view xml position) itemslist это лист только для адаптера(то, что отображается на экране)
    MyClickInterface myClickListener;

    public MyRecycleViewAdapter(List<FoodsItems> items,MyClickInterface myClickListener){  //в конструктор передаю список объектов класса ITEM это то что хранится
        this.itemslist = items;                                                                //в базе данных, три столбика первых
        this.myClickListener = myClickListener;
        notifyDataSetChanged();          // notifyDataSetChanged(); говорит чтобы обновиили список согласно новым переданным данным

    }
    void setItems(List<FoodsItems>list){
        this.itemslist = list;
      //  notifyDataSetChanged();                // присваиваем адаптеру новый список и обновляем
    }
    @NonNull
    @Override
    public MyRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pozition, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(@NonNull final MyRecycleViewAdapter.ViewHolder holder, final int position) {   //onBindViewHolder вызывается, каждый раз, когда надо
        final FoodsItems item = itemslist.get(position);
        holder.tw1.setText(position+1+"");                //будет засетить данные в holder это объект класса снизу
        holder.name.setText(item.pozitionName);
        holder.tw3.setText(item.amount.toString());
        holder.plus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onPlusClick(Integer.parseInt(holder.etAdd.getText().toString()),item.id);  //данные из etAdd ,pozition - это
                // в каком элементе списка был клик(из нашего интерфейса)
                holder.etAdd.setText("");
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onMinusclick(Integer.parseInt(holder.etSubstract.getText().toString()),item.id);  //данные из etAdd ,pozition - это
                // в каком элементе списка был клик
                holder.etSubstract.setText("");
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClickListener.onDelClick(itemslist.get(position).id);  //данные из etAdd ,pozition - это
                // в каком элементе списка был клик
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemslist.size();          //метод возвр. к-во элементов, которые в списке
    }
    public class ViewHolder extends RecyclerView.ViewHolder{ //ViewHolder этто класс, который отвечаеи за одну ячейку
        TextView tw1;
        TextView name;
        TextView tw3;
        Button plus;
        Button minus;
        EditText etAdd;
        EditText etSubstract;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tw1 = (TextView)itemView.findViewById(R.id.tw1);
            name = (TextView)itemView.findViewById(R.id.tw2);
            tw3 = (TextView)itemView.findViewById(R.id.tw3);
            plus = (Button)itemView.findViewById(R.id.button_plus);
            minus = (Button)itemView.findViewById(R.id.button_minus);
            delete = (Button)itemView.findViewById(R.id.buttonDelete);
            etAdd = (EditText)itemView.findViewById(R.id.editTextAdd);
            etSubstract = (EditText)itemView.findViewById(R.id.editTextSubstract);

        }
    }
}
