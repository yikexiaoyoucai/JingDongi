package okhttp;

import android.content.Context;

import java.io.IOException;
import java.util.Map;

import common.LogInterceptor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by asus on 2017/10/21.
 */

public class Request_A {
    private Context context;
    private static Request_A intsal;

    private   Request_A (Context context){
        this.context=context;

    }
    public Request_A getIntsal(Context context) {
        if (intsal == null) {
            synchronized (Request_A.class) {
                if (intsal == null) {
                    intsal = new Request_A(context);
                }
            }
        }
      return  intsal;
    }
    public static void call(String url, Map<String,String> parmes, final Callback callback){
        OkHttpClient.Builder OB=new OkHttpClient.Builder().addInterceptor(new LogInterceptor());
        OkHttpClient client=OB.build();
        FormBody.Builder body=new FormBody.Builder();
        for (Map.Entry<String, String> stringStringEntry : parmes.entrySet()) {
            body.add(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
        RequestBody bb=body.build();
        Request request=new Request.Builder().post(bb).url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callback.onResponse(call,response);
            }
        });
    }

}
