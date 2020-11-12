package com.babaev;

/*
 * @author Islam Babaev
 * */
public class Main {
	private static final String FOLDER = "/home";

	public static void main(String[] args) {
		FileMerger fileMerger = new FileMerger(FOLDER);
		fileMerger.mergeFiles();
	}
}
