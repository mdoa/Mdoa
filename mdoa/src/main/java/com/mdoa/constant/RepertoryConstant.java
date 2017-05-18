package com.mdoa.constant;

public class RepertoryConstant {
	
	/**
	 * 方便获取色值的辅助计数器
	 */
	private static int count=0;
	
	/**
	 * 存储20种色值的字符串数组
	 */
	private static String[] colors = {
			"#5b5b5b","#930000","#d9006c","#ae00ae","#6f00d2",
			"#0000c6","#005ab5","#007979","#019858","009100",
			"#73bf00","#8c8c00","#ae8f00","#d26900","bb3d00",
			"#804040","#707038","#3d7878","#5151a2","7e3d76"
	};
	
	/**
	 * 获取色值
	 * @return
	 */
	public static String getColor(){
		count++;
		return colors[count];
	}
	
	/**
	 * 将count置为-1
	 */
	public static void closeCount(){
		count = -1;
	}
}
