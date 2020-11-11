package com.babaev;

import java.io.*;
import java.util.*;

/*
* @author Islam Babaev
* */
public class FileMerger {
	private final File folder;
	private String resultFile = "result.txt";
	private int buffSize = 100000;
	private List<File> files = new ArrayList<>();

	public FileMerger(String folder, String resultFilePath, int buffSize){
		this.folder = new File(folder);
		this.buffSize = buffSize;
		this.resultFile = resultFilePath;
	}

	public FileMerger(String folder, int buffSize){
		this.folder = new File(folder);
		this.buffSize = buffSize;
	}

	public FileMerger(String folder){
		this.folder = new File(folder);
	}

	public void mergeFiles(){
		addFilesToList(folder);
		files.sort(Comparator.comparing(File::getName));
		process();
	}

	private void addFilesToList(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.getName().equals(resultFile))
				continue;
			if (fileEntry.isDirectory()) {
				addFilesToList(fileEntry);
			}
			else if ((fileEntry.getName().endsWith(".txt"))) {
				files.add(fileEntry);
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
