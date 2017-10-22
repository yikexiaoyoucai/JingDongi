package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.jingdong.R;

import java.util.List;

import bean.Icon;
import common.Re;

/**
 * Created by asus on 2017/10/9.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private List<List<Icon>> lists;

    public ViewPagerAdapter(Context context, List<List<Icon>> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=View.inflate(context, R.layout.recyview,null);
        RecyclerView rv=view.findViewById(R.id.rv);
        ShangpinAdapter sa=new ShangpinAdapter(lists.get(position),context);
        rv.setLayoutManager(new GridLayoutManager(context,5));
        rv.addItemDecoration(new Re(10,5));
        rv.setAdapter(sa);
        container.addView(view);
        return view;
    }
}
