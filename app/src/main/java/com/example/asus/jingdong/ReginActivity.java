package com.example.asus.jingdong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import presenter.LoginPresenter;
import view.LoginView;

public class ReginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {
    private ImageView iv_back;
    private EditText et_name,et_pass;
    private Button but_zc,but_dl;
    private LoginPresenter lp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regin);
        initview();
        lp=new LoginPresenter(this,this);
    }

    private void initview() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        et_name= (EditText) findViewById(R.id.et_name);
        et_pass= (EditText) findViewById(R.id.et_pass);
        but_zc= (Button) findViewById(R.id.but_zc);
        but_dl= (Button) findViewById(R.id.but_dl);
        but_zc.setOnClickListener(this);
        but_dl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.but_zc:
                String name=et_name.getText().toString();
                String pass=et_pass.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(ReginActivity.this,"注册用户名不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(pass.isEmpty()){
                    Toast.makeText(ReginActivity.this,"注册密码不能为空",Toast.LENGTH_SHORT).show();
                    if(pass.length()<6){
                        Toast.makeText(ReginActivity.this,"密码不能少于6位数",Toast.LENGTH_SHORT).show();
                    }
                }
                lp.regin(name,pass);
                break;
            case R.id.but_dl:
                break;
        }
    }

    @Override
    public void ReginSuccess(String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ReginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void ReginFail(String code, String msg) {

    }

    @Override
    public void ReginError(String code, String msg) {

    }

    @Override
    public void LoginSuccess(String result) {

    }

    @Override
    public void LoginFail(String code, String msg) {

    }

    @Override
    public void LoginError(String code, String msg) {

    }

    @Override
    public void UpDateSuccess(String result) {

    }

    @Override
    public void UpDateFail(String code, String msg) {

    }

    @Override
    public void UpDateError(String code, String msg) {

    }
}
