package com.jonneykim.skateatrideau;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListView extends LinearLayout {
    TextView textView, textView2;

    public ListView(Context context){
        super(context);
        init(context);
    }

    public ListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_item, this, true); //여기에 populate

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);

    }

    public void setTitle(String title){
        textView.setText(title);
    }

    public void setDate(String date){
        textView2.setText(date);
    }

}
