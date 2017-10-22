package adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by asus on 2017/10/17.
 */

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> list_title;
    private List<Fragment> list_fragment;
    private Context context;

    public FragmentViewPagerAdapter(FragmentManager fm, List<String> list_title, List<Fragment> list_fragment, Context context) {
        super(fm);
        this.list_title = list_title;
        this.list_fragment = list_fragment;
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_title.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_title.size();
    }
}
