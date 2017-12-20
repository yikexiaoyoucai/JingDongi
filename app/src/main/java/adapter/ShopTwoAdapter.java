package adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CX;
import bean.CXX;
import bean.SP;
import common.ShareUtis;
import common.ViewUtils;
import presenter.Presenter;

public class ShopTwoAdapter extends RecyclerView.Adapter<ShopTwoAdapter.ShopTwoViewHolder> implements view.View{
    private List<CX.DataBean.ListBean> list;
    private Context context;
    private Presenter pp;
    private SharedPreferences sp;
    private int num=0;
    private  List<Map<Integer,Boolean>> listt=new ArrayList<>();
    public ShopTwoAdapter(List<CX.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
        pp=new Presenter(this);
        initdata();
    }
    private void initdata() {
        for (int i = 0; i <list.size() ; i++) {
            int selected=list.get(i).getSelected();
            Map<Integer,Boolean> map=new HashMap<>();
            if(selected==1){
                map.put(i,true);
            }else{
                map.put(i,false);
            }
            listt.add(map);
        }
    }
    @Override
    public ShopTwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.zi_item,null);
        ShopTwoViewHolder mv=new ShopTwoViewHolder(view);
        return mv;
    }
    @Override
    public void onBindViewHolder(final ShopTwoViewHolder holder, final int position) {

        if(list.get(position).getSelected()==1){
            holder.zi_chekbox.setChecked(true);
            num++;
            aa.Success(num);

        }else{
            holder.zi_chekbox.setChecked(false);
        }
        sp= ShareUtis.getPreferences();
        holder.name.setText(list.get(position).getTitle());
        holder.textView.setText(list.get(position).getSubhead());
        double v = list.get(position).getBargainPrice();
        holder.price.setText("￥"+v);
        String url=list.get(position).getImages();
        String[] ss=url.split("\\|");
        Glide.with(context).load(ss[0]).into(holder.img);
        final CX.DataBean.ListBean listBean = list.get(position);

         holder.zi_chekbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 listt.get(position).put(position,b);
                 if(b){
                     num++;
                     listBean.setSelected(1);
                     int uid=sp.getInt("uid",0);
                     int pid=list.get(position).getPid();
                     int sell=list.get(position).getSellerid();
                     int selected1 = list.get(position).getSelected();
                     pp.genggai(uid+"",sell+"",pid+"",selected1+"",list.get(position).getNum()+"");
                     System.out.println("amount===="+list.get(position).getNum()+"");
                     System.out.println("selected1===="+selected1+"");
                 }else{
                     if(num>0){
                         num--;
                     }
                     listBean.setSelected(0);
                     int uid=sp.getInt("uid",0);
                     int pid=list.get(position).getPid();
                     int sell=list.get(position).getSellerid();
                     int selected1 = list.get(position).getSelected();
                     pp.genggai(uid+"",sell+"",pid+"",selected1+"",list.get(position).getNum()+"");
                     System.out.println("amount===="+list.get(position).getNum()+"");
                     System.out.println("selected1===="+selected1+"");
                 }
                 aa.Success(num);
                 bb.Success();
                 ff.Success(list.get(position).getSelected(),list.get(position).getPid());
             }
         });
        if(listt.get(position)==null){
            listt.get(position).put(position,false);
        }
        holder.zi_chekbox.setChecked(listt.get(position).get(position));
        holder.amount_view.setGoods_storage(50,list.get(position).getNum());
        holder.amount_view.setOnAmountChangeListener(new ViewUtils.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view,int amount) {
               holder.zi_chekbox.setChecked(true);
                    int uid=sp.getInt("uid",0);
                    int pid=list.get(position).getPid();
                    int sell=list.get(position).getSellerid();
                    int selected1 = list.get(position).getSelected();
                    list.get(position).setNum(amount);
                    pp.genggai(uid+"",sell+"",pid+"",selected1+"",amount+"");
                    bb.Success();
                    ff.Success(selected1,pid);
            }
        });
    }

    public void sum(){
        for (int i = 0; i <list.size() ; i++) {
            CX.DataBean.ListBean listBean = list.get(i);
            listBean.setSelected(1);
            listt.get(i).put(i,true);
        }

    }
    public void fan(){
        for (int i = 0; i <list.size() ; i++) {
            CX.DataBean.ListBean listBean = list.get(i);
            listBean.setSelected(0);
            listt.get(i).put(i,false);
        }
    }
    @Override
    public int getItemCount() {
        return list.size();
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

    private AA aa;
    public  interface  AA{
        void Success(int postion);
    }
    public void setAa(AA aa) {
        this.aa = aa;
    }
    private BB bb;
    public interface  BB{
        void Success();
    }
    private FF ff;
    public interface  FF{
        void Success(int selected,int pid);
    }

    public void setFf(FF ff) {
        this.ff = ff;
    }

    public void setBb(BB bb) {
        this.bb = bb;
    }

    static class ShopTwoViewHolder extends RecyclerView.ViewHolder{
        private TextView name,textView,price;
        private ImageView img;
        private ViewUtils amount_view;
        private CheckBox zi_chekbox;

        public ShopTwoViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            img=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.textView);
            price=itemView.findViewById(R.id.price);
            amount_view=itemView.findViewById(R.id.amount_view);
            zi_chekbox=itemView.findViewById(R.id.zi_chekbox);
        }
    }
}
