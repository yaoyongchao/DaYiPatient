package com.fh.baselib.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Author: Austin
 * Time: 2018/8/2
 * Description: 保存对象
 *
 * // 保存一个泛型对象
 SpUtils.putObject(context, list,
 new TypeToken<List<Person>>() { }.getType());

 // 获取一个泛型对象
 List<Person> list2 = SpUtils.getObject(context,
 new TypeToken<List<Person>>() { }.getType());
 *
 */
public class SPObjUtil {
    private static final String DEFAULT_SP_NAME = "dykt_p_sp";

    // 通过类名字去获取一个对象
    public static <T> T getObject(Context context, Class<T> clazz) {
        String key = getKey(clazz);
        String json = getString(context, key, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    // 通过Type去获取一个泛型对象
    public static <T> T getObject(Context context, Type type) {
        String key = getKey(type);
        String json = getString(context, key, null);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 保存一个对象，object必须是普通类，而不是泛型，如果是泛型,请使用 {@link
     * //SpUtils#putObject(Context, Object, Type)}
     *
     * @param context
     * @param object
     */
    public static void putObject(Context context, Object object) {
        String key = getKey(object.getClass());
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(context, key, json);
    }

    /**
     * 保存一个泛型对象
     *
     * @param context
     * @param object
     * @param type    如果你要保存 List<Person> 这个类, type应该 传入 new TypeToken<List<Person>>() {}.getType()
     */
    public static void putObject(Context context, Object object, Type type) {
        String key = getKey(type);
        Gson gson = new Gson();
        String json = gson.toJson(object);
        putString(context, key, json);
    }

    public static void removeObject(Context context, Class<?> clazz) {
        remove(context, getKey(clazz));
    }

    public static void removeObject(Context context, Type type) {
        remove(context, getKey(type));
    }

    public static String getKey(Class<?> clazz) {
        return clazz.getName();
    }

    public static String getKey(Type type) {
        return type.toString();
    }

    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove(key);
        edit.commit();
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(DEFAULT_SP_NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }
}
