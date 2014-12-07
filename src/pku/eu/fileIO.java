package pku.edu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.print.attribute.standard.OutputDeviceAssigned;
import javax.swing.ProgressMonitor;
import javax.swing.ProgressMonitorInputStream;

public class fileIO {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		fileIO fileIO=new fileIO();
		fileIO.BufferedReaderDemo("part-r-00000");
		readFile();
		
		
	}
	public static void readFile(){
		Scanner scanner=new Scanner(System.in);
		String string=scanner.next();
		File f=new File(string);
		if (f.exists()) {
			try {
				int b;
				byte tom[]=new byte[125];
				FileInputStream input=new FileInputStream(f);
				ProgressMonitorInputStream in=new ProgressMonitorInputStream(null, "��ȡjava�ļ�",input);
				ProgressMonitor p=in.getProgressMonitor();
				while ((b=input.read(tom, 0,125))!=-1) {
					String s=new String(tom,0,125);
					System.out.print(s);
					Thread.sleep(2000);
				}
				input.close();
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}                                                                     
		else {
			System.out.println("��Ҫ��ȡ���ļ������ڣ�");
		}
	}
	public static void writeFile(){
		Scanner scanner=new Scanner(System.in);
		System.out.println("enter a  write file name:");
		String name=scanner.next();
		int b;
		System.out.println("enter u want:");
		String string=scanner.next();
		File f=new File(name+".txt");
		if (f.exists()) {
			try {
				FileWriter writer=new FileWriter(f,true);
				writer.write(string);
				writer.flush();
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
		else {
			try {
				f.createNewFile();
				FileWriter writer=new FileWriter(f,true);
				writer.write(string);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
    public void copyFile(String src,String dest) throws IOException{
        FileInputStream in=new FileInputStream(src);
        File file=new File(dest);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file);
        int c;
        byte buffer[]=new byte[1024];
        while((c=in.read(buffer))!=-1){
            for(int i=0;i<c;i++)
                out.write(buffer[i]);        
        }
        in.close();
        out.close();
    }
    
    
    public void renameFile(String path,String oldname,String newname){
        if(!oldname.equals(newname)){//�µ��ļ�������ǰ�ļ�����ͬʱ,���б�Ҫ����������
            File oldfile=new File(path+"/"+oldname);
            File newfile=new File(path+"/"+newname);
            if(newfile.exists())//���ڸ�Ŀ¼���Ѿ���һ���ļ������ļ�����ͬ��������������
                System.out.println(newname+"�Ѿ����ڣ�");
            else{
                oldfile.renameTo(newfile);
            } 
        } 
    }
    
    /**
     * 
     * @param path
     * @returnFileInputStream��ȡ�ļ�
     * @throws IOException
     */
    public String FileInputStreamDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        FileInputStream fis=new FileInputStream(file);
        byte[] buf = new byte[1024];
        StringBuffer sb=new StringBuffer();
        while((fis.read(buf))!=-1){
            sb.append(new String(buf));    
            buf=new byte[1024];//�������ɣ�������ϴζ�ȡ�������ظ�
        }
        return sb.toString();
    }
    /**
     * 
     * @param path
     * @return����BufferedReader��BufferedWriter
     * @throws IOException
     */
    public void BufferedReaderDemo(String path) throws IOException{
        File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
        	BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        String temp=null;
        StringBuffer sb=new StringBuffer();
        temp=br.readLine();
        while((temp=br.readLine())!=null){
        	System.out.println(temp);
        }
    }
    
    /**
     * ɾ��Ŀ¼
     * @param path
     */
    public void delDir(String path){
        File dir=new File(path);
        if(dir.exists()){
            File[] tmp=dir.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    delDir(path+"/"+tmp[i].getName());
                }
                else{
                    tmp[i].delete();
                }
            }
            dir.delete();
        }
    }

}
