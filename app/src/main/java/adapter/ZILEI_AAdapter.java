package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;

import java.util.List;

import bean.ZL;


public class ZILEI_AAdapter extends RecyclerView.Adapter<ZILEI_AAdapter.ZILEIViewHolder> {
    private List<ZL> list;
    private Context context;
    public ZILEI_AAdapter(List<ZL> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ZILEIViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.zilei_item2,null);
        ZILEIViewHolder mv=new ZILEIViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(final ZILEIViewHolder holder, final int position) {
        holder.title.setText(list.get(position).title);
        String ss=list.get(position).images;
        String[] s=ss.split("\\|");
        Glide.with(context).load(s[0]).into(holder.img);
        holder.price.setText("￥"+list.get(position).price);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ff.OnClick(holder.itemView,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ZILEIViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView price;
        private ImageView img;
        public ZILEIViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
        }
    }
    private FFd ff;
    public interface   FFd{
        void OnClick(View view,int postion);
    }

    public void setFFd(FFd ff) {
        this.ff = ff;
    }

}
