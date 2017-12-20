package view;


public interface LoginView {
    void ReginSuccess(String result);
    void ReginFail(String code,String msg);
    void ReginError(String code,String msg);
    void LoginSuccess(String result);
    void LoginFail(String code,String msg);
    void LoginError(String code,String msg);
    void UpDateSuccess(String result);
    void UpDateFail(String code,String msg);
    void UpDateError(String code,String msg);
}

