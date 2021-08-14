package com.zhangmagle.io.tutorial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReverseOuput {

	public static void main(String[] args) throws IOException {
		RandomAccessFile in = null;
		
		FileOutputStream out = null;
		File file = null;
		
		try {
			file = new File("main/com/zhangmagle/io/tutorial/number.txt");
			in = new RandomAccessFile(file, "r");
			
			out = new FileOutputStream("main/com/zhangmagle/io/tutorial/reverse.txt");
			
			int c;
			
			
			long index, size = file.length();
			index = size -1 ;
			
			while(index >= 0) {
				in.seek(index);
				c = in.read();
				out.write(c);
				index--;
			}
		
		} finally {
			if (in != null) {
				in.close();
			}
			
			if (out != null) {
				out.close();
			}
		}
	}
}
