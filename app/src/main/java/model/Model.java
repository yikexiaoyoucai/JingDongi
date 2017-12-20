package model;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.Bean;
import bean.CX;
import bean.GSON;
import bean.SP;
import bean.XQ;
import bean.ZL;
import common.Api;
import okhttp.Requset;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class Model {
    private Sousu sousu;
    public interface Sousu{
        void Success(String result);
        void Fail(String code,String msg);
        void Error(String code,String msg);
    }
    public void setSousu(Sousu sousu) {
        this.sousu = sousu;
    }
    private Shanchu shanchu;
    private Chaxun chaxun;
    public interface  Chaxun{
        void ChaxunSuccess(String result);
        void ChaxunFail(String code,String msg);
        void ChaxunError(String code,String msg);
    }

    public void setChaxun(Chaxun chaxun) {
        this.chaxun = chaxun;
    }

    public interface Shanchu{
        void ShanchuSuccess(String result);
        void ShanchuFail(String code,String msg);
        void ShanchuError(String code,String msg);
    }

    public void setShanchu(Shanchu shanchu) {
        this.shanchu = shanchu;
    }

    public void xiangqing(String pid){
        Map<String,String> map=new HashMap<>();
        map.put("pid",pid);
        Requset.call(Api.XIANGQING, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // regin.Fail(code,msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try{
//                    JSONObject obj=new JSONObject(result);
//                    XQ b=new XQ();
//                    b.code=obj.getString("code");

                        sousu.Success(result);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void search(String keywords){
        Map<String,String> map=new HashMap<>();
        map.put("keywords",keywords);
        Requset.call(Api.SOUSU, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // regin.Fail(code,msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try{
                    JSONObject obj=new JSONObject(result);
                    ZL b=new ZL();
                    b.code=obj.getString("code");
                    if ("0".equals(b.code)){
                        sousu.Success(result);
                    }else {

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void tianjia(String uid,String pid){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        Requset.call(Api.TIANJIA, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // regin.Fail(code,msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try{
                    JSONObject obj=new JSONObject(result);
                    ZL b=new ZL();
                    b.code=obj.getString("code");
                    if ("0".equals(b.code)){
                        sousu.Success(result);
                    }else {

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void chaxun(String uid) {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        Requset.call(Api.CHAXUN, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // regin.Fail(code,msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    Gson gson = new Gson();
                    CX cx = gson.fromJson(result, CX.class);
                    String code = cx.getCode();
                    if ("0".equals(code)) {
                        chaxun.ChaxunSuccess(result);
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void genggai(String uid,String sellerid,String pid,String selected,String num){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("sellerid",sellerid);
        map.put("pid",pid);
        map.put("selected",selected);
        map.put("num",num);
        Requset.call(Api.GENGGAI, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // regin.Fail(code,msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try{
                    Gson gson=new Gson();
                    CX cx = gson.fromJson(result, CX.class);
                    String code = cx.getCode();
                    if ("0".equals(code)){
                        sousu.Success(result);
                    }else {

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void shanchu(String uid,String pid){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("pid",pid);
        Requset.call(Api.SHANCHU, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // regin.Fail(code,msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try{
                    Gson gson=new Gson();
                    CX cx = gson.fromJson(result, CX.class);
                    String code = cx.getCode();
                    if ("0".equals(code)){
                        shanchu.ShanchuSuccess(result);
                    }else {

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
