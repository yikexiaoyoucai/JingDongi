package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.jingdong.R;

import java.util.List;

import bean.FL;
import bean.TJ;


public class FenLeiAdapter extends RecyclerView.Adapter<FenLeiAdapter.FenleiViewHolder> {
    private List<FL> list;
    private Context context;
    private int flag;
    public FenLeiAdapter(List<FL> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public FenleiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recyview_item3,null);
        FenleiViewHolder mv=new FenleiViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(final FenleiViewHolder holder, final int position) {
        holder.name.setText(list.get(position).name);
        System.out.println("name+"+list.get(position).name);
        if(position==flag){
            holder.itemView.setSelected(true);
            holder.name.setTextColor(Color.RED);
        }else{
            holder.itemView.setSelected(false);
            holder.name.setTextColor(Color.BLACK);
        }
         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 shangpin.OnClick(holder.itemView,position);

             }
         });
    }
    public void Draw(int num){
        this.flag=num;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FenleiViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        public FenleiViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);

        }
    }
    private Shangpin shangpin;
    public interface   Shangpin{
        void OnClick(View view,int postion);
    }

    public void setShangpin(Shangpin shangpin) {
        this.shangpin = shangpin;
    }
}
