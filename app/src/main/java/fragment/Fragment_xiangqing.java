package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.asus.jingdong.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import bean.GSON;
import bean.XQ;
import presenter.Presenter;

import static com.example.asus.jingdong.R.id.xq;

/**
 * Created by asus on 2017/10/17.
 */

public class Fragment_xiangqing extends Fragment implements view.View {
    private View view;
    private Presenter pp;
    private WebView wv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_xiangqing,null);
        pp=new Presenter(this);
        int pid=getArguments().getInt("pid");
        System.out.println("pid=="+pid);
        pp.xiangqing(pid+"");
        initview();
        return view;
    }
    private void initview() {
        wv=view.findViewById(R.id.wv);
    }

    @Override
    public void onResume() {
        super.onResume();
        pp=new Presenter(this);
        Bundle bund=new Bundle();
        int pid=bund.getInt("pid");
        pp.xiangqing(pid+"");
        initview();
    }

    @Override
    public void Success(String result) {
        try{
            Gson gson=new Gson();
//            JSONObject obj=new JSONObject(result);
//            JSONObject objj=obj.getJSONObject("data");
//            final XQ xq=new XQ();
//            xq.detailUrl=objj.getString("detailUrl");
//            System.out.println("url=="+xq.detailUrl);
//
            GSON gson1 = gson.fromJson(result, GSON.class);
            final String detailUrl = gson1.getData().getDetailUrl();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    wv.getSettings().setJavaScriptEnabled(true);
                    wv.getSettings().setBuiltInZoomControls(true);
                    wv.getSettings().setLoadWithOverviewMode(true);
                    wv.loadUrl(detailUrl);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void Fail(String code, String msg) {

    }

    @Override
    public void Error(String code, String msg) {

    }

    @Override
    public void ShanchuSuccess(String result) {

    }

    @Override
    public void ShanchuFail(String code, String msg) {

    }

    @Override
    public void ShanchuError(String code, String msg) {

    }

    @Override
    public void ChaxunSuccess(String result) {

    }

    @Override
    public void ChaxunFail(String code, String msg) {

    }

    @Override
    public void ChaxunError(String code, String msg) {

    }
}
