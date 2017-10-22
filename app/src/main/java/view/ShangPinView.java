package view;

/**
 * Created by asus on 2017/10/11.
 */

public interface ShangPinView {
    void FenleiSuccess(String result);
    void FenleiFail(String code,String msg);
    void FenleiError(String code,String msg);
    void ZILEISuccess(String result);
    void ZILEIFail(String code,String msg);
    void ZILEIError(String code,String msg);
    void ZISuccess(String result);
    void ZIFail(String code,String msg);
    void ZIError(String code,String msg);
}
