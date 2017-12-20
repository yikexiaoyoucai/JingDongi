package presenter;

import model.LunboModel;
import view.LunboView;


public class LunboPresenter implements LunboModel.Lunbo, LunboModel.Shangpin,LunboModel.MiaoSha,LunboModel.Tuijian{
  private LunboModel lunboModel;
    private LunboView lunboView;
    public LunboPresenter(LunboView lunboView) {
        this.lunboView = lunboView;
        lunboModel=new LunboModel();
        lunboModel.setLunbo(this);//
        lunboModel.setShangpin(this);
        lunboModel.setMiaoSha(this);
        lunboModel.setTuijian(this);
    }
    public void lunbo(String url){
        lunboModel.lunbo(url);
    }
    public void Shangpin(String url){
        lunboModel.Shangpin(url);
    }
    public  void Miaosha(String url){
        lunboModel.Miaosha(url);
    }
    public void Tuijian(String url){ lunboModel.Tuijian(url);}
    @Override
    public void Success(String result) {
       lunboView.Success(result);
    }

    @Override
    public void Fail(String code) {

    }

    @Override
    public void Error(String code) {

    }

    @Override
    public void ShangpinSuccess(String result) {
        lunboView.ShangpinSuccess(result);
    }

    @Override
    public void ShangpinFail(String code) {

    }

    @Override
    public void ShangpinError(String code) {

    }

    @Override
    public void MiaoShaSuccess(String result) {
        lunboView.MiaoShaSuccess(result);
    }

    @Override
    public void MiaoShaFail(String code) {

    }

    @Override
    public void MiaoShaError(String code) {

    }

    @Override
    public void TuijianSuccess(String result) {
        lunboView.TuijianSuccess(result);
    }

    @Override
    public void TuijianFail(String code) {

    }

    @Override
    public void TuijianError(String code) {

    }
}
