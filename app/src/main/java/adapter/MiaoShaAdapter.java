package adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;

import java.util.List;

import bean.MS;

/**
 * Created by asus on 2017/10/9.
 */

public class MiaoShaAdapter extends RecyclerView.Adapter<MiaoShaAdapter.MiaoShaViewHolder> {
    private List<MS> list;
    private Context context;

    public MiaoShaAdapter(List<MS> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MiaoShaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recyview1_item,null);
        MiaoShaViewHolder mv=new MiaoShaViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(MiaoShaViewHolder holder, int position) {
        holder.text.setText("￥"+list.get(position).price);
        holder.price.setText("￥"+list.get(position).bargainPrice);
        holder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        String imgs=list.get(position).images;
        String[] ss= imgs.split("\\|");
        Glide.with(context).load(ss[0]).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MiaoShaViewHolder extends RecyclerView.ViewHolder{
       private ImageView img;
        private TextView text;
        private TextView price;
        public MiaoShaViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
            price=itemView.findViewById(R.id.price);
        }
    }
}
