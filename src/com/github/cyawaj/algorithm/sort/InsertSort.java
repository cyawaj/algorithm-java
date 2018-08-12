package com.github.cyawaj.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 * 
 * @author xavier
 * @date 2018-08-12
 */
public class InsertSort {
	public static void sort(int[] array) {
		int temp;
		for (int i = 1; i < array.length; i++) {
			// 共需要比较array.length - 1次
			for (int j = i; j > 0; j--) {
				if (array[j] < array[j - 1]) {
					// 小于最大的数，说明位置在前面
					temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				} else {
					// 大于或等于最大的数，说明位置在此处，无需替换
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		int length = 100;
		int[] array = new int[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			array[i] = random.nextInt(100);
		}
		System.out.println("原本数组:" + Arrays.toString(array));
		sort(array);
		System.out.println("排序后的数组:" + Arrays.toString(array));
	}
}
