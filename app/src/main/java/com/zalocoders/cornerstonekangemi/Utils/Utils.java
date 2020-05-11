package com.zalocoders.cornerstonekangemi.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Utils implements Map<String, String> {

   public static Map<String,String> map;
    private static Utils utils = null;

    public static Utils getInstance() {

        if(utils == null) {
            utils = new Utils();
            map = new HashMap<>();

        }

        return utils;
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(@Nullable Object key) {
        return false;
    }

    @Override
    public boolean containsValue(@Nullable Object value) {
        return false;
    }

    @Nullable
    @Override
    public String get(@Nullable Object key) {
        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return map.put(key,value);
    }

    @Nullable
    @Override
    public String remove(@Nullable Object key) {
        return null;
    }

    @Override
    public void putAll(@NonNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @NonNull
    @Override
    public Set<String> keySet() {
        return null;
    }

    @NonNull
    @Override
    public Collection<String> values() {
        return map.values();
    }

    @NonNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return null;
    }
}
