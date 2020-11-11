package com.babaev;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) {
		File file = new File("/Users/kroyce/");
		FileMerger fileMerger = new FileMerger(file);

		String result = fileMerger.mergeFiles();

		try {
			PrintWriter pw = new PrintWriter("result.txt");
			pw.print(result);
			pw.close();
		} catch (FileNotFoundException e) {

		}
	}
}

