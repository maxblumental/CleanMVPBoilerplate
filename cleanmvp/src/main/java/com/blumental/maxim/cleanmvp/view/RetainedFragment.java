package com.blumental.maxim.cleanmvp.view;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class RetainedFragment extends Fragment {

    private Map<String, Object> map;

    public RetainedFragment() {
        super();
        setRetainInstance(true);

        map = new HashMap<>();
    }

    public void saveObject(String key, Object object) {
        map.put(key, object);
    }

    public <R> R retrieveObject(Class<R> objectClass, String key) {

        Object o = map.get(key);

        if (objectClass.isInstance(o)) {

            @SuppressWarnings("unchecked")
            R value = (R) o;

            return value;
        }

        return null;
    }
}
