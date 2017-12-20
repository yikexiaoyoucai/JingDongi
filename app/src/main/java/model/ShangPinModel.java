package model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import bean.FL;
import bean.Icon;
import bean.SP;
import bean.ZL;
import common.Api;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ShangPinModel {
    private Fenlei fenlei;
    private ZILEI zilei;
    public interface ZILEI{
        void ZILEISuccess(String result);
        void ZILEIFail(String code,String msg);
        void ZILEIError(String code,String msg);
    }
    public interface Fenlei{
        void FenleiSuccess(String result);
        void FenleiFail(String code,String msg);
        void FenleiError(String code,String msg);
    }
    public void setZILEI(ZILEI zilei){
        this.zilei=zilei;
    }
    public void setFenlei(Fenlei fenlei) {
        this.fenlei = fenlei;
    }
    public void zilei(String cid){
        OkHttpClient.Builder ok=new OkHttpClient.Builder();
        OkHttpClient client=ok.build();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("cid",cid);
        RequestBody body=builder.build();
        Request request=new Request.Builder().post(body).url(Api.ZIFENLEI).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try{
                    JSONObject obj=new JSONObject(result);
                    SP sp=new SP();
                    sp.code=obj.getString("code");
                    sp.msg=obj.getString("msg");
                    if(sp.code.equals("0")){
                        zilei.ZILEISuccess(result);
                    }else{
                        zilei.ZILEIFail(sp.code,sp.msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void fenlei(String url){
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
                    FL fl=new FL();
                    fl.code=json.getString("code");
                    fl.msg=json.getString("msg");
                    if(fl.code.equals("0")){
                        fenlei.FenleiSuccess(result);
                    }else{
                        fenlei.FenleiError(fl.code,fl.msg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private ZI zi;
    public interface  ZI{
        void ZISuccess(String result);
        void ZIFail(String code,String msg);
        void ZIError(String code,String msg);
    }

    public void setZi(ZI zi) {
        this.zi = zi;
    }

    public void zil(String pscid,String page){
        OkHttpClient.Builder ok=new OkHttpClient.Builder();
        OkHttpClient client=ok.build();
        FormBody.Builder builder=new FormBody.Builder();
        builder.add("pscid",pscid);
        builder.add("page",page+"");
        RequestBody body=builder.build();
        Request request=new Request.Builder().post(body).url(Api.ZILEI).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try{
                    JSONObject obj=new JSONObject(result);
                    ZL zl=new ZL();
                    zl.code=obj.getString("code");
                    zl.msg=obj.getString("msg");
                    if(zl.code.equals("0")){
                        zi.ZISuccess(result);
                    }else{
                        zi.ZIFail(zl.code,zl.msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
