package com.pku.edu;

import java.io.IOException;

 

public class SpiderRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] seeds={"http://localhost/openzone/"};
		String line="http://localhost";
		String savepath="D:\\javaworkspace\\openzone";
		String encoding="utf-8";
		Spider spider=new Spider(seeds, line, savepath, encoding);
		try {
			spider.run();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		} 
	}

}
