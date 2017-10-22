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

import bean.Icon;

/**
 * Created by asus on 2017/10/9.
 */

public class ShangpinAdapter extends RecyclerView.Adapter<ShangpinAdapter.SpGridViewHolder> {
    private List<Icon> listt;
    private Context context;

    public ShangpinAdapter(List<Icon> listt, Context context) {
        this.listt = listt;
        this.context = context;
    }

    @Override
    public SpGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.recyview_item,null);
        SpGridViewHolder sh=new SpGridViewHolder(view);
        return sh;
    }

    @Override
    public void onBindViewHolder(SpGridViewHolder holder, int position) {
          holder.text.setText(listt.get(position).name);
        Glide.with(context).load(listt.get(position).icon).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return listt.size();
    }

    static class SpGridViewHolder extends RecyclerView.ViewHolder{
       private ImageView img;
        private TextView text;
        public SpGridViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
        }
    }
}
