package com.github.cyawaj.algorithm.alternativePrint;

import com.github.cyawaj.algorithm.alternativePrint.common.NumberWrapper;

/**
 * 交替打印：同步方式实现
 * 
 * @author xavier
 * @date 2018-09-07
 */
public class SynchronizedAlternativePrint {
	/** 打印最大值 */
	final static int MAX_NUMBER = 100;

	public static void main(String[] args) {
		NumberWrapper number = new NumberWrapper();
		// 打印奇数线程
		Thread oddThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (number.value <= MAX_NUMBER) {
					synchronized (number) {
						if (!number.flag) {
							System.out.println(Thread.currentThread().getName() + "=>" + number.value++);
							number.flag = true;
							number.notify();
						} else {
							try {
								number.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		// 打印偶数线程
		Thread evenThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (number.value <= MAX_NUMBER) {
					synchronized (number) {
						if (number.flag) {
							System.out.println(Thread.currentThread().getName() + "=>" + number.value++);
							number.flag = false;
							number.notify();
						} else {
							try {
								number.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		});
		oddThread.start();
		evenThread.start();
	}
}