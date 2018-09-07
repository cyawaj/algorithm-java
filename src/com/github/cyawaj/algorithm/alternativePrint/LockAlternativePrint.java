package com.github.cyawaj.algorithm.alternativePrint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.github.cyawaj.algorithm.alternativePrint.common.NumberWrapper;
/**
 * 交替打印：可重入锁
 * @author admin
 * @date 2018-09-07
 */
public class LockAlternativePrint {
	/** 打印最大值 */
	static final int MAX_NUMBER = 100;
	public static void main(String[] args) {
		NumberWrapper number = new NumberWrapper();
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		Thread oddThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(number.value <= MAX_NUMBER) {
					lock.lock();
					try {
						if(!number.flag) {
							System.out.println(Thread.currentThread().getName() + "=>" + number.value ++);
							number.flag = true;
							condition.signal();
						} else {
							try {
								condition.await();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} finally {
						lock.unlock();
					}
				}
			}
		});
		Thread eventThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(number.value <= MAX_NUMBER) {
					lock.lock();
					try {
						if(number.flag) {
							System.out.println(Thread.currentThread().getName() + "=>" + number.value ++);
							number.flag = false;
							condition.signal();
						} else {
							try {
								condition.await();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} finally {
						lock.unlock();
					}
				}
			}
		});
		oddThread.start();
		eventThread.start();
	}
}
