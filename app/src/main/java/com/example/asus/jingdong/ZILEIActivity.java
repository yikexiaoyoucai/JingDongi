package com.example.asus.jingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import adapter.ZILEIAdapter;
import adapter.ZILEI_AAdapter;
import bean.Bean;
import bean.ZL;
import common.ShareUtis;
import presenter.Presenter;
import presenter.ShangPinPresenter;
import view.ShangPinView;

public class ZILEIActivity extends AppCompatActivity implements ShangPinView, View.OnClickListener, view.View {
    private ImageView fh, type;
    private TextView xl, jg;
    private RecyclerView rv;
    private List<ZL> list;
    private ShangPinPresenter sp;
    private ZILEIAdapter za;
    private ZILEI_AAdapter zza;
    private int a=0;
    private EditText head_sou;
    private Presenter pp;
    private int page=1;
    private int pscid;
    private LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
    private GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zilei);
        sp = new ShangPinPresenter(this);
            pp=new Presenter(this);
           initview();
            initdata();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sp = new ShangPinPresenter(this);
        pp=new Presenter(this);
        initview();
        init();
    }

    private void initview() {
        fh = (ImageView) findViewById(R.id.fh);
        type = (ImageView) findViewById(R.id.type);
        xl = (TextView) findViewById(R.id.xl);
        jg = (TextView) findViewById(R.id.jg);
        rv = (RecyclerView) findViewById(R.id.rv);
        fh.setOnClickListener(this);
        type.setOnClickListener(this);
        jg.setOnClickListener(this);
        head_sou= (EditText) findViewById(R.id.head_sou);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //静止状态
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    RecyclerView.LayoutManager layoutManager=rv.getLayoutManager();
                    if(layoutManager instanceof LinearLayoutManager){
                        int item=((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                        if(item==list.size()-1){
                            page++;
                            sp.zil(pscid+"",page+"");
                        }
                    }
                }
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    RecyclerView.LayoutManager layoutManager=rv.getLayoutManager();
                    if(layoutManager instanceof GridLayoutManager){
                        int item=((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                        if(item==list.size()-1){
                            page++;
                            sp.zil(pscid+"",page+"");
                        }
                    }
                }

            }
        });
    }

    private void initdata() {
        list = new ArrayList<>();
        Intent intent=getIntent();
        pscid=intent.getIntExtra("pscid",0);
        sp.zil(pscid+"",page+"");
        System.out.println("=======pscid"+pscid);
    }
    private void init(){
        list = new ArrayList<>();
        Intent intent1=getIntent();
        String name=intent1.getStringExtra("name");
        System.out.println("name"+name);
        head_sou.setText(name);
        if(name!=null){
            pp.search(name);
        }
    }
    @Override
    public void FenleiSuccess(String result) {

    }

    @Override
    public void FenleiFail(String code, String msg) {

    }

    @Override
    public void FenleiError(String code, String msg) {

    }

    @Override
    public void ZILEISuccess(String result) {

    }

    @Override
    public void ZILEIFail(String code, String msg) {

    }

    @Override
    public void ZILEIError(String code, String msg) {

    }

    @Override
    public void ZISuccess(String result) {
        try {
            JSONObject obj = new JSONObject(result);
          //  zl.page=obj.getString("page");
            JSONArray data = obj.getJSONArray("data");
            if (data != null && data.length() > 0) {
                for (int i = 0; i < data.length(); i++) {
                    ZL zl = new ZL();
                    JSONObject arr = data.getJSONObject(i);
                    System.out.println("arr="+arr);
                    zl.title = arr.getString("title");
                    zl.price = arr.getInt("price");
                    zl.images = arr.getString("images");
                    zl.pid=arr.getInt("pid");
                    System.out.println("title="+zl.title);
                    list.add(zl);
                }
               //list.add(zl);
            }
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       za=new ZILEIAdapter(list,ZILEIActivity.this);
                       rv.setLayoutManager(linearLayoutManager);
                       rv.setAdapter(za);
                       za.setFF(new ZILEIAdapter.FF(){
                           @Override
                           public void OnClick(View view, int postion) {
                               Intent intent=new Intent(ZILEIActivity.this,WebViewActivity.class);
                               intent.putExtra("pid",postion);
                               System.out.println("pdddddd"+postion);
                               startActivity(intent);
                           }
                       });
                   }
               });
    }catch(Exception e){
        e.printStackTrace();
    }
}
    @Override
    public void ZIFail(String code, String msg) {

    }

    @Override
    public void ZIError(String code, String msg) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fh:
                finish();
                break;
            case R.id.type:
                RecyclerView.LayoutManager layoutManager=rv.getLayoutManager();
                if(layoutManager==linearLayoutManager){
                    type.setSelected(true);
                  //  za=new ZILEIAdapter(list,ZILEIActivity.this);
                    rv.setLayoutManager(gridLayoutManager);
                    rv.setAdapter(za);
                }else if(layoutManager==gridLayoutManager){
                    type.setSelected(false);
                   // za=new ZILEIAdapter(list,ZILEIActivity.this);
                    rv.setLayoutManager(linearLayoutManager);
                    rv.setAdapter(za);
//                    za.setFF(new ZILEIAdapter.FF(){
//                        @Override
//                        public void OnClick(View view, int postion) {
//                            Intent intent=new Intent(ZILEIActivity.this,WebViewActivity.class);
//                            intent.putExtra("pid",postion);
//                            startActivity(intent);
//                        }
//                    });
                }
                break;
            case R.id.jg:
                Collections.sort(list, new Comparator<ZL>() {
                    @Override
                    public int compare(ZL house, ZL t1) {
                        int i=house.getPrice()-t1.getPrice();
                        if(i<0){
                            return -1;
                        }else{
                            return 0;
                        }
                    }

                });
                break;
        }
    }

    @Override
    public void Success(String result) {
        try{
            JSONObject objj=new JSONObject(result);
            JSONArray arr=objj.getJSONArray("data");
            if(arr!=null&&arr.length()>0){
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject data= (JSONObject) arr.get(i);
                    ZL  bean=new ZL();
                    bean.images=data.getString("images");
                    bean.title=data.getString("title");
                    bean.price=data.getInt("price");
                    bean.pid=data.getInt("pid");
                    list.add(bean);
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    za=new ZILEIAdapter(list,ZILEIActivity.this);
                    rv.setLayoutManager(new LinearLayoutManager(ZILEIActivity.this,LinearLayoutManager.VERTICAL,false));
                    rv.setAdapter(za);
                    za.setFF(new ZILEIAdapter.FF(){

                        @Override
                        public void OnClick(View view, int postion) {
                            Intent intent=new Intent(ZILEIActivity.this,WebViewActivity.class);
                            intent.putExtra("pid",postion);
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
    public void Fail(String code, String msg) {

    }

    @Override
    public void Error(String code, String msg) {

    }

    @Override
    public void ShanchuSuccess(String result) {

    }

    @Override
    public void ShanchuFail(String code, String msg) {

    }

    @Override
    public void ShanchuError(String code, String msg) {

    }

    @Override
    public void ChaxunSuccess(String result) {

    }

    @Override
    public void ChaxunFail(String code, String msg) {

    }

    @Override
    public void ChaxunError(String code, String msg) {

    }
}
