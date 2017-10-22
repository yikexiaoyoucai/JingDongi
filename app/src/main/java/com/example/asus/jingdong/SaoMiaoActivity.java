package com.example.asus.jingdong;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class SaoMiaoActivity extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {
     private Button sg;
    private DecoratedBarcodeView db;
    private CaptureManager captureManager;
    private boolean isLightOn=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_miao);
        initview();
        db.setTorchListener(this);
        captureManager=new CaptureManager(this,db);
        captureManager.initializeFromIntent(getIntent(),savedInstanceState);
        captureManager.decode();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return db.onKeyDown(keyCode,event)||super.onKeyDown(keyCode,event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    private void initview() {
        sg= (Button) findViewById(R.id.sg);
        db= (DecoratedBarcodeView) findViewById(R.id.db);
    }


    @Override
    public void onTorchOn() {
        Toast.makeText(this,"torch on",Toast.LENGTH_LONG).show();
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        Toast.makeText(this,"torch off",Toast.LENGTH_LONG).show();
        isLightOn = false;
    }
}
