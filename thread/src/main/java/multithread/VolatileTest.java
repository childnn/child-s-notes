package multithread;

/**
 * ~~ Talk is cheap. Show me the code. ~~ :-)
 *
 * @author MiaoOne
 * @since 2019/12/28 14:40
 */
public class VolatileTest {
    public volatile int inc = 0;

    public static void main(String[] args) {
        final VolatileTest test = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    test.increase();
            }).start();
        }

        while (Thread.activeCount() > 1)  // 保证前面的线程都执行完
            Thread.yield();

        System.out.println(test.inc);
    }

    // ++ 操作并不会保证原子性
    public void increase() {
        inc++;
    }
}
