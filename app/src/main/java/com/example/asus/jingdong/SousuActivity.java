package com.example.asus.jingdong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bean.Bean;

public class SousuActivity extends AppCompatActivity implements View.OnClickListener{
     private ImageView sou,fh;
     private EditText head_sou;
     private TextView ss;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sousu);
        initview();

    }

    private void initview() {
        sou= (ImageView) findViewById(R.id.sou);
        fh= (ImageView) findViewById(R.id.fh);
        head_sou= (EditText) findViewById(R.id.head_sou);
        ss= (TextView) findViewById(R.id.ss);
        ss.setOnClickListener(this);
        fh.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ss:
                Intent intent=new Intent(SousuActivity.this,ZILEIActivity.class);
                name=head_sou.getText().toString();
                intent.putExtra("name",name);
                System.out.println("====name"+name);
                startActivity(intent);
                break;
            case R.id.fh:
                finish();
                break;
        }
    }

}
