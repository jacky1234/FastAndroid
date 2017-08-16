package com.jackyang.android.support.queue;

import android.os.Bundle;

/**
 * Created by jackyang on 2017/4/15.
 */

public interface Operation {

    void run(Queue queue, Bundle bundle);
}
