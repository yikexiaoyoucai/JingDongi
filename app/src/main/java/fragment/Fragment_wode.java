package fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.asus.jingdong.DingDanActivity;
import com.example.asus.jingdong.LoginActivity;
import com.example.asus.jingdong.R;
import com.example.asus.jingdong.ShezhiActivity;
import com.example.asus.jingdong.UserActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bean.Login;
import common.Api;
import common.ShareUtis;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.asus.jingdong.R.drawable.login;
import static com.umeng.socialize.a.b.d.i;


public class Fragment_wode extends Fragment implements View.OnClickListener {
    private TextView login_regist;
    private View view;
    private ImageView shezhi,wodedingdan;
    private SharedPreferences sp;
    private ImageView icon;
    private List<Login> list;
    private Login login;
    private int uid;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view=View.inflate(getActivity(), R.layout.fragment_wode,null);
        initview();
        initdata();
        sp= ShareUtis.getPreferences();
        uid=sp.getInt("uid",0);
        if(uid!=0){
            okhttp(uid);
        }else{
            login_regist.setText("注册/登录");
            icon.setImageResource(R.drawable.login);
        }
        return view;
    }

    private void initdata() {
        list=new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        uid=sp.getInt("uid",0);
        System.out.println("========"+uid);
        if(uid!=0){
            okhttp(uid);
        }else{
            login_regist.setText("注册/登录");
            icon.setImageResource(R.drawable.login);
        }


    }
    public void okhttp(int uid){
        OkHttpClient.Builder ob=new OkHttpClient.Builder();
        OkHttpClient okHttpClient=ob.build();
        FormBody.Builder fb=new FormBody.Builder();
        fb.add("uid", uid+"");
        RequestBody body=fb.build();
        Request request=new Request.Builder().url(Api.USER).post(body).build();
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
                    JSONObject jobj=obj.getJSONObject("data");
                    login=new Login();
                    login.nickname=jobj.getString("nickname");
                    login.username=jobj.getString("username");
                    login.icon=jobj.getString("icon");
                    list.add(login);
                    System.out.println("icon============"+login.icon);
                    if(!login.nickname.equals("null")){
                        login_regist.setText(login.nickname);
                    }else{
                        login_regist.setText(login.username);
                    }
                    if("null".equals(login.icon)){
                        icon.setImageResource(R.drawable.login);
                    }else{
                        System.out.println("sssss"+login.icon);
                        Glide.with(getActivity()).load(login.icon).asBitmap().diskCacheStrategy(DiskCacheStrategy.NONE).into(icon);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    private void initview() {
        login_regist=view.findViewById(R.id.login_regist);
        login_regist.setOnClickListener(this);
        shezhi=view.findViewById(R.id.shezhi);
        shezhi.setOnClickListener(this);
        icon=view.findViewById(R.id.icon);
        icon.setOnClickListener(this);
        wodedingdan=view.findViewById(R.id.wodedingdan);
        wodedingdan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_regist:
                if(uid!=0){
                    Intent intent1=new Intent(getActivity(), ShezhiActivity.class);
                   // if(!login.nickname.equals("null")){
                        intent1.putExtra("nickname",login.nickname);
                        intent1.putExtra("icon",login.icon);
                    //}else{
                        intent1.putExtra("username",login.username);
                       // intent1.putExtra("icon",login.icon);
                   // }
                    startActivity(intent1);
                }else{
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.shezhi:
                Intent intent1=new Intent(getActivity(), ShezhiActivity.class);
                intent1.putExtra("nickname",login.nickname);
                intent1.putExtra("icon",login.icon);
                intent1.putExtra("username",login.username);
                startActivity(intent1);
                break;
            case R.id.icon:
                Intent intent=new Intent(getActivity(),ShezhiActivity.class);
                intent.putExtra("nickname",login.nickname);
                intent.putExtra("icon",login.icon);
                intent.putExtra("username",login.username);
                startActivity(intent);

                break;
            case R.id.wodedingdan:
                Intent intentt=new Intent(getActivity(), DingDanActivity.class);
                startActivity(intentt);
                break;
        }
    }
}
