package com.example.demo.shujujiegou.ch哈希表;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Kingcym
 * @Description:
 * @Date: 2017/11/28 9:48
 *
 * https://www.cnblogs.com/leesf456/p/5242233.html
 */
public class MyHashMap<K,V> extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable {
    /**
     * 默认初始容量-必须是2次幂。
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    /**
     * The maximum capacity
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 加载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;




















    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
