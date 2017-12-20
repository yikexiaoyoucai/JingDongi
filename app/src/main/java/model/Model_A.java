package model;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import bean.CX;
import bean.DD;
import common.Api;
import okhttp.Request_A;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import presenter.Presenter_A;


public class Model_A
{
    private CHUANGJIAN chuangjian;
    private XIUGAIDINGDAN xiugaidingdan;
    public interface  XIUGAIDINGDAN{
        void XSuccess(String result);
        void XFail(String code,String msg);
        void XError(String code,String msg);
    }
    public void setXiugaidingdan(XIUGAIDINGDAN xiugaidingdan) {
        this.xiugaidingdan = xiugaidingdan;
    }
    public interface CHUANGJIAN{
        void CSuccess(String result);
        void CFail(String code,String msg);
        void CError(String code,String msg);
    }
    public void setChuangjian(CHUANGJIAN chuangjian) {
        this.chuangjian = chuangjian;
    }
    public void chuangjian(String uid,String price){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("price",price);
        Request_A.call(Api.CHUANGJIAN, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
//                    Gson gson = new Gson();
//                    CX cx = gson.fromJson(result, CX.class);
//                    String code = cx.getCode();
//                    if ("0".equals(code)) {
                        chuangjian.CSuccess(result);
//                    } else {
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void dingdan(String uid){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        Request_A.call(Api.DINGDAN, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    Gson gson = new Gson();
                    DD dd = gson.fromJson(result, DD.class);
                    String code = dd.getCode();
                    if ("0".equals(code)) {
                    chuangjian.CSuccess(result);
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void xiugaidingdan(String uid,String status,String orderId){
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("status",status);
        map.put("orderId",orderId);
        Request_A.call(Api.XIUGAIDINGDAN, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                try {
                    Gson gson = new Gson();
                    DD dd = gson.fromJson(result, DD.class);
                    String code = dd.getCode();
                    if ("0".equals(code)) {
                        chuangjian.CSuccess(result);
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
