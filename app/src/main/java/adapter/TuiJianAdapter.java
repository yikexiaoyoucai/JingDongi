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
import bean.TJ;


public class TuiJianAdapter extends RecyclerView.Adapter<TuiJianAdapter.TuiJianViewHolder> {
    private List<TJ> list;
    private Context context;

    public TuiJianAdapter(List<TJ> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public TuiJianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recyview2_item,null);
        TuiJianViewHolder mv=new TuiJianViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(final TuiJianViewHolder holder, final int position) {
        holder.title.setText(list.get(position).title);
        holder.price.setText("￥"+list.get(position).bargainPrice);
       // holder.price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        String imgs=list.get(position).images;
        String[] ss= imgs.split("\\|");
        Glide.with(context).load(ss[0]).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tuijian.TUIJIANSP(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TuiJianViewHolder extends RecyclerView.ViewHolder{
       private ImageView img;
        private TextView title;
        private TextView price;
        public TuiJianViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
    }
    private Tuijian tuijian;
    public interface   Tuijian{
        void TUIJIANSP(View view,int postion);
    }

    public void setTuijian(Tuijian tuijian) {
        this.tuijian = tuijian;
    }
}
