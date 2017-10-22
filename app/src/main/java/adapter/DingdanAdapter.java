package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.DingDanActivity;
import com.example.asus.jingdong.R;

import java.util.List;

import bean.DD;
import bean.MS;
import zhifubao.PayDemoActivity;

/**
 * Created by asus on 2017/10/9.
 */

public class DingdanAdapter extends RecyclerView.Adapter<DingdanAdapter.DingdanViewHolder> {

    private Context context;
    List<DD.DataBean> data;
    public DingdanAdapter(List<DD.DataBean> data, Context context) {
        this.data=data;
        this.context=context;
    }


    @Override
    public DingdanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.dingdan_item,null);
        DingdanViewHolder mv=new DingdanViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(DingdanViewHolder holder, final int position) {
        holder.price.setText(data.get(position).getPrice()+"");
        holder.time.setText(data.get(position).getCreatetime());
        holder.dingdan.setText("订单号:"+data.get(position).getOrderid());
        if(data.get(position).getStatus()==1){
            holder.btn.setText("已支付");
        }else if(data.get(position).getStatus()==0){
            holder.btn.setText("未支付");
        }
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,PayDemoActivity.class);
                intent.putExtra("orderid",data.get(position).getOrderid());
                context.startActivity(intent);
                ((Activity)context).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DingdanViewHolder extends RecyclerView.ViewHolder{
        private TextView price;
        private TextView time,dingdan;
        private Button btn;
        public DingdanViewHolder(View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.price);
            time=itemView.findViewById(R.id.time);
            dingdan=itemView.findViewById(R.id.dingdan);
            btn=itemView.findViewById(R.id.btn);

        }
    }
}
