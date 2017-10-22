package com.example.asus.jingdong;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import adapter.DingdanAdapter;
import bean.DD;
import common.ShareUtis;
import presenter.Presenter_A;
import view.View_A;

public class DingDanActivity extends AppCompatActivity implements View_A {
    private RecyclerView rv;
    private List<DD.DataBean> list;
    private Presenter_A ppa;
    private SharedPreferences  sp;
    private int uid;
    private DingdanAdapter da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        sp=ShareUtis.getPreferences();
        uid=sp.getInt("uid",0);
        ppa=new Presenter_A(this);
        ppa.dingdan(uid+"");
        initview();
        intidata();
    }

    private void intidata() {
        list=new ArrayList<>();
    }

    private void initview() {
        rv= (RecyclerView) findViewById(R.id.rv);
    }

    @Override
    public void CSuccess(String result) {
        try {
            Gson gson=new Gson();
            DD dd = gson.fromJson(result, DD.class);
            final List<DD.DataBean> data = dd.getData();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    da=new DingdanAdapter(data,DingDanActivity.this);
                    rv.setLayoutManager(new LinearLayoutManager(DingDanActivity.this));
                    rv.setAdapter(da);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void CFail(String code, String msg) {

    }

    @Override
    public void CError(String code, String msg) {

    }

    @Override
    public void XSuccess(String result) {

    }

    @Override
    public void XFail(String code, String msg) {

    }

    @Override
    public void XError(String code, String msg) {

    }
}
