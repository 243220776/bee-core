package com.yestae.bee.tools.cache;

import java.util.List;

public interface DataTimeWindowListener<T> {
    /**
     * 监听失效的数据，该方法的实现尽量是轻量级的，如果有复杂的业务处理建议异步处理。 该监听会堵塞时间窗的计时器，堵塞越久时间窗的数据失效准确性越低
     *
     * @param data
     */
    public void onInvalid(List<T> data);
}
