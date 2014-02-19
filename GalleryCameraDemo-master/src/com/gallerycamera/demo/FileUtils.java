package com.gallerycamera.demo;

import java.io.File;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class FileUtils {
	public static final String IMAGE_FILE = "sample_file.jpg";
	private static String APPLICATION_TAG = "GALLERY_CAMERA_DEMO";
	private static String path;
	
	private static void initPath(Context context) {
		
		// Find the dir to save cached images
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GalleryCameraDemo/images";
		} else {
			path = context.getCacheDir().getAbsolutePath();
		}
	}
	
	public static String getPath(String folderName) {
		return path + folderName + "/";
	}
	
	public static void createDefaultFolder(Context context) {
		initPath(context);
		File file = new File(path);
		boolean success = file.mkdirs();
		Log.i(APPLICATION_TAG, success + "");
	}
	
	public static void createFolder(String folder) {
		// initPath(folder);
		File file = new File(path + "/" + folder);
		boolean success = file.mkdirs();
		Log.i(APPLICATION_TAG, success + "");
	}
	
	public static File createFile(String filename) {
		File file = new File(path + "/" + filename);
		return file;
	}
	
	public static File createFile(String folderName, String filename) {
		File file = new File(path + folderName, filename);
		
		return file;
	}
	
	public static String[] getAllFiles(String folderName) {
		File file = new File(path + "/" + folderName);
		return file.list();
	}
	
	
}
