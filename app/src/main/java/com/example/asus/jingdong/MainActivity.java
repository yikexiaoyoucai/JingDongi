package com.example.asus.jingdong;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements View.OnClickListener {
    private ImageView img;
    private Handler handler=new Handler();
    private Runnable run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        run=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,ZhuActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.postDelayed(run,8000);
    }

    private void initview() {
        img= (ImageView) findViewById(R.id.img);
        img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img:
                Intent intent=new Intent(MainActivity.this,ZhuActivity.class);
                startActivity(intent);
                finish();
                handler.removeCallbacks(run);
                break;
        }
    }
}
