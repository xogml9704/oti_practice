package ch14.sec09.exam01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
	public static void main(String[] args) {
		// 스레드풀 생성
		// ExecutorService executorService = Executors.newFixedThreadPool(5);
		// ExecutorService executorService = Executors.newFixedThreadPool();
		ExecutorService threadPool = new ThreadPoolExecutor(
				3,
				100,
				120L,
				TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>()
				);
				
		// 작업 생성과 처리 요청
		// 스레드풀 종료
		// executorService.shutdown();
		threadPool.shutdown();
	}
}
