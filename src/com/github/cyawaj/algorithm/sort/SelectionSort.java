package com.github.cyawaj.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 * 
 * @author xavier
 * @date 2018-08-12
 */
public class SelectionSort {
	public static void sort(int[] array) {
		for(int i=0; i< array.length - 1; i++) {
			for(int j= i+1; j< array.length; j++) {
				if(array[i] > array[j]) {
					array[i] ^= array[j];
					array[j] ^= array[i];
					array[i] ^= array[j];
				}
			}
		}
	}
	
	public static void sort2(int[] array) {
		for(int i=0; i< array.length - 1; i++) {
			int minIndex = i;
			for(int j= i+1; j< array.length; j++) {
				if(array[minIndex] > array[j]) {
					minIndex = j;
				}
			}
			if(minIndex != i) {
				// 确保minIndex不等于i，即异或双方不是同一个变量
				array[i] ^= array[minIndex];
				array[minIndex] ^= array[i];
				array[i] ^= array[minIndex];
			}
		}
	}
	
	public static void main(String[] args) {
		int length = 10;
		int[] array = new int[length];
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			array[i] = random.nextInt(100);
		}
		System.out.println("原本数组:" + Arrays.toString(array));
		sort2(array);
		System.out.println("排序后的数组:" + Arrays.toString(array));
	}
}
