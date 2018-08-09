package com.github.cyawaj.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序：时间复杂度O(n2)
 * 
 * @author xavier
 * @date 2018-08-09
 */
public class BubbleSort {
	public static void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = 0; j < array.length - i; j++) {
				if (array[j] > array[j + 1]) {
					// 1.中间变量交换
					// int temp = array[j];
					// array[j] = array[j + 1];
					// array[j + 1] = temp;
					// 2.异或交换: 一个数与另一个数异或两次等于自身
					array[j] = array[j] ^ array[j + 1];
					array[j + 1] = array[j] ^ array[j + 1];
					array[j] = array[j + 1] ^ array[j];
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
