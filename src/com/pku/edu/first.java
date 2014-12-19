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
	
	// delete directory
	    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
	
	// delete file
	/**
	* 删除单个文件
	* @param   sPath    被删除文件的文件名
	* @return 单个文件删除成功返回true，否则返回false
	*/
    public boolean deleteFile(String sPath) {
        flag = false;
        file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
}
