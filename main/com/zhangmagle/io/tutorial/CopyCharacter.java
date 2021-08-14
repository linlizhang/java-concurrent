package com.zhangmagle.io.tutorial;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyCharacter {

	public static void main(String[] args) throws IOException {
		FileReader reader = null;
		FileWriter writer = null;
		
		try {
			reader = new FileReader("main/com/zhangmagle/io/tutorial/inputstr.txt");
			writer = new FileWriter("main/com/zhangmagle/io/tutorial/charaout.txt");
			
			int c ;
			
			while( (c = reader.read()) != -1) {
				writer.write(c);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
			
			if (writer != null) {
				writer.close();
			}
		}
	}
}
