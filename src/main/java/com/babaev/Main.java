package com.babaev;

/*
 * @author Islam Babaev
 * */
public class Main {
	private static final String PATH = "/home";

	public static void main(String[] args) {
		FileMerger fileMerger = new FileMerger();
		fileMerger.mergeFiles(PATH);
	}
}
