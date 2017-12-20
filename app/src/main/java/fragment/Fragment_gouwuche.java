package fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jingdong.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapter.ShopOneAdapter;
import bean.CX;
import bean.CXX;
import bean.GSON;
import common.ShareUtis;
import common.ViewUtils;
import presenter.Presenter;
import presenter.Presenter_A;
import view.View_A;


public class Fragment_gouwuche extends Fragment implements View.OnClickListener, view.View,View_A{
    private View view;
    private TextView bianji,tv_delete,tv_total_price,tv_go_to_pay;
    private Presenter pp;
    private SharedPreferences sp;
    private int uid;
    private List<CX.DataBean> list_cx;
    private RecyclerView fl_rv;
    private ShopOneAdapter sd;
    private LinearLayout dr,ll_shar;
    private CheckBox all_chekbox;
    private int a=0;
    private int pi;
    private int ss;
    private int aa;
    private int sl;
    private double pr;
    private Presenter_A ppa;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_gouwuche,null);
        pp=new Presenter(this);
        ppa=new Presenter_A(this);
        sp= ShareUtis.getPreferences();
        uid=sp.getInt("uid",0);
        pp.chaxun(uid+"");
        initview();
        initdata();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        pp=new Presenter(this);
        sp= ShareUtis.getPreferences();
        uid=sp.getInt("uid",0);
        pp.chaxun(uid+"");
        initview();
        initdata();
    }

    private void initdata() {
        list_cx=new ArrayList<>();

    }
    private void initview() {
        bianji=view.findViewById(R.id.bianji);
        bianji.setOnClickListener(this);
        fl_rv=view.findViewById(R.id.fl_rv);
        dr=view.findViewById(R.id.ll_info);
        ll_shar=view.findViewById(R.id.ll_shar);
        all_chekbox=view.findViewById(R.id.all_chekbox);
        tv_delete=view.findViewById(R.id.tv_delete);
        tv_delete.setOnClickListener(this);
        all_chekbox=view.findViewById(R.id.all_chekbox);
        tv_total_price=view.findViewById(R.id.tv_total_price);
        tv_go_to_pay=view.findViewById(R.id.tv_go_to_pay);
        tv_go_to_pay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
   switch (view.getId()){
       case R.id.bianji:
           a++;
           if(a%2==0){
               dr.setVisibility(View.GONE);
               ll_shar.setVisibility(View.VISIBLE);
               bianji.setText("完成");
           }else{
               dr.setVisibility(View.VISIBLE);
               ll_shar.setVisibility(View.GONE);
               bianji.setText("编辑");
           }
           break;
       case R.id.tv_delete:
           sd.setGG(new ShopOneAdapter.GG() {
               @Override
               public void Success(int selected,int pid) {
                   if(selected==1){
                       pp.shanchu(uid+"",pi+"");
                       // sd.notifyDataSetChanged();
                       System.out.println(""+uid+""+""+pi+"");
                       pp.chaxun(uid+"");
                   }
                   System.out.println("===1===");
               }
           });
           break;
       case R.id.tv_go_to_pay:
           String s=tv_total_price.getText().toString();
           ppa.chuangjian(uid+"",s);
           System.out.println(uid+""+s);
           break;
   }
    }
    @Override
    public void Success(String result) {
        System.out.println("更新成功");
    }

    @Override
    public void Fail(String code, String msg) {

    }

    @Override
    public void Error(String code, String msg) {

    }

    @Override
    public void ShanchuSuccess(String result) {
        System.out.println("删除成功");
    }

    @Override
    public void ShanchuFail(String code, String msg) {

    }

    @Override
    public void ShanchuError(String code, String msg) {

    }
   public void ccc(List<CX.DataBean> data){
       int kb=0;
       double money=0;
       for (CX.DataBean bean : data) {
           List<CX.DataBean.ListBean> list=bean.getList();
           for (CX.DataBean.ListBean listBean : list) {
               if(listBean.getSelected()==1){
                   kb++;
                   money+=listBean.getNum()*listBean.getBargainPrice();
               }
           }

       }
       final double finalMoney = money;
       final int finalKb = kb;
       getActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               tv_total_price.setText(finalMoney+"");
               tv_go_to_pay.setText("结算（"+ finalKb +")");
           }
       });
   }
    @Override
    public void ChaxunSuccess(String result) {
        try{
            Gson gson=new Gson();
            CX cx = gson.fromJson(result, CX.class);
            final List<CX.DataBean> data = cx.getData();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fl_rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    //  rcy.addItemDecoration(new Re(10,5));
                    sd=new ShopOneAdapter(data,getActivity());
                    fl_rv.setAdapter(sd);
                    ccc(data);
                    sd.setCc(new ShopOneAdapter.CC() {
                        @Override
                        public void Success() {
                           ccc(data);
                        }
                    });
                }
            });
            all_chekbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(CX.DataBean bean:data){
                        for(CX.DataBean.ListBean listBean:bean.getList()){
                            if(all_chekbox.isChecked()){
                                listBean.setSelected(1);
                                sd.setStatus(1);
                                pp.genggai(uid+"",+sl+"",pi+"",ss+"",aa+"");
                                System.out.println("ss"+listBean.getSelected()+"");
                            }else{
                                listBean.setSelected(0);
                                sd.setStatus(2);
                                pp.genggai(uid+"",+sl+"",pi+"",ss+"",aa+"");
                            }
                        }
                    }
                    ccc(data);
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            tv_total_price.setText("￥"+ finalMoney);
//                            tv_go_to_pay.setText("结算（"+ finalKb +")");
//                        }
//                    });
                    sd.notifyDataSetChanged();

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void ChaxunFail(String code, String msg) {

    }

    @Override
    public void ChaxunError(String code, String msg) {

    }

    @Override
    public void CSuccess(String result) {
        System.out.println("===="+result);
    }

    @Override
    public void CFail(String code, String msg) {

    }

    @Override
    public void CError(String code, String msg) {

    }

    @Override
    public void XSuccess(String result) {

    }

    @Override
    public void XFail(String code, String msg) {

    }

    @Override
    public void XError(String code, String msg) {

    }
}
