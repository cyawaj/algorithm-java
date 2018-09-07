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
	/**
	 * 挖坑法
	 * @param array
	 * @param l
	 * @param r
	 */
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
	/**
	 * 指针交换法
	 * @param array
	 * @param l
	 * @param r
	 */
	public static void sort2(int[] array, int l, int r) {
		if (l < r) {
			int i = l, j = r, key = array[l];
			while (i < j) {
				while (i < j && array[j] >= key) {
					j--;
				}
				while (i < j && array[i] <= key) {
					i++;
				}
				if (i < j) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
			// 基准与ij相等的位置交换
			key = array[i];
			array[i] = array[l];
			array[l] = key;
			// 基准的位置为i
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
		int[] array2 = Arrays.copyOf(array, array.length);
		System.out.println("原本数组array:" + Arrays.toString(array));
		System.out.println("原本数组array2:" + Arrays.toString(array2));
		sort(array, 0, array2.length - 1);
		sort2(array2, 0, array.length - 1);
		System.out.println("排序后array:" + Arrays.toString(array));
		System.out.println("排序后array2:" + Arrays.toString(array2));
	}
}
