package com.proginn.gitinn;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GitinnCmd {

	public static Set<Integer> executeCmd(String cmd) {
		
		Process p;
		//用来保存frameList数组的索引
		Set<Integer> saveIndex = new HashSet<Integer>(); 
		String[] split = cmd.split(",");
		try {
			
			if (split.length == 2){//maven命令
				cmd = split[0];
				File dir = new File(split[1]);
				// 执行命令
				p = Runtime.getRuntime().exec(cmd,null,dir);
			}else {//jdeps命令
				// 执行命令
				p = Runtime.getRuntime().exec(cmd);
			}
			// 取得命令结果的输出流
			InputStream fis = p.getInputStream();
			// 用一个读输出流类去读
			// 用缓冲器读行
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "GB2312"));
			String line = null;
			//循环去获取输出流数据
			while ((line = br.readLine()) != null) {
				for(int i=0; i < FrameworkList.javaFrameList.length; i++){
					if (line.contains(FrameworkList.javaFrameList[i])) { // 解析该行是否包含常用框架。
						saveIndex.add(i);
						continue;
					}
				}
			}
			br.close();//关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		return saveIndex;
	}
}
