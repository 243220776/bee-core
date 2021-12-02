package com.yestae.bee.tools.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: YX-ZHANG
 * @create: 2021-11-29 14:22
 */
public class CustomThreadPool {

    private static final Logger logger = LoggerFactory.getLogger(CustomThreadPool.class.getName());

    private final ThreadPoolExecutor threadPoolExecutor;

    private final String bizName;

    private static final AtomicLong atomicLong = new AtomicLong();

    private final BlockingQueue<Runnable> workQueue;

    public CustomThreadPool(int corePoolSize, int maximumPoolSize, String bizName) {
        this(corePoolSize, maximumPoolSize, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20000), bizName);
    }

    public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit timeUnit, BlockingQueue<Runnable> workQueue, String bizName) {
        this.bizName = bizName;
        this.workQueue = workQueue;
        this.threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue,
                new CustomThreadFactory("CustomThreadPool"), new CustomRejectedExecutionHandler());
    }

    public void execute(Runnable runnable) {
        this.threadPoolExecutor.execute(runnable);
    }

    public void stop() {
        this.threadPoolExecutor.shutdown();
        while (true) {
            if (threadPoolExecutor.isTerminated()) {
                logger.info("{} finish closed.", bizName);
                break;
            }
        }
    }

    //自定义拒绝策略
    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.error("线程被线程拒绝，没有被执行，第{}个", atomicLong.getAndIncrement());
            //可以往消息队列中间件里面放 可以发Email等等
        }
    }

    //自定义线程工厂
    private static class CustomThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private final AtomicInteger nextId = new AtomicInteger(1);

        /**
         * 定义线程组名称，在 jstack 问题排查时，非常有帮助
         */
        public CustomThreadFactory(String whatFeaturOfGroup) {
            namePrefix = "From CustomThreadFactory's " + whatFeaturOfGroup + "-Worker-";
        }

        @Override
        public Thread newThread(Runnable task) {
            String name = namePrefix + nextId.getAndIncrement();
            Thread thread = new Thread(null, task, name, 0);
            return thread;
        }
    }
}
