package com.proginn.gitinn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) throws Exception {
		// List<String> resultFileName = new ArrayList<String>();
		// String path = "D:/GitinnRepository/SSH-PhoneServer/";
		// File file = new File(path);
		// List<String> ergodic = ergodic(file, resultFileName);
		// for(String str : ergodic){
		// System.out.println(str.replaceAll("\\\\", "/"));
		// }
		// Process p = Runtime.getRuntime().exec("mvn dependency:list" "cd
		// D:/GitinnRepository/project47115");
		// Process p = Runtime.getRuntime().exec("CMD /C mvn dependency:list",
		// null, new File("D:/GitinnRepository/project47115"));
		// InputStream fis = p.getInputStream();
		// // 用一个读输出流类去读
		// // 用缓冲器读行
		// BufferedReader br = new BufferedReader(new InputStreamReader(fis,
		// "GB2312"));
		// String line = null;
		// //循环去获取输出流数据
		// while ((line = br.readLine()) != null) {
		// System.out.println(line);
		// }
		// br.close();//关闭流
		String test = "spring-boot-starter-web";
		boolean contains = test.contains("spring-webmvc");
		System.out.println(contains);
	}

	public static List<String> ergodic(File file, List<String> resultFileName) throws Exception {
		File[] files = file.listFiles();
		if (files == null) {
			return resultFileName;// 判断目录下是不是空的
		}
		for (File f : files) {
			if (f.isDirectory()) {// 判断是否文件夹
				if (f.getPath().contains("classes")) {
					resultFileName.add(f.getPath());
					break;
				}
				ergodic(f, resultFileName);// 调用自身,查找子目录
			}
		}
		return resultFileName;
	}
}
