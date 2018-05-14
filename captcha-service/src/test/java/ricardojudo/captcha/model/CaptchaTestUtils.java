package ricardojudo.captcha.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CaptchaTestUtils {

	public static void runMultiple(final int threads, final int loop, final RunMultiple runnable)
			throws InterruptedException {
		ExecutorService es = Executors.newCachedThreadPool();
		for (int j = 0; j < threads; j++) {
			es.execute(()->{
					for (int i = 0; i < loop; i++) {
						System.out.println(Thread.currentThread().getName() + " : " + i);
						try {
							runnable.run();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			);
		}
		es.shutdown();
		es.awaitTermination(1, TimeUnit.HOURS);
	}

	@FunctionalInterface
	public static interface RunMultiple {
		void run() throws Exception;
	}
}
