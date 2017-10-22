package presenter;

import model.Model_A;
import view.View_A;

/**
 * Created by asus on 2017/10/21.
 */

public class Presenter_A implements Model_A.CHUANGJIAN,Model_A.XIUGAIDINGDAN {
    private Model_A model_a;
    private View_A view_a;
    public Presenter_A(View_A view_a) {
        this.view_a = view_a;
        model_a=new Model_A();
        model_a.setChuangjian(this);
        model_a.setXiugaidingdan(this);
    }
    public void chuangjian(String uid,String price){
        model_a.chuangjian(uid,price);
    }
    public void xiugaidingdan(String uid,String status,String orderId){
        model_a.xiugaidingdan(uid,status,orderId);
    }
    public void dingdan(String uid){
        model_a.dingdan(uid);
    }
    @Override
    public void CSuccess(String result) {
        view_a.CSuccess(result);
    }

    @Override
    public void CFail(String code, String msg) {

    }

    @Override
    public void CError(String code, String msg) {

    }

    @Override
    public void XSuccess(String result) {

    }

    @Override
    public void XFail(String code, String msg) {

    }

    @Override
    public void XError(String code, String msg) {

    }
}
