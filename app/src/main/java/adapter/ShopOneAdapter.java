package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.ZILEIActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.CX;
import bean.CXX;
import bean.SP;
import common.Re;

/**
 * Created by asus on 2017/10/9.
 */

public class ShopOneAdapter extends RecyclerView.Adapter<ShopOneAdapter.ShopOneViewHolder> {
    private  List<CX.DataBean>  list;
    private Context context;
    boolean[] flag;
    private CC cc;
private int pos;
    public ShopOneAdapter(List<CX.DataBean> list, Context context) {
        pos=0;
        this.list = list;
        this.context = context;
        flag=new boolean[list.size()];
    }
    @Override
    public ShopOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.fu_item,null);
        ShopOneViewHolder mv=new ShopOneViewHolder(view);
        return mv;
    }
    public void setStatus(int pos){
        this.pos=pos;
    }
    @Override
    public void onBindViewHolder(final ShopOneViewHolder holder, final int position) {
        if(pos==1)
        {
            flag[position]=true;
        }
        else if(pos==2)
        {
            flag[position]=false;
        }
        holder.fu_chekbox.setOnCheckedChangeListener(null);
        System.out.println("flag[position]"+flag[position]);
        holder.fu_chekbox.setChecked(flag[position]);
        holder.fu_chekbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flag[position]=b;
            }
        });
        holder.tv.setText(list.get(position).getSellerName());
        holder.rvv.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        final ShopTwoAdapter shopTwoAdapter=new ShopTwoAdapter(list.get(position).getList(),context);
        holder.rvv.setAdapter(shopTwoAdapter);
        holder.fu_chekbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.fu_chekbox.isChecked()){
                    shopTwoAdapter.sum();
                    flag[position]=true;
                    holder.fu_chekbox.setChecked(true);
                    Toast.makeText(context, "check", Toast.LENGTH_SHORT).show();

                }else{
                    shopTwoAdapter.fan();
                    flag[position]=false;
                    holder.fu_chekbox.setChecked(false);
                    Toast.makeText(context, "nocheck", Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();
                cc.Success();
            }
        });
        shopTwoAdapter.setAa(new ShopTwoAdapter.AA() {
            @Override
            public void Success(int i) {
                System.out.println("==="+i+"=="+list.get(position).getList().size());
                if(i==list.get(position).getList().size()){
                    holder.fu_chekbox.setChecked(true);
                    flag[position]=true;

                }else{
                    holder.fu_chekbox.setChecked(false);
                    flag[position]=false;
                }
            }
        });
        shopTwoAdapter.setBb(new ShopTwoAdapter.BB() {
            @Override
            public void Success() {
                if(cc != null){
                    cc.Success();
                }
            }
        });
        shopTwoAdapter.setFf(new ShopTwoAdapter.FF() {
            @Override
            public void Success(int selected,int pid) {
                if(gg!=null){
                    gg.Success(selected,pid);
                }

            }
        });
    }

    public interface  CC{
        void Success();
    }
    private GG gg;
    public void setGG(GG gg) {
        this.gg = gg;
    }
    public interface  GG{
        void Success(int selected,int pid);
    }

    public void setCc(CC cc) {
        this.cc = cc;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ShopOneViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        private RecyclerView rvv;
        private CheckBox fu_chekbox;
        public ShopOneViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
            rvv= itemView.findViewById(R.id.rvv);
            fu_chekbox=itemView.findViewById(R.id.fu_chekbox);
        }
    }

}
