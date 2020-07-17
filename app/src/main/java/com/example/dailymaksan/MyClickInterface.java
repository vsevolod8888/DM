package com.example.dailymaksan;

public interface MyClickInterface {             //сюда пишем методы на каждый переданный клик
    void onPlusClick(Integer count,Integer pozition);
    void  onMinusclick(Integer count, Integer pozition);
    void  onDelClick(Integer pozition);
}
