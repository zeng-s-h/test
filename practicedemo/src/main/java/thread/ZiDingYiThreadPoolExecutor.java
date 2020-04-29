package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author 小白i
 * @date 2020/4/29
 */
public class ZiDingYiThreadPoolExecutor {

    /**
     * 首先，程序会将线程放入到任务队列里面，当队列大小不够的时候多的任务会分给线程直接操作（此时就是核心线程数），当核心线程数不够的时候就会创建新的线程
     * 当最大线程加上核心线程还是不够的时候就会用主线程
     *
     * @param args
     */
    public static void main(String[] args) {
        // 创建数组型缓冲等待队列
        BlockingQueue<Runnable> bq = new ArrayBlockingQueue<Runnable>(2);
        //threadFactory
        ThreadFactory myThread = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        // ThreadPoolExecutor:创建自定义线程池，池中保存的线程数为3，允许最大的线程数为6
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 6, 50, TimeUnit.MILLISECONDS, bq, myThread);

        // 创建3个任务
        Runnable t1 = new MyThread();
        Runnable t2 = new MyThread();
        Runnable t3 = new MyThread();
        Runnable t4 = new MyThread();
        Runnable t5 = new MyThread();
        Runnable t6 = new MyThread();

        // 3个任务在分别在3个线程上执行
        tpe.execute(t1);
        tpe.execute(t2);
        tpe.execute(t3);
        tpe.execute(t4);
        tpe.execute(t5);
        tpe.execute(t6);

        // 关闭自定义线程池
        tpe.shutdown();
    }

}
