package com.proginn.gitinn;

import java.util.Set;

public class GitinnMain {
	public static void main(String[] args) {

		String remotePath = "https://github.com/***.git";// 远程库路径
		String username = "***";// 仓库登录名
		String password = "***";// 仓库登录密码
		String projectKey = "project12345";//作为本地仓库项目目录，和远程仓库名一致
		
		try {
			//命令
			String cmd = GitinnClone.httpClone(projectKey,username, password, remotePath);
			//常用框架列表索引
			Set<Integer> result = GitinnCmd.executeCmd(cmd);
			System.out.println("该项目用到的框架技术有:");
			for (int i : result) {
				System.out.println(FrameworkList.javaFrameList[i]);// 输出常用框架
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
