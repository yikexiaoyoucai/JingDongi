package view;


public interface View_A {
    void CSuccess(String result);
    void CFail(String code,String msg);
    void CError(String code,String msg);
    void XSuccess(String result);
    void XFail(String code,String msg);
    void XError(String code,String msg);
}
