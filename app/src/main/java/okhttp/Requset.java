package okhttp;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by asus on 2017/10/16.
 */

public class Requset {
    public static void call(String url, Map<String,String> parmes, final Callback callback){
        OkHttpClient.Builder OB=new OkHttpClient.Builder();
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
