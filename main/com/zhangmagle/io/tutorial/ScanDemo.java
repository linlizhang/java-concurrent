package com.zhangmagle.io.tutorial;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ScanDemo {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner s = null;
		
		try {
			s = new Scanner(new BufferedReader(new FileReader("main/com/zhangmagle/io/tutorial/inputstr.txt")));
			
			while (s.hasNext()) {
				System.out.println(s.next());
			}
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}

}
