package presenter;

import model.ShangPinModel;
import view.ShangPinView;

/**
 * Created by asus on 2017/10/11.
 */

public class ShangPinPresenter implements ShangPinModel.Fenlei, ShangPinModel.ZILEI,ShangPinModel.ZI{
    private ShangPinModel shangPinModel;
    private ShangPinView shangPinView;

    public ShangPinPresenter(ShangPinView shangPinView) {
        this.shangPinView = shangPinView;
        shangPinModel=new ShangPinModel();
        shangPinModel.setFenlei(this);
        shangPinModel.setZILEI(this);
        shangPinModel.setZi(this);
    }
    public void fenlei(String url){
        shangPinModel.fenlei(url);
    }
    public void zilei(String cid){
        shangPinModel.zilei(cid);
    }
    public void zil(String pscid,String page){
        shangPinModel.zil(pscid,page);
    }
    @Override
    public void FenleiSuccess(String result) {
        shangPinView.FenleiSuccess(result);
    }

    @Override
    public void FenleiFail(String code, String msg) {
       shangPinView.FenleiFail(code,msg);
    }

    @Override
    public void FenleiError(String code, String msg) {
      shangPinView.FenleiError(code,msg);
    }

    @Override
    public void ZILEISuccess(String result) {
        shangPinView.ZILEISuccess(result);
    }

    @Override
    public void ZILEIFail(String code, String msg) {
        shangPinView.ZILEIFail(code,msg);
    }

    @Override
    public void ZILEIError(String code, String msg) {
        shangPinView.ZILEIFail(code,msg);
    }

    @Override
    public void ZISuccess(String result) {
        shangPinView.ZISuccess(result);
    }

    @Override
    public void ZIFail(String code, String msg) {
       shangPinView.ZIFail(code,msg);
    }

    @Override
    public void ZIError(String code, String msg) {
      shangPinView.ZIError(code,msg);
    }
}
