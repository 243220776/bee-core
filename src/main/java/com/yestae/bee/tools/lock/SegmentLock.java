package com.yestae.bee.tools.lock;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 默认同时支持16个线程并发，可增大
 *
 * @Author: zhangyaxin
 * @Date: 2021/8/30
 */
public class SegmentLock {

    private Integer segments = 16;

    private final Object NULL = new Object();

    private static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash

    private final HashMap<Object, ReentrantLock> lockHashMap = new HashMap<>();

    public SegmentLock() {
        init(null, false);
    }

    public SegmentLock(boolean fair) {
        init(segments, fair);
    }

    public SegmentLock(int length, boolean fair) {
        init(length, fair);
    }

    private void init(Integer length, boolean fair) {
        segments = length != null ? length : segments;
        for (int i = 0; i < segments; i++) {
            lockHashMap.put(i, new ReentrantLock(fair));
        }
    }

    private final int spread(int h) {
        return ((h ^ (h >>> 16)) & HASH_BITS) % segments;
    }

    private int getIndex(Object key) {
        key = key == null ? NULL : key;
        int index = spread(key.hashCode());
        return index < 0 ? 0 : index;
    }

    public void lock(Object key) {
        lockHashMap.get(getIndex(key)).lock();
    }

    public void unlock(Object key) {
        lockHashMap.get(getIndex(key)).unlock();
    }

    public boolean tryLock(Object key, long timeout, TimeUnit unit) throws InterruptedException {
        ReentrantLock reentrantLock = lockHashMap.get(getIndex(key));
        return reentrantLock.tryLock(timeout, unit);
    }
}
