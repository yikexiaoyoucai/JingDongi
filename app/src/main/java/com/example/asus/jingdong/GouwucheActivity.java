package com.example.asus.jingdong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.TuiJianAdapter;
import bean.TJ;
import common.Api;
import presenter.LunboPresenter;
import view.LunboView;

public class GouwucheActivity extends AppCompatActivity implements View.OnClickListener, LunboView {
      private Button dl;
      private RecyclerView rv;
    private TuiJianAdapter ta;
    private LunboPresenter presenter;
    private List<TJ> tj_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gouwuche);
        presenter=new LunboPresenter(this);
        presenter.Tuijian(Api.LUNBO);
        initview();
        initdata();
    }

    private void initdata() {
        tj_list=new ArrayList<>();
    }

    private void initview() {
        dl= (Button) findViewById(R.id.dl);
        rv= (RecyclerView) findViewById(R.id.rv);
        dl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dl:
                Intent intent=new Intent(GouwucheActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void Success(String result) {

    }

    @Override
    public void Fail(String code) {

    }

    @Override
    public void Error(String code) {

    }

    @Override
    public void ShangpinSuccess(String result) {

    }

    @Override
    public void ShangpinFail(String code) {

    }

    @Override
    public void ShangpinError(String code) {

    }

    @Override
    public void MiaoShaSuccess(String result) {

    }

    @Override
    public void MiaoShaFail(String code) {

    }

    @Override
    public void MiaoShaError(String code) {

    }

    @Override
    public void TuijianSuccess(String result) {
        try{
            JSONObject obj=new JSONObject(result);
            JSONObject ojj=obj.getJSONObject("tuijian");
            JSONArray arr=ojj.getJSONArray("list");
            //ms_list.clear();
            if(arr!=null&&arr.length()>0){
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject data= (JSONObject) arr.get(i);
                    TJ tj=new TJ();
                    tj.images=data.getString("images");
                    tj.bargainPrice=data.getString("bargainPrice");
                    tj.title=data.getString("title");
                    tj.detailUrl=data.getString("detailUrl");
                    tj_list.add(tj);
                }
            }
          runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rv.setLayoutManager(new GridLayoutManager(GouwucheActivity.this,2));
                    // rcy.addItemDecoration(new Re(10,5));
                    ta=new TuiJianAdapter(tj_list,GouwucheActivity.this);
                    rv.setAdapter(ta);
                    ta.setTuijian(new TuiJianAdapter.Tuijian() {
                        @Override
                        public void TUIJIANSP(View view, int postion) {
                            Intent intent=new Intent(GouwucheActivity.this, TJWebViewActivity.class);
                            intent.putExtra("url",tj_list.get(postion).detailUrl);
                            startActivity(intent);
                        }
                    });
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void TuijianFail(String code) {

    }

    @Override
    public void TuijianError(String code) {

    }
}
