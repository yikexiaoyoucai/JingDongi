package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import adapter.GlideImage;
import com.example.asus.jingdong.R;
import com.example.asus.jingdong.SaoMiaoActivity;
import com.example.asus.jingdong.SousuActivity;
import com.example.asus.jingdong.TJWebViewActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.MiaoShaAdapter;
import adapter.ShangpinAdapter;
import adapter.TuiJianAdapter;
import adapter.ViewPagerAdapter;
import bean.Icon;
import bean.MS;
import bean.TJ;
import common.Api;
import common.Re;
import presenter.LunboPresenter;
import view.LunboView;


public class Fragment_shouye extends Fragment implements LunboView, View.OnClickListener {
    private View view;
    private EditText head_sou;
    private List<String> list;
    private LunboPresenter presenter;
    private List<Icon> listt;
    private List<Icon> listtt;
    private List<List<Icon>> lists;
    private Banner banner;
    private ViewPager vp;
    private LinearLayout layout;
    private List<ImageView> img_list;
    private ImageView sys,sou;
    private RecyclerView rcy;
    private List<MS> ms_list;
    private MiaoShaAdapter ms;
    private RecyclerView rv;
    private List<TJ> tj_list;
    private TuiJianAdapter ta;
    private TextView shi,fen,miao;
    private long mHour=02;
    private long mMin=00;
    private long mSecond=36;
    private boolean isRun=true;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                computeTime();
                if(mHour<10){
                    shi.setText("0"+mHour+"");
                }else{
                    shi.setText("0"+mHour+"");
                }
                if(mMin<10){
                    fen.setText("0"+mMin);
                }else{
                    fen.setText(mMin+"");
                }
                if(mSecond<10){
                    miao.setText("0"+mSecond+"");
                }else{
                    miao.setText(mSecond+"");
                }
            }
        }
    };

    private void computeTime() {
        mSecond--;
        if(mSecond<0){
            mMin--;
            mSecond=59;
            if(mMin<0){
                mMin=59;
                mHour--;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_shouye,null);
        presenter=new LunboPresenter(this);
        presenter.lunbo(Api.LUNBO);
        presenter.Shangpin(Api.SHANGPIN);
        presenter.Miaosha(Api.LUNBO);
        presenter.Tuijian(Api.LUNBO);
        initview();
        initdata();
        addDot();
        startRun();
        return view;
    }

    private void startRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRun){
                    try{
                       Thread.sleep(1000);
                        Message me=Message.obtain();
                        me.what=1;
                        handler.sendMessage(me);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    //添加小圆点的方法
    private void addDot() {
        for (int i = 0; i <=1; i++) {
            ImageView iv=new ImageView(getActivity());
            if(i==0){
                iv.setImageResource(R.drawable.check);
            }else{
                iv.setImageResource(R.drawable.nor);
            }
            LinearLayout.LayoutParams pp=new LinearLayout.LayoutParams(20,20);
            pp.setMargins(8,0,8,0);
            layout.addView(iv,pp);
            img_list.add(iv);

        }
    }

    private void initdata() {
        list=new ArrayList<>();
        listt=new ArrayList<>();
        listtt=new ArrayList<>();
        lists=new ArrayList<>();
        img_list=new ArrayList<>();
        ms_list=new ArrayList<>();
        tj_list=new ArrayList<>();
    }

    private void initview() {
        shi=view.findViewById(R.id.shi);
        fen=view.findViewById(R.id.fen);
        miao=view.findViewById(R.id.miao);
        banner=view.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImage());
        vp=view.findViewById(R.id.vp);
        layout=view.findViewById(R.id.layout);
        sys=view.findViewById(R.id.sys);
        sys.setOnClickListener(this);
        sou=view.findViewById(R.id.sou);
        rcy=view.findViewById(R.id.rcy);
        rv=view.findViewById(R.id.rv);
        head_sou=view.findViewById(R.id.head_sou);
        head_sou.setOnClickListener(this);
    }

    @Override
    public void Success(String result) {
        try{
            JSONObject obj=new JSONObject(result);
            JSONArray arr=obj.getJSONArray("data");
            list.clear();
            if(arr!=null&&arr.length()>0){
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject data= (JSONObject) arr.get(i);
                    Icon icc=new Icon();
                    icc.icon=data.getString("icon");
                    list.add(data.getString("icon"));
                }
                System.out.println("===="+list.size());
            }
            if(getActivity()==null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    banner.setImages(list);
                    banner.start();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void Fail(String code) {

    }

    @Override
    public void Error(String code) {

    }

    @Override
    public void ShangpinSuccess(String result) {
        try{
            JSONObject obj=new JSONObject(result);
            JSONArray arr=obj.getJSONArray("data");
            list.clear();
            if(arr!=null&&arr.length()>0){
                lists.clear();
                listt.clear();
                listtt.clear();
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject data= (JSONObject) arr.get(i);
                    if(i<10){
                        Icon icc=new Icon();
                        icc.icon=data.getString("icon");
                        icc.name=data.getString("name");
                        listt.add(icc);
                    }else if(i>=10){
                        Icon icc=new Icon();
                        icc.icon=data.getString("icon");
                        icc.name=data.getString("name");
                        listtt.add(icc);
                    }
                }
                lists.add(listt);
                lists.add(listtt);
                if (getActivity() == null)
                return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ViewPagerAdapter va=new ViewPagerAdapter(getActivity(),lists);
                        vp.setAdapter(va);
                        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                for (int i = 0; i < layout.getChildCount(); i++) {
                                    ImageView dot= (ImageView) layout.getChildAt(i);
                                    if(i==position){
                                        dot.setImageResource(R.drawable.check);
                                    }else{
                                        dot.setImageResource(R.drawable.nor);
                                    }
                                }
                            }

                            @Override
                            public void onPageSelected(int position) {

                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    }
                });

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void ShangpinFail(String code) {

    }

    @Override
    public void ShangpinError(String code) {

    }

    @Override
    public void MiaoShaSuccess(String result) {

        try{
            JSONObject obj=new JSONObject(result);
            JSONObject ojj=obj.getJSONObject("miaosha");
            int time=ojj.getInt("time");
            JSONArray arr=ojj.getJSONArray("list");
            //ms_list.clear();
            if(arr!=null&&arr.length()>0){
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject data= (JSONObject) arr.get(i);
                    MS mm=new MS();
                    mm.images=data.getString("images");
                    mm.bargainPrice=data.getString("bargainPrice");
                    mm.price=data.getString("price");
                    ms_list.add(mm);
                }
                System.out.println("===="+ms_list.toString());
            }
            if(getActivity()==null)
                return;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
               rcy.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                  //  rcy.addItemDecoration(new Re(10,5));
                    ms=new MiaoShaAdapter(ms_list,getActivity());
                    rcy.setAdapter(ms);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void MiaoShaFail(String code) {

    }

    @Override
    public void MiaoShaError(String code) {

    }

    @Override
    public void TuijianSuccess(String result) {
        try{
            JSONObject obj=new JSONObject(result);
            JSONObject ojj=obj.getJSONObject("tuijian");
            JSONArray arr=ojj.getJSONArray("list");
            //ms_list.clear();
            if(arr!=null&&arr.length()>0){
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject data= (JSONObject) arr.get(i);
                   TJ tj=new TJ();
                    tj.images=data.getString("images");
                    tj.bargainPrice=data.getString("bargainPrice");
                    tj.title=data.getString("title");
                    tj.detailUrl=data.getString("detailUrl");
                    tj_list.add(tj);
                }
                System.out.println("===="+ms_list.toString());
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
                     // rcy.addItemDecoration(new Re(10,5));
                    ta=new TuiJianAdapter(tj_list,getActivity());
                    rv.setAdapter(ta);
                    ta.setTuijian(new TuiJianAdapter.Tuijian() {
                        @Override
                        public void TUIJIANSP(View view, int postion) {
                             Intent intent=new Intent(getActivity(), TJWebViewActivity.class);
                            intent.putExtra("url",tj_list.get(postion).detailUrl);
                            startActivity(intent);
                        }
                    });
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void TuijianFail(String code) {

    }

    @Override
    public void TuijianError(String code) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sys:
//                Intent intent=new Intent(getActivity(), SaoMiaoActivity.class);
//                startActivity(intent);
                new IntentIntegrator(getActivity())
                        .setCameraId(0)
                        .setBeepEnabled(true)
                        .setCaptureActivity(SaoMiaoActivity.class) // 设置自定义的activity是CustomActivity
                        .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
                        .initiateScan(); // 初始
                break;
            case R.id.head_sou:
                Intent intent=new Intent(getActivity(), SousuActivity.class);
                startActivity(intent);
                break;

        }
    }
}
