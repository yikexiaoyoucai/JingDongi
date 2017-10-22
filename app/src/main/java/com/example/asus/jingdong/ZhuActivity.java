package com.example.asus.jingdong;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import fragment.Fragment_faxian;
import fragment.Fragment_feilei;
import fragment.Fragment_gouwuche;
import fragment.Fragment_shouye;
import fragment.Fragment_wode;

public class ZhuActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout ft;
    private Fragment f1=new Fragment_shouye();
    private Fragment f2=new Fragment_feilei();
    private Fragment f3=new Fragment_gouwuche();
    private Fragment f4=new Fragment_faxian();
    private Fragment f5=new Fragment_wode();
    private RadioButton sy,fl,fx,wd,gwc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu);
        initview();
        Intent intent=getIntent();
        int tt = intent.getIntExtra("tt", 0);
        if(tt==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.ft,f3).commit();
            gwc.setChecked(true);
        }else{
            getSupportFragmentManager().beginTransaction().replace(R.id.ft,f1).commit();
            sy.setChecked(true);
        }
    }

    private void initview() {
        sy= (RadioButton) findViewById(R.id.sy);
        fl= (RadioButton) findViewById(R.id.fl);
        fx= (RadioButton) findViewById(R.id.fx);
        gwc= (RadioButton) findViewById(R.id.gwc);
        wd= (RadioButton) findViewById(R.id.wd);
        sy.setOnClickListener(this);
        fl.setOnClickListener(this);
        fx.setOnClickListener(this);
        gwc.setOnClickListener(this);
        wd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sy:
                getSupportFragmentManager().beginTransaction().replace(R.id.ft,f1).commit();
                sy.setChecked(true);
                fl.setChecked(false);
                fx.setChecked(false);
                gwc.setChecked(false);
                wd.setChecked(false);
                break;
            case R.id.fl:
                getSupportFragmentManager().beginTransaction().replace(R.id.ft,f2).commit();
                fl.setChecked(true);
                sy.setChecked(false);
                fx.setChecked(false);
                gwc.setChecked(false);
                wd.setChecked(false);
                break;
            case R.id.fx:
                getSupportFragmentManager().beginTransaction().replace(R.id.ft,f4).commit();
                fx.setChecked(true);
                sy.setChecked(false);
                fl.setChecked(false);
                gwc.setChecked(false);
                wd.setChecked(false);
                break;
            case R.id.gwc:
                getSupportFragmentManager().beginTransaction().replace(R.id.ft,f3).commit();
                gwc.setChecked(true);
                sy.setChecked(false);
                fx.setChecked(false);
                fl.setChecked(false);
                wd.setChecked(false);
                break;
            case R.id.wd:
                getSupportFragmentManager().beginTransaction().replace(R.id.ft,f5).commit();
                wd.setChecked(true);
                sy.setChecked(false);
                fx.setChecked(false);
                gwc.setChecked(false);
                fl.setChecked(false);
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this,"内容为空",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"扫描成功",Toast.LENGTH_LONG).show();
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                Intent intent=new Intent(ZhuActivity.this,SMWebViewActivity.class);
                intent.putExtra("url",ScanResult);
                startActivity(intent);
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
