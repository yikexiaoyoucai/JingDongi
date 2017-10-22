package presenter;

import model.LoginModel;
import view.LoginView;

/**
 * Created by asus on 2017/10/10.
 */

public class LoginPresenter implements LoginModel.Regin,LoginModel.Login,LoginModel.UpDate {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel=new LoginModel();
        loginModel.setRegin(this);
        loginModel.setLogin(this);
        loginModel.setUpDate(this);

    }
    public void regin(String mobile,String pass){
        loginModel.regin(mobile,pass);

    }
    public void updata(String uid,String nickname){
        loginModel.update(uid,nickname);
    }
    public void login(String mobile,String pass){
        loginModel.login(mobile,pass);
    }
    @Override
    public void ReginSuccess(String result) {
       loginView.ReginSuccess(result);
    }

    @Override
    public void ReginFail(String code, String msg) {
       loginView.ReginFail(code,msg);
    }

    @Override
    public void ReginError(String code, String msg) {
      loginView.ReginError(code,msg);
    }

    @Override
    public void LoginSuccess(String result) {
        loginView.LoginSuccess(result);
    }

    @Override
    public void LoginFail(String code, String msg) {
        loginView.LoginFail(code,msg);
    }

    @Override
    public void LoginError(String code, String msg) {
       loginView.LoginError(code,msg);
    }

    @Override
    public void UpDateSuccess(String result) {
        loginView.UpDateSuccess(result);
    }

    @Override
    public void UpDateFail(String code, String msg) {

    }

    @Override
    public void UpDateError(String code, String msg) {

    }
}
