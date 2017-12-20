package common;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.asus.jingdong.App;


public class ShareUtis {
    private final static String KEY = "JingSp";
    /**
     * 得到SharedPreferences对象
     * @return
     */
    public static SharedPreferences getPreferences() {
        return App.context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    /**
     * 存一行数据，uid
     * @param key
     * @param value
     */
    public static void putPreferences(String key, String value) {
        SharedPreferences.Editor mEditor = getPreferences().edit();
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * 获取uid的数据
     * @param key
     * @return
     */
    public static String getPreferencesValue(String key) {
        return getPreferences().getString(key, "");
    }

    /**
     * 清除指定数据
     * @param key
     */
    public static void clearPreferences(String key) {

        SharedPreferences.Editor mEditor = getPreferences().edit();
        mEditor.remove(key);
        mEditor.commit();
    }

    /**
     * 清空所有数据
     */
    public static void clearPreferences() {

        SharedPreferences.Editor mEditor = getPreferences().edit();
        mEditor.clear();
        mEditor.commit();
    }

}
