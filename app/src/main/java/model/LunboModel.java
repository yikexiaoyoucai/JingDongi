package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import bean.Icon;
import bean.MS;
import bean.TJ;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by asus on 2017/10/8.
 */

public class LunboModel {
    private Lunbo lunbo;
    private Shangpin shangpin;
    private MiaoSha miaoSha;
    private Tuijian tuijian;
    public void lunbo(String url){
        OkHttpClient.Builder ok=new OkHttpClient.Builder();
        OkHttpClient client=ok.build();
        FormBody.Builder builder=new FormBody.Builder();
        RequestBody body=builder.build();
        Request request=new Request.Builder().post(body).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              String result=response.body().string();
                try {
                    JSONObject json=new JSONObject(result);
                    Icon icc=new Icon();
                    icc.code=json.getString("code");
                    if(icc.code.equals("0")){
                        lunbo.Success(result);
                    }else{
                        lunbo.Error(icc.code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void setLunbo(Lunbo lunbo) {
        this.lunbo = lunbo;
    }
    public interface Lunbo{
       void Success(String result);
        void Fail(String code);
        void Error(String code);
    }
    public void setShangpin(Shangpin shangpin) {
        this.shangpin = shangpin;
    }
    public interface Shangpin{
        void ShangpinSuccess(String result);
        void ShangpinFail(String code);
        void ShangpinError(String code);
    }
    public void setMiaoSha(MiaoSha miaoSha) {
        this.miaoSha = miaoSha;
    }
    public interface MiaoSha{
        void MiaoShaSuccess(String result);
        void MiaoShaFail(String code);
        void MiaoShaError(String code);
    }
    public void Miaosha(String url){
        OkHttpClient.Builder ok=new OkHttpClient.Builder();
        OkHttpClient client=ok.build();
        FormBody.Builder builder=new FormBody.Builder();
        RequestBody body=builder.build();
        Request request=new Request.Builder().post(body).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try {
                    JSONObject json=new JSONObject(result);
                    MS mm=new MS();
                    mm.code=json.getString("code");
                    if(mm.code.equals("0")){
                        miaoSha.MiaoShaSuccess(result);
                    }else{
                        miaoSha.MiaoShaFail(mm.code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void Shangpin(String url){
        OkHttpClient.Builder oc=new OkHttpClient.Builder();
        OkHttpClient okHttpClient=oc.build();
        FormBody.Builder builder=new FormBody.Builder();
        RequestBody body=builder.build();
        Request request=new Request.Builder().post(body).url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try {
                    JSONObject json=new JSONObject(result);
                    Icon icc=new Icon();
                    icc.code=json.getString("code");
                    if(icc.code.equals("0")){
                        shangpin.ShangpinSuccess(result);
                    }else{
                        shangpin.ShangpinError(icc.code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public interface Tuijian{
        void TuijianSuccess(String result);
        void TuijianFail(String code);
        void TuijianError(String code);
    }
    public void setTuijian(Tuijian tuijian) {
        this.tuijian = tuijian;
    }
    public void Tuijian(String url){
        OkHttpClient.Builder ok=new OkHttpClient.Builder();
        OkHttpClient client=ok.build();
        FormBody.Builder builder=new FormBody.Builder();
        RequestBody body=builder.build();
        Request request=new Request.Builder().post(body).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try {
                    JSONObject json=new JSONObject(result);
                    TJ tj=new TJ();
                    tj.code=json.getString("code");
                    if(tj.code.equals("0")){
                       tuijian.TuijianSuccess(result);
                    }else{
                        tuijian.TuijianFail(tj.code);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
