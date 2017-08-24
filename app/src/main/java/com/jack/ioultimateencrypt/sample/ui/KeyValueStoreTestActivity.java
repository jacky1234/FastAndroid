package com.jack.ioultimateencrypt.sample.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Spinner;

import com.jack.ioultimateencrypt.sample.R;
import com.jack.ioultimateencrypt.sample.base.BaseActivity;
import com.jack.ioultimateencrypt.sample.module.Person;
import com.jack.test.logger.Log;
import com.jackyang.android.support.repository.KeyValueStore;
import com.jackyang.android.support.repository.support.prefs.SharedPreferenceKeyValueStore;

import java.util.ArrayList;
import java.util.List;

/**
 * 2017/8/7.
 * github:[https://github.com/jacky1234]
 *
 * @author jackyang
 *         SP保存示例
 */

public class KeyValueStoreTestActivity extends BaseActivity {
    private SharedPreferences sharedPreferences;
    private KeyValueStore keyValueStore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("blackboard_pref", Context.MODE_PRIVATE);
        keyValueStore = new SharedPreferenceKeyValueStore(sharedPreferences);
    }

    @Override
    protected void description() {

    }

    @Override
    protected int getArrayId() {
        return R.array.keyValueStore;
    }

    private Person person;
    private List<Person> list;

    @Override
    protected void onSpinnerItemClick(Spinner sp, View view, int position) {
        switch (position) {
            case 0:
                keyValueStore.set("int", 100);
                Log.d(TAG, "store int :" + 100);
                break;
            case 1:
                final Integer integer = keyValueStore.get("int", Integer.class);
                Log.d(TAG, "read int :" + integer);
                break;
            case 2:
                person = new Person();
                person.setAge(100);
                person.setName("jackyang");
                keyValueStore.set("person", person);
                Log.d(TAG, "save json:" + person.toString());
                break;
            case 3:
                Log.d(TAG, "read json:" + keyValueStore.get("person", Person.class).toString());
                break;
            case 4:
                list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    person = new Person();
                    person.setAge((i + 1) * 10);
                    person.setName("jackyang" + i);
                    list.add(person);
                }
                keyValueStore.set("list", list);
                Log.d(TAG, "save list:" + list.toString());
                break;
            case 5:
                list = keyValueStore.getList("list", Person.class);
                Log.d(TAG, "read list:" + list.toString());
                break;
        }
    }


}
