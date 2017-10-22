package view;

/**
 * Created by asus on 2017/10/21.
 */

public interface View_A {
    void CSuccess(String result);
    void CFail(String code,String msg);
    void CError(String code,String msg);
    void XSuccess(String result);
    void XFail(String code,String msg);
    void XError(String code,String msg);
}
