package com.example.asus.jingdong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhy.android.percent.support.PercentRelativeLayout;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import common.Api;
import common.ShareUtis;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import presenter.LoginPresenter;
import view.LoginView;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, LoginView{
    private ImageView iv_settingBack,login;
    private TextView nc;
    private String nickname,username,icon;
    private SharedPreferences sp;
    private   int uid;
    private static final int CHOOSE_PICTURE=0;
    private static final int TAKE_PICTURE=1;
    private static final int CROP_SMALL_PICTURE=2;
    private static Uri tempUri;
    private LoginPresenter lp;
    private PercentRelativeLayout dd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        lp=new LoginPresenter(this);
        intiview();
        initdata();
        sp= ShareUtis.getPreferences();
        uid=sp.getInt("uid",0);
        if(uid==0){
            nc.setText("注册/登录");
            login.setImageResource(R.drawable.login);
        }else{
            if(icon.equals("null")){
                login.setImageResource(R.drawable.login);
            }else{
                Glide.with(UserActivity.this).load(icon).into(login);
            }

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
            nc.setText(nickname);
        }else{
            nc.setText(username);
        }
        if("null".equals(icon)){
            login.setImageResource(R.drawable.login);
        }else{
            Glide.with(UserActivity.this).load(icon).into(login);
        }
    }

    private void intiview() {
        iv_settingBack= (ImageView) findViewById(R.id.iv_settingBack);
        iv_settingBack.setOnClickListener(this);
        login= (ImageView) findViewById(R.id.login);
        login.setOnClickListener(this);
        nc= (TextView) findViewById(R.id.nc);
        nc.setOnClickListener(this);
        dd= (PercentRelativeLayout) findViewById(R.id.dd);
        dd.setOnClickListener(this);
    }


    /**
     * 裁剪图片方法实现
     * @param uri
     */
    protected void startPhotoZoom(Uri uri){
        if(uri==null){
            Log.i("tag","The uri is not exist");
        }
        tempUri=uri;
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        //设置裁剪
        intent.putExtra("crop","true");
        //aspectX aspectY是宽高的比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        //outputX outputY是裁剪图片的宽高
        intent.putExtra("outputX",150);
        intent.putExtra("outputY",150);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,CROP_SMALL_PICTURE);

    }
    /**
     * 保存裁剪之后的图片数据
     * @param data
     */
    protected void setImageToView(Intent data){
        Bundle extras=data.getExtras();
        if(extras!=null){
            Bitmap photo=extras.getParcelable("data");
            login.setImageBitmap(photo);
            saveImage(photo);
            File file=new File(getCacheDir()+"/aa.jpg");
            OkHttpClient ck=new OkHttpClient();
            MultipartBody.Builder mb=new MultipartBody.Builder().setType(MultipartBody.FORM);
            mb.addFormDataPart("uid",uid+"");
            mb.addFormDataPart("file",file.getName(), RequestBody.create(MediaType.parse("image/*"),file));
            Request r=new Request.Builder().url(Api.BITMAP).post(mb.build()).build();
            ck.newCall(r).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UserActivity.this,"上传失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result=response.body().string();
                    System.out.println("头像========"+result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(UserActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }
    /**
     * 读取的图片存在一个路径里
     * @param photo
     */
    private void saveImage(Bitmap photo) {
        File file=new File(getCacheDir()+"/aa.jpg");
        try{
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
            photo.compress(Bitmap.CompressFormat.JPEG,100,bos);//压缩
            bos.flush();
            bos.close();
        }catch (Exception e){

        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_settingBack:
                finish();
                break;
            case R.id.login:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("设置头像");
                String[] items={"选择本地照片","拍照"};
                builder.setNegativeButton("取消",null);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case CHOOSE_PICTURE://选择本地照片
                                Intent open=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                open.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                                startActivityForResult(open,CHOOSE_PICTURE);
                                break;
                            case TAKE_PICTURE://拍照
                                Intent openC=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                tempUri=Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                                //指定照片保存路径sd卡，image.jpg为一个临时文件，每次拍照后这个图
                                openC.putExtra(MediaStore.EXTRA_OUTPUT,tempUri);
                                startActivityForResult(openC,TAKE_PICTURE);
                                break;
                        }
                    }
                });
                builder.create().show();
                break;
            case R.id.dd:
                View vv= LayoutInflater.from(this).inflate(R.layout.xiugai,null);
                final EditText xg=vv.findViewById(R.id.xg);

                AlertDialog.Builder ab=new AlertDialog.Builder(this);
                ab.setMessage("修改一下昵称");
                ab.setView(vv);
                ab.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String updata=xg.getText().toString();
                        lp.updata(uid+"",updata);
                        nc.setText(updata);

                    }
                });
                ab.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ab.create();
                ab.show();

                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case TAKE_PICTURE:
                startPhotoZoom(tempUri);
                break;
            case CHOOSE_PICTURE:
                startPhotoZoom(data.getData());
                break;
            case CROP_SMALL_PICTURE:
                if(data!=null){
                    setImageToView(data);
                }
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
