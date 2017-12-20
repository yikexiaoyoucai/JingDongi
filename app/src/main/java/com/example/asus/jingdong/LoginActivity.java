package com.example.asus.jingdong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import bean.Login;
import common.ShareUtis;
import presenter.LoginPresenter;
import view.LoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView {
    private ImageView iv_back,qq_login;
    private TextView tv_regist;
    private Button but_login;
    private LoginPresenter lp;
    private EditText et_name,et_pass;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initview();
        lp=new LoginPresenter(this,this);
        sp= ShareUtis.getPreferences();

    }

    private void initview() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        qq_login= (ImageView) findViewById(R.id.qq_login);
        qq_login.setOnClickListener(this);
        tv_regist= (TextView) findViewById(R.id.tv_regist);
        tv_regist.setOnClickListener(this);
        but_login= (Button) findViewById(R.id.but_login);
        but_login.setOnClickListener(this);
        et_name= (EditText) findViewById(R.id.et_name);
        et_pass= (EditText) findViewById(R.id.et_pass);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.qq_login:
                break;
            case R.id.tv_regist:
                Intent intent=new Intent(LoginActivity.this,ReginActivity.class);
                startActivity(intent);
                break;
            case R.id.but_login:
                String name=et_name.getText().toString();
                String pass=et_pass.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(LoginActivity.this,"登录用户名不能为空",Toast.LENGTH_SHORT).show();
                }
                else if(pass.isEmpty()){
                    Toast.makeText(LoginActivity.this,"登录密码不能为空",Toast.LENGTH_SHORT).show();
                }
                lp.login(name,pass);

                break;

        }
    }

    @Override
    public void ReginSuccess(String result) {

    }

    @Override
    public void ReginFail(String code, String msg) {

    }

    @Override
    public void ReginError(String code, String msg) {

    }

    @Override
    public void LoginSuccess(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject obj=new JSONObject(result);
                    JSONObject jobj=obj.getJSONObject("data");
                    Login login=new Login();
                    login.uid=jobj.getInt("uid");
                    System.out.println("uid"+login.uid);
                    sp.edit().putInt("uid",login.uid).commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                finish();

            }
        });
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
