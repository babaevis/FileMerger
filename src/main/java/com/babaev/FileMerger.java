package com.babaev;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileMerger {
	private List<File> files;
	private File folder;

	public FileMerger(File folder){
		this.folder = folder;
	}

	public String mergeFiles(){
		files = new ArrayList<>();
		addFilesToList(folder);
		sortFilesByName();

		return mergeFileContents();
	}

	private void addFilesToList(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				addFilesToList(fileEntry);
			} else if((fileEntry.getName().endsWith(".txt")))
					files.add(fileEntry);
			}
		}

	private void sortFilesByName(){
		files.sort(Comparator.comparing(File::getName));
	}

	private String getFileContent(File file){
		String data = "";
		try {
			data = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

	private String mergeFileContents(){
		StringBuilder result = new StringBuilder();
		for(File f: files){
			result.append(getFileContent(f));
		}
		return result.toString();
	}
}
