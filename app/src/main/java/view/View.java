package view;


public interface View {
    void Success(String result);
    void Fail(String code,String msg);
    void Error(String code,String msg);
    void ShanchuSuccess(String result);
    void ShanchuFail(String code,String msg);
    void ShanchuError(String code,String msg);
    void ChaxunSuccess(String result);
    void ChaxunFail(String code,String msg);
    void ChaxunError(String code,String msg);
}
