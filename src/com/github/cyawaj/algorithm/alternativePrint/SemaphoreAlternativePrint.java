package com.github.cyawaj.algorithm.alternativePrint;

import java.util.concurrent.Semaphore;
/**
 * 交替打印：信号量实现
 * TODO 是否有线程安全问题？
 * @author xavier
 * @date 2018-09-07
 */
public class SemaphoreAlternativePrint {
	final static int MAX_NUMBER = 100;
	volatile static int value = 1;
	public static void main(String[] args) {
		// 先打印奇数
		Semaphore oddSemaphore = new Semaphore(1);
		Semaphore evenSemaphore = new Semaphore(0);
		
		Thread oddThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// 最大数是偶数, 若不减去1，则会多打印一次MAX_NUMBER+1
				while(value <= MAX_NUMBER - 1) {
					try {
						oddSemaphore.acquire();
						System.out.println(Thread.currentThread().getName() + "=>" + value++);
						evenSemaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread evenThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(value <= MAX_NUMBER) {
					try {
						evenSemaphore.acquire();
						System.out.println(Thread.currentThread().getName() + "=>" + value++);
						oddSemaphore.release();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		oddThread.start();
		evenThread.start();
	}
}
