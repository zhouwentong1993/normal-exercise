 线程池主要考虑了两个问题
 
    ①当执行大量异步任务时用来提升性能（减少每一个任务的调用时间）
    ②提供了资源管理的方式，包括线程、消费者
    
下面是线程池中核心的构造参数，含义分别如下：
* corePoolSize：线程池中存活的线程数
* maximumPoolSize：线程池中允许存活的最多线程数
* keepAliveTime：当线程池中线程数超过 corePoolSize 时，多余线程等待新任务的最大时间
* unit：等待时间单位，与 keepAliveTime 配对使用
* workQueue：工作队列，当任务数大于线程数时，任务的暂存队列
* threadFactory：线程生产者，线程池中所有的工作线程都是由 threadFactory 产生的
* handler：拒绝策略，当线程池不能再处理更多的任务时，触发
```java
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
```
首先从入口方法开始分析，ThreadPoolExecutor.execute 方法。

 ① 当工作线程数小于 corePoolSize 时，增加 worker。<br>
 ② 当线程池正在运行并且能够将任务放入缓冲队列时，再次检验一遍状态，如果此时线程池不处于运行状态时，移除掉刚加入的任务。<br> 
 ③ 拒绝任务。
 
```java
public void execute(Runnable command) {
    if (command == null)
        throw new NullPointerException();
    int c = ctl.get();
    if (workerCountOf(c) < corePoolSize) {
        if (addWorker(command, true))
            return;
        c = ctl.get();
    }
    if (isRunning(c) && workQueue.offer(command)) {
        int recheck = ctl.get();
        if (! isRunning(recheck) && remove(command))
            reject(command);
        else if (workerCountOf(recheck) == 0)
            addWorker(null, false);
    }
    else if (!addWorker(command, false))
        reject(command);
    }
```

然后来到 ThreadPoolExecutor.addWorker 方法，上半部分（前两个死循环）是通过 CAS 的手段先设置线程池状态，让工作者数量增加 1，
**然后创建 Worker 对象，调用 Worker 对象中的 thread 内部对象的 start 方法**，这里为什么要调用 thread 的 start 而不是 worker 自身的 start 呢？
我认为是为了让使用者更好地定义自己所需要的线程特征，用户可以通过自定义 ThreadFactory 来实现。如果线程创建失败，则回滚之前的操作。
```java
private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);
            if (rs >= SHUTDOWN &&
                ! (rs == SHUTDOWN &&
                   firstTask == null &&
                   ! workQueue.isEmpty()))
                return false;

            for (;;) {
                int wc = workerCountOf(c);
                if (wc >= CAPACITY ||
                    wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                if (compareAndIncrementWorkerCount(c))
                    break retry;
                c = ctl.get();  // Re-read ctl
                if (runStateOf(c) != rs)
                    continue retry;
            }
        }

        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    int rs = runStateOf(ctl.get());

                    if (rs < SHUTDOWN ||
                        (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        workers.add(w);
                        int s = workers.size();
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (! workerStarted)
                addWorkerFailed(w);
        }
        return workerStarted;
    }
```
接下来就看 Worker 类做了些什么工作，Worker 实现了两个接口，一个是 AQS，一个是 Runnable。
```java
private final class Worker
        extends AbstractQueuedSynchronizer
        implements Runnable
    {
        final Thread thread;
        Runnable firstTask;
        volatile long completedTasks;

        Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);
        }

        public void run() {
            runWorker(this);
        }

        // Lock methods
        //
        // The value 0 represents the unlocked state.
        // The value 1 represents the locked state.

        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock()        { acquire(1); }
        public boolean tryLock()  { return tryAcquire(1); }
        public void unlock()      { release(1); }
        public boolean isLocked() { return isHeldExclusively(); }

        void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
    }
```