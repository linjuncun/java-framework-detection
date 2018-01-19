package com.proginn.gitinn;

import java.io.File;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class GitinnClone {

	public static String httpClone(String projectKey, String username, String password, String remotePath)
			throws Exception {

		// 本地仓库路径
		String localPath = "D:/GitinnRepository/" + projectKey;
		File file = new File(localPath);
		boolean isCreateDir = file.mkdir();
		// 用于标志该项目类别：true为maven项目，false为普通项目
		boolean flag = false;

		if (isCreateDir) {// isCreateDir为true时创建目录成功
			// 设置远程服务器上的用户名和密码
			UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(
					username, password);

			// 克隆代码库命令
			CloneCommand cloneCommand = Git.cloneRepository();
			Git repo1 = cloneCommand.setURI(remotePath) // 设置远程URI
					.setBranch("master") // 设置clone下来的分支
					.setDirectory(new File(localPath)) // 设置下载存放路径
					.setCredentialsProvider(usernamePasswordCredentialsProvider) // 设置权限验证
					.call();
			
			repo1.close();
		}

		String[] list = file.list();
		for (String fileName : list) {
			if ("pom.xml".equals(fileName)) {
				flag = true;
				break;
			}
		}
		if (flag) {// maven项目
			return "CMD /C mvn dependency:list," + localPath;
		} else {// 普通项目
			return  "jdeps " + GitinnGetDirectoryPath.getPath(file);
		}
	}
}
