package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.asus.jingdong.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.FenLeiAdapter;
import adapter.ShangpinAdapter;
import bean.FL;
import common.Api;
import presenter.ShangPinPresenter;
import view.ShangPinView;

public class Fragment_feilei extends Fragment implements ShangPinView {
    private RecyclerView rv;
    private View view;
    private FenLeiAdapter fa;
    private ShangPinPresenter sp;
    private List<FL> list;
    private FrameLayout xq;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fragment_fl,null);
        sp=new ShangPinPresenter(this);
        sp.fenlei(Api.FENLEI);
        initview();
        initdata();
        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        sp=new ShangPinPresenter(this);
//        sp.fenlei(Api.FENLEI);
//        initview();
//        initdata();
//    }

    private void initdata() {
        list=new ArrayList<>();
    }

    private void initview() {
        rv=view.findViewById(R.id.rv);
        xq=view.findViewById(R.id.xq);
    }
    @Override
    public void FenleiSuccess(String result) {
        try{
            JSONObject obj=new JSONObject(result);
            JSONArray arr=obj.getJSONArray("data");
            //ms_list.clear();
            if(arr!=null&&arr.length()>0){
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject data= (JSONObject) arr.get(i);
                    FL fl=new FL();
                    fl.name=data.getString("name");
                    fl.cid=data.getInt("cid");
                    list.add(fl);
                }
            }
            if (getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    rv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    //  rcy.addItemDecoration(new Re(10,5));
                    fa=new FenLeiAdapter(list,getActivity());
                    rv.setAdapter(fa);
                    Fragment ff=Fragment_fl.getIns(list.get(0).cid);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.xq,ff).commit();
                    fa.setShangpin(new FenLeiAdapter.Shangpin(){
                        @Override
                        public void OnClick(View view, int postion) {
                            fa.Draw(postion);
                            Fragment ff=Fragment_fl.getIns(list.get(postion).cid);
                           getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.xq,ff).commit();


                        }
                    });
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void FenleiFail(String code, String msg) {

    }

    @Override
    public void FenleiError(String code, String msg) {

    }

    @Override
    public void ZILEISuccess(String result) {

    }

    @Override
    public void ZILEIFail(String code, String msg) {

    }

    @Override
    public void ZILEIError(String code, String msg) {

    }

    @Override
    public void ZISuccess(String result) {

    }

    @Override
    public void ZIFail(String code, String msg) {

    }

    @Override
    public void ZIError(String code, String msg) {

    }
}
