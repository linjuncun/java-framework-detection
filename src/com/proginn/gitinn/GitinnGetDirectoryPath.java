package com.proginn.gitinn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GitinnGetDirectoryPath {

	public static String getPath(File file) {// 获取classes或者lib目录路径

		List<String> classesList = GitinnGetDirectoryPath.classesErgodic(file, new ArrayList<String>());
		if (!classesList.isEmpty()) {
			return classesList.get(0);
		} else {//如果找不到classes目录，再找lib目录
			List<String> libList = GitinnGetDirectoryPath.libErgodic(file, new ArrayList<String>());
			if (!libList.isEmpty()) {
				return libList.get(0) + "\\*.jar";
			} else {
				return null;// 如果class/lib目录都找不到则返回null
			}
		}
	}

	public static List<String> classesErgodic(File file, List<String> resultFileName) {// 递归获取classes目录的路径
		File[] files = file.listFiles();
		String key = "classes";// classes目录
		if (files == null) {
			return resultFileName;// 判断目录下是不是空的
		}
		for (File f : files) {
			if (f.isDirectory()) {// 判断是否文件夹
				if (f.getPath().endsWith(key)) {
					resultFileName.add(f.getPath());
					break;
				}
				classesErgodic(f, resultFileName);// 调用自身,查找子目录
			}
		}

		return resultFileName;
	}

	public static List<String> libErgodic(File file, List<String> resultFileName) {// 递归获取lib目录的路径
		File[] files = file.listFiles();
		String key = "lib";// lib目录
		if (files == null) {
			return resultFileName;// 判断目录下是不是空的
		}
		for (File f : files) {
			if (f.isDirectory()) {// 判断是否文件夹
				if (f.getPath().endsWith(key)) {
					resultFileName.add(f.getPath());
					break;
				}
				libErgodic(f, resultFileName);// 调用自身,查找子目录
			}
		}

		return resultFileName;
	}
}
