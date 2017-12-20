package fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.GlideImage;
import bean.GSON;
import bean.XQ;
import common.ShareUtis;
import presenter.Presenter;


public class Fragment_shangpin  extends Fragment implements view.View{
    private View view;
    private Banner details_banner;
    private Presenter pp;
    private List<String> list;
    private List<XQ> xq_list;
    private TextView tv_detailsTitle,tv_detailsSubhead,tv_detailsBargain,tv_shopDes,tv_shopName,tv_shopScore,tv_shopNum,tv_shopId;
    private ImageView iv_shopIcon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(),R.layout.fragment_shangpin,null);
        pp=new Presenter(this);
        int pid=getArguments().getInt("pid");
        System.out.println("pid=="+pid);
        pp.xiangqing(pid+"");
         initview();
        initdata();
        return view;
    }

    private void initdata() {
        list=new ArrayList<>();
        xq_list=new ArrayList<>();
    }

    public void onResume() {
        super.onResume();
        pp=new Presenter(this);
        Bundle bund=new Bundle();
        int pid=bund.getInt("pid");
        pp.xiangqing(pid+"");
        initview();
        initdata();
    }
    private void initview() {
        details_banner=view.findViewById(R.id.details_banner);
        details_banner.setImageLoader(new GlideImage());
        details_banner.isAutoPlay(false);
        tv_detailsTitle=view.findViewById(R.id.tv_detailsTitle);
        tv_detailsSubhead=view.findViewById(R.id.tv_detailsSubhead);
        tv_detailsBargain=view.findViewById(R.id.tv_detailsBargain);
        tv_shopDes=view.findViewById(R.id.tv_shopDes);
        tv_shopName=view.findViewById(R.id.tv_shopName);
        tv_shopScore=view.findViewById(R.id.tv_shopScore);
        tv_shopNum=view.findViewById(R.id.tv_shopNum);
        tv_shopId=view.findViewById(R.id.tv_shopId);
        iv_shopIcon=view.findViewById(R.id.iv_shopIcon);
    }

    @Override
    public void Success(String result) {
        final XQ xq=new XQ();
        try{

//            JSONObject objj=new JSONObject(result);
//            JSONObject obj=objj.getJSONObject("data");
//            xq.images=obj.getString("images");

//            xq.title=obj.getString("title");
//            xq.subhead=obj.getString("subhead");
//            xq.price=obj.getInt("price");
//            JSONObject ott=objj.getJSONObject("seller");
//            xq.description=ott.getString("description");
//            xq.icon=ott.getString("icon");
//            xq.name=ott.getString("name");
//            xq.productNums=ott.getInt("productNums");
//            xq.score=ott.getInt("score");
//            xq.sellerid=ott.getInt("sellerid");
//            xq_list.add(xq);
            Gson gson=new Gson();
            GSON gson1 = gson.fromJson(result, GSON.class);
            String images = gson1.getData().getImages();
            final String ss=images;
            String[] tt=ss.split("\\|");
            for (int i = 0; i < tt.length; i++) {
                list.add(tt[i]);
            }
            final double bargainPrice = gson1.getData().getBargainPrice();
            final String title = gson1.getData().getTitle();
            final String subhead = gson1.getData().getSubhead();
            final String description = gson1.getSeller().getDescription();
            final String name = gson1.getSeller().getName();
            final int productNums = gson1.getSeller().getProductNums();
            final double score = gson1.getSeller().getScore();
            final int sellerid = gson1.getSeller().getSellerid();
            final String icon = gson1.getSeller().getIcon();
            final int pid = gson1.getData().getPid();
            if(getActivity()==null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    details_banner.setImages(list);
                    details_banner.start();
                    tv_detailsTitle.setText(title);
                    tv_detailsSubhead.setText(subhead);
                    tv_detailsBargain.setText(bargainPrice+"");
                    tv_shopDes.setText(description);
                    tv_shopName.setText(name);
                    tv_shopNum.setText(productNums+"");
                    tv_shopScore.setText(score+"");
                    tv_shopId.setText(sellerid+"");
                    System.out.println("头像~~~~~"+icon);
                    Glide.with(getActivity()).load(icon).into(iv_shopIcon);
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
