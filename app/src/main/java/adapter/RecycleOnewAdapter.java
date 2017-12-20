package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.ZILEIActivity;

import java.util.List;

import bean.FL;
import bean.SP;
import common.Re;


public class RecycleOnewAdapter extends RecyclerView.Adapter<RecycleOnewAdapter.RecycleOneViewHolder> {
    private List<SP> list;
    private Context context;
    public RecycleOnewAdapter(List<SP> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecycleOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.fl_item_recycleview,null);
        RecycleOneViewHolder mv=new RecycleOneViewHolder(view);
        return mv;
    }

    @Override
    public void onBindViewHolder(final RecycleOneViewHolder holder, final int position) {
        holder.name.setText(list.get(position).name);
        System.out.println("name+"+list.get(position).name);
        holder.fl_rv.setLayoutManager(new GridLayoutManager(context,4));
        holder.fl_rv.addItemDecoration(new Re(15,5));
        RecycleTwoAdapter ra=new RecycleTwoAdapter(list,context);
        holder.fl_rv.setAdapter(ra);
        ra.setFen(new RecycleTwoAdapter.Fen(){
            @Override
            public void OnClick(View view, int postion) {
                Intent intent=new Intent(context,ZILEIActivity.class);
                intent.putExtra("pscid",list.get(postion).pscid);
                System.out.println("pscid====="+list.get(postion).pscid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class RecycleOneViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private RecyclerView fl_rv;
        public RecycleOneViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.title);
            fl_rv= itemView.findViewById(R.id.fl_rv);
        }
    }

}
