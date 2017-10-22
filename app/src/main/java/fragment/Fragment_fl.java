package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.jingdong.R;
import com.example.asus.jingdong.ZILEIActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.RecycleOnewAdapter;
import adapter.RecycleTwoAdapter;
import bean.SP;
import presenter.ShangPinPresenter;
import view.ShangPinView;

/**
 * Created by asus on 2017/10/12.
 */

public class Fragment_fl extends Fragment implements ShangPinView {
    private View view;
    private RecyclerView rrv;
    private ShangPinPresenter sp;
    private List<SP> list;
    private RecycleOnewAdapter ra;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getActivity(), R.layout.fl_item,null);
        sp=new ShangPinPresenter(this);
        int cid= (int) getArguments().get("cid");
        System.out.println("cid+"+cid);
        sp.zilei(cid+"");
        initview();
        initdata();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sp=new ShangPinPresenter(this);
        int cid= (int) getArguments().get("cid");
        System.out.println("cid+"+cid);
        sp.zilei(cid+"");
        initview();
        initdata();
    }

    private void initdata() {
        list=new ArrayList<>();
    }

    private void initview() {
        rrv=view.findViewById(R.id.rrv);
    }

    public  static Fragment_fl getIns(int cid){
         Bundle bundle=new Bundle();
        bundle.putInt("cid",cid);
        Fragment_fl fragment_fl=new Fragment_fl();
        fragment_fl.setArguments(bundle);
        return  fragment_fl;
    }

    @Override
    public void FenleiSuccess(String result) {

    }

    @Override
    public void FenleiFail(String code, String msg) {

    }

    @Override
    public void FenleiError(String code, String msg) {

    }

    @Override
    public void ZILEISuccess(String result) {
       try{
           JSONObject obj=new JSONObject(result);
           JSONArray data=obj.getJSONArray("data");
           if(data!=null&&data.length()>0){
               for (int i = 0; i <data.length() ; i++) {
                   JSONObject arr= (JSONObject) data.get(i);
                   SP sp=new SP();
                   sp.name=arr.getString("name");
                   JSONArray objj=arr.getJSONArray("list");
                   if(objj!=null&&objj.length()>0){
                       for (int j = 0; j <objj.length() ; j++) {
                           JSONObject dd= (JSONObject) objj.get(i);
                           sp.zname=dd.getString("name");
                           sp.icon=dd.getString("icon");
                           sp.pscid=dd.getInt("pscid");
                       }

                   }
                   list.add(sp);
               }
           }
           getActivity().runOnUiThread(new Runnable() {
               @Override
               public void run() {
                   rrv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                   ra=new RecycleOnewAdapter(list,getActivity());
                   rrv.setAdapter(ra);


               }
           });
       }catch (Exception e){
           e.printStackTrace();
       }
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
