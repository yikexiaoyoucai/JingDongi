package presenter;

import common.ViewUtils;
import model.Model;
import view.View;


public class Presenter implements Model.Sousu,Model.Shanchu,Model.Chaxun{
    private Model model;
    private View view;

    public Presenter(View view) {
        this.view = view;
        model=new Model();
        model.setSousu(this);
        model.setShanchu(this);
        model.setChaxun(this);
    }


    public void search(String keywords)
    {
       model.search(keywords);
    }
    public void xiangqing(String pid){
        model.xiangqing(pid);
    }
    public void tianjia(String uid,String pid){
        model.tianjia(uid,pid);
    }
    public void chaxun(String uid){
        model.chaxun(uid);
    }
    public void shanchu(String uid,String pid){model.shanchu(uid,pid);}
    public void genggai(String uid,String sellerid,String pid,String selected,String num){
        model.genggai(uid,sellerid,pid,selected,num);
    }
    @Override
    public void Success(String result) {
      view.Success(result);
    }

    @Override
    public void Fail(String code, String msg) {

    }

    @Override
    public void Error(String code, String msg) {

    }

    @Override
    public void ShanchuSuccess(String result) {
       view.ShanchuSuccess(result);
    }

    @Override
    public void ShanchuFail(String code, String msg) {

    }

    @Override
    public void ShanchuError(String code, String msg) {

    }

    @Override
    public void ChaxunSuccess(String result) {
        view.ChaxunSuccess(result);
    }

    @Override
    public void ChaxunFail(String code, String msg) {

    }

    @Override
    public void ChaxunError(String code, String msg) {

    }
}
