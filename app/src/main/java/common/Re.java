package common;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by asus on 2017/10/9.
 */

public class Re  extends RecyclerView.ItemDecoration{
    private int top;
    private int right;
    //获取条目间距
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int hegiht=parent.getChildAdapterPosition(view);
        outRect.left=right;
        outRect.top=top;
        if(hegiht!=0){
            if(hegiht%2==0){
                outRect.bottom=top;
                outRect.top=top;
            }else{
                outRect.top=top;
                outRect.bottom=top;
            }
        }

    }

    public Re(int right,int top) {
        this.right = right;
        this.top = top;
    }
}
