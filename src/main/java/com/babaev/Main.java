package com.babaev;

public class Main {
	private static final String PATH = "/home";

	public static void main(String[] args) {
		FileMerger fileMerger = new FileMerger();
		fileMerger.mergeFiles(PATH);
	}
}
