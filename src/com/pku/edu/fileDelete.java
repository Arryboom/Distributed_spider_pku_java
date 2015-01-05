package pku.eu;

import java.io.File;
import java.io.FileInputStream;

public class fileDel {

    /**
     * @param args
     * @throws Exception
     */
    /*public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
		fileDel f=new fileDel();
	       File dir=new File("data");
	        if(dir.isDirectory()){
	            File[] tmp=dir.listFiles();
	            for (int i = 0; i < tmp.length; i++) {
	            	if (f.getFileSizes(tmp[i])==0) {
						tmp[i].delete();
					}
				}
	}
	}
	*/
    public long getFileSizes(File f) throws Exception {//ȡ���ļ���С
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            s = fis.available();
        } else {
            f.createNewFile();
            System.out.println("�ļ�������");
        }
        return s;
    }
	
	public static void getFile() {
		int i=1;
		String path="rule2";
		try {
			File file=new File(path+".txt");
			File newfile=new File("rules1.txt");
			newfile.createNewFile();
			if(!file.exists()||file.isDirectory())
				throw new FileNotFoundException();
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			BufferedWriter pw=new BufferedWriter(new FileWriterWithEncoding(newfile, "UTF-8",true));
			String temp=null;
			while ((temp=br.readLine())!=null) {
				i++;
				System.out.println(temp);
				//pw.append(temp);
				pw.write(temp);
				pw.newLine();
			}
			pw.flush();
			System.out.println(i);
		}catch(Exception e){
			System.out.println("file resource error");
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



