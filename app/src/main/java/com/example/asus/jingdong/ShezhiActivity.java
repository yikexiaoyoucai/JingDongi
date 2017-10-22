package com.example.asus.jingdong;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import bean.Login;
import common.ShareUtis;

public class ShezhiActivity extends AppCompatActivity {
    private ImageView iv_settingIcon,iv_settingBack;
    private TextView tv_settingNickName;
    private Button btn_exit;
    private SharedPreferences sp;
    private int uid;
    private String nickname,username,icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        initview();
        initdata();
        sp=ShareUtis.getPreferences();
        uid=sp.getInt("uid",0);
        if(uid==0){
            tv_settingNickName.setText("注册/登录");
            iv_settingIcon.setImageResource(R.drawable.login);
        }else{
            if(icon.equals("null")){
                iv_settingIcon.setImageResource(R.drawable.login);
            }else{
                Glide.with(ShezhiActivity.this).load(icon).into(iv_settingIcon);
            }

        }
        btn();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initview();
        initdata();
        sp=ShareUtis.getPreferences();
        uid=sp.getInt("uid",0);
        if(uid==0){
            tv_settingNickName.setText("注册/登录");
            iv_settingIcon.setImageResource(R.drawable.login);
        }else{
            if(icon.equals("null")){
                iv_settingIcon.setImageResource(R.drawable.login);
            }else{
                Glide.with(ShezhiActivity.this).load(icon).into(iv_settingIcon);
            }

        }
        btn();
    }

    private void btn() {

        if(uid==0){
            btn_exit.setVisibility(View.GONE);
        }else{
            btn_exit.setVisibility(View.VISIBLE);
            btn_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder ab=new AlertDialog.Builder(ShezhiActivity.this);
                    ab.setMessage("是否确定要退出的登录");
                    ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            sp.edit().clear().commit();
                            tv_settingNickName.setText("注册/登录");
                            finish();
                        }
                    });
                    ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    ab.create();
                    ab.show();
                }
            });
        }
    }

    private void initdata() {
        Intent intent=getIntent();
        nickname=intent.getStringExtra("nickname");
        System.out.println("==========="+nickname);
        username=intent.getStringExtra("username");
        System.out.println("==========="+username);
        icon=intent.getStringExtra("icon");
        if(!"null".equals(nickname)){
            tv_settingNickName.setText(nickname);
            System.out.println("nickname"+nickname);

        }else{
            tv_settingNickName.setText(username);
        }
        if("null".equals(icon)){
            iv_settingIcon.setImageResource(R.drawable.login);
        }else{
            Glide.with(ShezhiActivity.this).load(icon).into(iv_settingIcon);
        }
        iv_settingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShezhiActivity.this,UserActivity.class);
                intent.putExtra("nickname",nickname);
                intent.putExtra("icon",icon);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });

    }

    private void initview() {
        iv_settingIcon= (ImageView) findViewById(R.id.iv_settingIcon);
        tv_settingNickName= (TextView) findViewById(R.id.tv_settingNickName);
        btn_exit= (Button) findViewById(R.id.btn_exit);
        iv_settingBack= (ImageView) findViewById(R.id.iv_settingBack);
        iv_settingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
