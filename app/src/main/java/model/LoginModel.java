package model;

import android.app.Activity;
import android.test.ActivityTestCase;

import org.json.JSONObject;

import java.io.IOException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import bean.Login;
import common.Api;
import common.LogInterceptor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginModel {
    private Regin regin;
    private UpDate upDate;
    private Activity context;
    public LoginModel(Activity context) {
        this.context=context;
    }

    public interface  UpDate{
        void UpDateSuccess(String result);
        void UpDateFail(String code,String msg);
        void UpDateError(String code,String msg);
    }

    public void setUpDate(UpDate upDate) {
        this.upDate = upDate;
    }
    public void update(String uid,String nickname){
        OkHttpClient.Builder ob=new OkHttpClient.Builder();
        OkHttpClient okHttpClient=ob.build();
        FormBody.Builder fb=new FormBody.Builder();
        fb.add("uid",uid);
        fb.add("nickname",nickname);
        RequestBody body=fb.build();
        Request request=new Request.Builder().url(Api.XIUGAI).post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                System.out.println("===="+result);
                try{
                    JSONObject obj=new JSONObject(result);
                    String code=obj.getString("code");
                    String msg=obj.getString("msg");
                    if(code.equals("0")){
                        upDate.UpDateSuccess(result);
                    }else{
                        upDate.UpDateFail(code,msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public OkHttpClient setCard() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            String certificateAlias = Integer.toString(0);
            keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(context.getAssets().open("kson_server.cer")));//拷贝好的证书
            SSLContext sslContext = SSLContext.getInstance("TLS");
            final TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );
            builder.sslSocketFactory(sslContext.getSocketFactory());
            builder.addInterceptor(new LogInterceptor());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    public interface Regin{
        void ReginSuccess(String result);
        void ReginFail(String code,String msg);
        void ReginError(String code,String msg);
    }

    public void setRegin(Regin regin) {
        this.regin = regin;
    }
    private Login login;
    public interface Login{
        void LoginSuccess(String result);
        void LoginFail(String code,String msg);
        void LoginError(String code,String msg);
    }
    public void setLogin(Login login){
        this.login=login;
    }
    public void login(String mobile,String pass){
//        OkHttpClient.Builder ob=new OkHttpClient.Builder();
//        OkHttpClient okHttpClient=ob.build();
//        FormBody.Builder fb=new FormBody.Builder();
//        fb.add("mobile",mobile);
//        fb.add("password",pass);
//        RequestBody body=fb.build();
//        Request request=new Request.Builder().url(Api.LOGIN).post(body).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("请求失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result=response.body().string();
//                System.out.println("===="+result);
//                try{
//                    JSONObject obj=new JSONObject(result);
//                    String code=obj.getString("code");
//                    String msg=obj.getString("msg");
//                    if(code.equals("0")){
//                        login.LoginSuccess(result);
//                    }else{
//                        login.LoginFail(code,msg);
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        });
        FormBody formbody = new FormBody.Builder().add("mobile", mobile).add("password", pass).build();
        Request request = new Request.Builder().url(Api.LOGIN).post(formbody).build();
        setCard().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                System.out.println("===="+result);
                try{
                    JSONObject obj=new JSONObject(result);
                    String code=obj.getString("code");
                    String msg=obj.getString("msg");
                    if(code.equals("0")){
                        login.LoginSuccess(result);
                    }else{
                        login.LoginFail(code,msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
               }
            }
        });
    }
    public void regin(String mobile,String pass){
        OkHttpClient.Builder ob=new OkHttpClient.Builder();
        OkHttpClient okHttpClient=ob.build();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("mobile",mobile);
        builder.add("password",pass);
        RequestBody body=builder.build();
        Request request=new Request.Builder().url(Api.REGIN).post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                System.out.println("===="+result);
                try{
                    JSONObject obj=new JSONObject(result);
                    String code=obj.getString("code");
                    String msg=obj.getString("msg");
                    if(code.equals("0")){
                        regin.ReginSuccess(result);
                    }else{
                        regin.ReginFail(code,msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
