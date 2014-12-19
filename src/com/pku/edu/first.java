package com.shengsiyuan.decorator;

import java.io.File;
import java.io.IOException;

public class ConcreteComponent implements Component {
    public void doSomething() {
        System.out.println("����A");
    }
	
	// create file
	public File createFile() {
		File directory = new File("F:/atest");
		directory.mkdir();
		String prefix = "hello";
		String suffix = ".txt";
		File.createTempFile(prefix, suffix, directory);
		return directory;
	}
	
	// delete folder
	public static void delFolder(String folderPath) {
		try {
		   delAllFile(folderPath); // 删除完里面所有内容
		   String filePath = folderPath;
		   filePath = filePath.toString();
		   File myFilePath = new File(filePath);
		   myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
		   e.printStackTrace();
		}
	}
	
	// delete all files
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
		   return flag;
		}
		if (!file.isDirectory()) {
		   return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
		   if (path.endsWith(File.separator)) {
			temp = new File(path + tempList[i]);
		   } else {
			temp = new File(path + File.separator + tempList[i]);
		   }
		   if (temp.isFile()) {
			temp.delete();
		   }
		   if (temp.isDirectory()) {
			delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
			delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			flag = true;
		   }
		}
		return flag;
	} 
}
