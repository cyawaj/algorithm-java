package com.github.cyawaj.algorithm.gist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 获取Excel某个范围的所有列名
 * 
 * @author xavier
 * @date 2018-09-13
 */
public class ExcelColumnRange {
	/** xls: iv; xlsx：xfd (小写) */
	private static final String DEFAULT_MAX_COLUMN = "xfd";
	/** 小写字母组成的字符串 */
	private static Pattern LOWER_CASE_LETTER_PATTERN = Pattern.compile("[a-z]+");

	/**
	 * Excel某个范围的所有列
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<String> listBetween(String begin, String end, String maxColumn) throws Exception {
		// 默认最大列
		if (maxColumn == null || maxColumn.isEmpty()) {
			maxColumn = DEFAULT_MAX_COLUMN;
		}
		if (begin == null || end == null) {
			throw new IllegalArgumentException("参数不能为空！");
		}
		// 都转成小写
		begin = begin.toLowerCase();
		end = end.toLowerCase();
		int beginLength;
		int endLength;
		if (!LOWER_CASE_LETTER_PATTERN.matcher(begin).matches() || end.compareTo(maxColumn) > 0
				|| !LOWER_CASE_LETTER_PATTERN.matcher(end).matches() || (beginLength = begin.length()) > 3
				|| (endLength = end.length()) > 3 || beginLength > endLength
				|| (beginLength == endLength && begin.compareTo(end) > 0)) {
			throw new IllegalArgumentException("参数不合法！");
		}
		List<String> columns = new ArrayList<>();
		char[] beginCharArray = begin.toCharArray();
		char[] endCharArray = end.toCharArray();
		if (beginLength == 1) {
			// 开始列名：1个字母
			char beginChar0 = beginCharArray[0];
			char endChar0 = endCharArray[0];
			if (endLength == 1) {
				// 结束列名：1个字母
				between(columns, beginChar0, endChar0);
			} else if (endLength == 2) {
				// 结束列名：2个字每
				char endChar1 = endCharArray[1];
				between(columns, beginChar0, 'z');
				between(columns, 'a', 'a', endChar0, endChar1);
			} else {
				// 结束列名：3个字母
				char endChar1 = endCharArray[1];
				char endChar2 = endCharArray[2];
				between(columns, beginChar0, 'z');
				between(columns, 'a', 'a', 'z', 'z');
				between(columns, 'a', 'a', 'a', endChar0, endChar1, endChar2);
			}
		} else if (beginLength == 2) {
			// 开始列名：2个字母
			char beginChar0 = beginCharArray[0];
			char beginChar1 = beginCharArray[1];
			char endChar0 = endCharArray[0];
			char endChar1 = endCharArray[1];
			if (endLength == 2) {
				// 结束列名：2个字母
				between(columns, beginChar0, beginChar1, endChar0, endChar1);
			} else {
				// 技术列名：3个字母
				char endChar2 = endCharArray[2];
				between(columns, beginChar0, beginChar1, 'z', 'z');
				between(columns, 'a', 'a', 'a', endChar0, endChar1, endChar2);
			}
		} else {
			// 开始列名：3个字母；结束列名：3个字母
			char beginChar0 = beginCharArray[0];
			char beginChar1 = beginCharArray[1];
			char beginChar2 = beginCharArray[2];
			char endChar0 = endCharArray[0];
			char endChar1 = endCharArray[1];
			char endChar2 = endCharArray[2];
			between(columns, beginChar0, beginChar1, beginChar2, endChar0, endChar1, endChar2);
		}
		return columns;
	}

	/**
	 * Excel某个范围的所有列
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<String> listBetween(String begin, String end) throws Exception {
		return listBetween(begin, end, null);
	}

	/**
	 * 返回b0列-e0列之间的所有列
	 * 
	 * @param columns
	 * @param b0
	 * @param e0
	 */
	private static void between(List<String> columns, char b0, char e0) {
		while (b0 < e0 + 1) {
			columns.add("" + (char) b0++);
		}
	}

	/**
	 * 返回b0b1列-e0e1列的所有列
	 * 
	 * @param columns
	 * @param b0
	 * @param b1
	 * @param e0
	 * @param e1
	 */
	private static void between(List<String> columns, char b0, char b1, char e0, char e1) {
		for (char i = b0; i <= e0; i++) {
			for (char j = (i == b0) ? b1 : 'a', maxJ = (i == e0) ? e1 : 'z'; j <= maxJ; j++) {
				columns.add((char) i + "" + (char) j);
			}
		}
	}

	/**
	 * 返回b0b1b2列-e0e1e2列的所有列
	 * 
	 * @param columns
	 * @param b0
	 * @param b1
	 * @param b2
	 * @param e0
	 * @param e1
	 * @param e2
	 */
	private static void between(List<String> columns, char b0, char b1, char b2, char e0, char e1, char e2) {
		for (char i = b0; i <= e0; i++) {
			for (char j = (i == b0) ? b1 : 'a', maxJ = (i == e0) ? e1 : 'z'; j <= maxJ; j++) {
				for (char k = (j == b1) ? b2 : 'a', maxK = (j == e1) ? e2 : 'z'; k <= maxK; k++) {
					columns.add((char) i + "" + (char) j + "" + (char) k);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		List<String> xlsColumns = listBetween("a", "iv");
		System.out.println("xls all columns:");
		xlsColumns.forEach(System.out::println);
		System.out.println("xlsx all columns:");
		List<String> xlsxColumns = listBetween("a", "xfd");
		xlsxColumns.forEach(System.out::println);
	}
}
