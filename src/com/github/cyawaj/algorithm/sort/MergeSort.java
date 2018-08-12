package com.github.cyawaj.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序
 * 
 * @author xavier
 * @date 2018-08-12
 */
public class MergeSort {
	public static void sort(int array[], int left, int right, int[] temp) {
		if (left < right) {
			int middle = (left + right) / 2;
			sort(array, left, middle, temp);
			sort(array, middle + 1, right, temp);
			merge(array, left, middle, right, temp);
		}
	}

	public static void merge(int array[], int left, int middle, int right, int[] temp) {
		int i = left;
		int j = middle + 1;
		int t = 0;
		while (i <= middle && j <= right) {
			if (array[i] < array[j]) {
				temp[t++] = array[i++];
			} else {
				temp[t++] = array[j++];
			}
		}
		while (i <= middle) {
			temp[t++] = array[i++];
		}
		while (j <= right) {
			temp[t++] = array[j++];
		}
		System.out.println("left:" + left + ", right:" + right + ", temp[]:"
				+ Arrays.toString(Arrays.copyOfRange(temp, 0, right - left + 1)));
		t = 0;
		while (left <= right) {
			array[left++] = temp[t++];
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
		sort(array, 0, array.length - 1, new int[array.length]);
		System.out.println("排序后的数组:" + Arrays.toString(array));
	}
}
