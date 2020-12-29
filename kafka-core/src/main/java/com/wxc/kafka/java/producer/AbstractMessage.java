package com.wxc.kafka.java.producer;

import com.alibaba.fastjson.JSON;

/**
 * @author cAn
 */
public abstract class AbstractMessage {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
