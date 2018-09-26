package com.github.cyawaj.algorithm.gist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 处理文本
 * 
 * @author xavier
 * @date 2018-09-26
 */
public class SimplifiedRule {
	/** 类别 */
	private Integer type;
	/** 属性序号 */
	private Integer property;
	/** 0=移除;1=保留 */
	private Integer keep;
	/** 条件：0=包含；1=等于 */
	private Integer operator;
	/** 条件：关键字 */
	private String keyword;

	public SimplifiedRule(Integer property, Integer keep, Integer operator, String keyword) {
		this.property = property;
		this.keep = keep;
		this.operator = operator;
		this.keyword = keyword;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public Integer getKeep() {
		return keep;
	}

	public void setKeep(Integer keep) {
		this.keep = keep;
	}

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 过滤
	 * 
	 * @param line
	 * @return true保留(默认)|false移除
	 */
	public boolean filter(String line) {
		try {
			if (line != null && !line.isEmpty()) {
				line = line.replaceAll("\\s+", " ");
				String[] words = line.split(" ");
				if (words.length > property) {
					String word = words[property];
					boolean isKeep = keep == 1;
					return matchs(word) ? isKeep : !isKeep;
				}
			}
		} catch (Exception e) {
		}
		return true;
	}

	/**
	 * 是否符合关键字
	 * 
	 * @param word
	 * @return
	 */
	private boolean matchs(String word) {
		if (operator == 0 && word.contains(keyword)) {
			return true;
		} else if (operator == 1 && word.equals(keyword)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "属性[" + property + "]" + (keep == 1 ? "保留" : "剔除") + (operator == 0 ? "包含" : "等于") + "关键字" + keyword;
	}

	/**
	 * 过滤出符合条件的行
	 * 
	 * @param lines
	 * @param rules
	 * @return
	 */
	public static List<String> handle(List<String> lines, List<SimplifiedRule> rules) {
		if (rules != null && !rules.isEmpty() && lines != null && !lines.isEmpty()) {
			Stream<String> linesStream = lines.stream();
			for (SimplifiedRule simpliedRule : rules) {
				linesStream = linesStream.filter(e -> simpliedRule.filter(e));
			}
			return linesStream.collect(Collectors.toList());
		}
		return lines;
	}

	public static void main(String[] args) {
		List<String> lines = new ArrayList<>();
		String pattern = "%s %s %s %s";
		lines.add(String.format(pattern, "福建", "福州", "鼓楼", "A区"));
		lines.add(String.format(pattern, "浙江", "杭州", "江干", "B区"));
		lines.add(String.format(pattern, "福建", "福州", "晋安", "A区"));
		lines.add(String.format(pattern, "福建", "厦门", "湖里", "A区"));
		lines.add(String.format(pattern, "福建", "福州", "鼓楼", "BA区"));
		List<SimplifiedRule> rules = new ArrayList<>();
		rules.add(new SimplifiedRule(2, 1, 0, "鼓楼"));
		rules.add(new SimplifiedRule(3, 0, 1, "A区"));
		System.out.println(rules);
		List<String> result = handle(lines, rules);
		System.out.println(result);
	}
}
