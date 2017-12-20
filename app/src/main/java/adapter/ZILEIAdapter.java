package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;

import org.w3c.dom.Text;

import java.util.List;

import bean.SP;
import bean.ZL;


public class ZILEIAdapter extends RecyclerView.Adapter<ZILEIAdapter.ZILEIViewHolder> {
    private List<ZL> list;
    private Context context;
    public ZILEIAdapter(List<ZL> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ZILEIViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.zilei_item1,null);
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
                ff.OnClick(holder.itemView,list.get(position).pid);
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
    private FF ff;
    public interface   FF{
        void OnClick(View view,int postion);
    }

    public void setFF(FF ff) {
        this.ff = ff;
    }

}
