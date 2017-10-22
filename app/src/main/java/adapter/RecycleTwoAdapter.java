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

import bean.SP;

/**
 * Created by asus on 2017/10/9.
 */

public class RecycleTwoAdapter extends RecyclerView.Adapter<RecycleTwoAdapter.RecycleTwoViewHolder> {
    private List<SP> list;
    private Context context;
    private int flag;
    public RecycleTwoAdapter(List<SP> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecycleTwoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recyle_item,null);
        RecycleTwoViewHolder mv=new RecycleTwoViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(final RecycleTwoViewHolder holder, final int position) {
        holder.name.setText(list.get(position).name);
        System.out.println("name+"+list.get(position).name);
        Glide.with(context).load(list.get(position).icon).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fen.OnClick(holder.itemView,position);
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

    static class RecycleTwoViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView img;
        public RecycleTwoViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            img=itemView.findViewById(R.id.img);
        }
    }
    private Fen fen;
    public interface  Fen{
        void OnClick(View view,int postion);
    }

    public void setFen(Fen fen) {
        this.fen = fen;
    }

}
