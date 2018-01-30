package com.example.ssoft_13.parsabledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ssoft_13.parsabledemo.Model.ContactInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv=(TextView)findViewById(R.id.textview);
        Intent i  = getIntent();

        /* to get single value */
        //ContactInfo ci = i.getExtras().getParcelable("contact");
        //tv.setText(ci.toString());

       //ContactInfo ciArr2 = new Gson().fromJson(getIntent().getStringExtra("json"),ContactInfo.class);

        Type collectionType = new TypeToken<Collection<ContactInfo>>(){}.getType();
        Collection<ContactInfo> ints2 = new Gson().fromJson(getIntent().getStringExtra("json"),collectionType);
        List<ContactInfo> ciArr2 = new ArrayList<>();
        ciArr2.add((ContactInfo) ints2);
        /* to get entire arraylist */
        List<ContactInfo> ciArr = (List) i.getParcelableArrayListExtra("contact");
        StringBuffer buffer = new StringBuffer();
        for (int x=0; x < ciArr.size(); x++) {
            buffer.append(ciArr.get(x).toString() + "\r\n");
        }
        tv.setText(buffer.toString());
    }
}
