package view;

/**
 * Created by asus on 2017/10/8.
 */

public interface LunboView {
    void Success(String result);
    void Fail(String code);
    void Error(String code);
    void ShangpinSuccess(String result);
    void ShangpinFail(String code);
    void ShangpinError(String code);
    void MiaoShaSuccess(String result);
    void MiaoShaFail(String code);
    void MiaoShaError(String code);
    void TuijianSuccess(String result);
    void TuijianFail(String code);
    void TuijianError(String code);
}
