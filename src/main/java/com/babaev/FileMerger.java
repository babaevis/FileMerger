package com.babaev;

import java.io.*;
import java.util.*;

public class FileMerger {
	private String resultFile = "result.txt";
	private int buffSize = 100000;
	private final List<File> files = new ArrayList<>();

	public FileMerger(String resultFile, int buffSize){
		this.resultFile = resultFile;
		this.buffSize = buffSize;
	}

	public FileMerger(int buffSize){
		this.buffSize = buffSize;
	}

	public FileMerger() {
	}

	public void mergeFiles(String path){
		File folder = new File(path);
		if (!folder.exists()){
			throw new IllegalArgumentException("Invalid path");
		}

		addFilesToList(folder);
		files.sort(Comparator.comparing(File::getName));
		process();
	}

	private void addFilesToList(final File folder) {
		File[] filesInDir = folder.listFiles();
		if (filesInDir != null){
			for (final File file : filesInDir) {
				if (file.getName().equals(resultFile))
					continue;
				if (file.isDirectory()) {
					addFilesToList(file.getAbsoluteFile());
				}
				if ((file.getName().endsWith(".txt"))) {
					files.add(file);
				}
			}
		}
	}

	private void process(){
		try {
			FileOutputStream output = new FileOutputStream(resultFile);
			for(File file: files){
				try(FileInputStream input = new FileInputStream(file))
				{
					byte[] buffer = new byte[buffSize];
					while (input.available() > 0)
					{
						int bytes = input.read(buffer);
						output.write(buffer, 0, bytes);
					}
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
