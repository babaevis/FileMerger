package com.babaev;

public class Main {
	private static final String FOLDER = "/home";

	public static void main(String[] args) {
		FileMerger fileMerger = new FileMerger(FOLDER);
		fileMerger.mergeFiles();
	}
}
