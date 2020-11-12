package com.babaev;

import java.io.*;
import java.util.*;

/*
* @author Islam Babaev
* */
public class FileMerger {
	private String resultFile = "result.txt";
	private int buffSize = 100000;
	private final File folder;
	private final List<File> files = new ArrayList<>();

	public FileMerger(String folder, String resultFile, int buffSize){
		this.folder = new File(folder);
		this.buffSize = buffSize;
		this.resultFile = resultFile;
	}

	public FileMerger(String folder, int buffSize){
		this.folder = new File(folder);
		this.buffSize = buffSize;
	}

	public FileMerger(String folder){
		this.folder = new File(folder);
	}

	public void mergeFiles(){
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
