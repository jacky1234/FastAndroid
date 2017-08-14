package com.jack.ioultimateencrypt.sample;

import com.jack.ioultimateencrypt.sample.module.Person;
import com.jackyang.android.support.utils.JsonUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void keystore_json() {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Person p = new Person();
            p.setName("jackyang" + i);
            p.setAge((i + 1) * 10);
            list.add(p);
        }

        final String json = JsonUtils.toJSON(list);
        Utils.P("ser:" + json);

        list = JsonUtils.fromJSON(json, List.class);
        Utils.P("deser:" + list.toString());
    }


}

