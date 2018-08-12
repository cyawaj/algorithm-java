package com.github.cyawaj.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序法
 * 
 * @author xavier
 * @date 2018-08-12
 */
public class QuickSort {
	public static void sort(int[] array, int l, int r) {
		if (l < r) {
			int i = l, j = r, key = array[l];
			while (i < j) {
				while (i < j && array[j] >= key) {
					j--;
				}
				if (i < j) {
					array[i++] = array[j];
				}
				while (i < j && array[i] <= key) {
					i++;
				}
				if (i < j) {
					array[j--] = array[i];
				}
			}
			array[i] = key;
			sort(array, l, i - 1);
			sort(array, i + 1, r);
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
		sort(array, 0, array.length - 1);
		System.out.println("排序后的数组:" + Arrays.toString(array));
	}
}
