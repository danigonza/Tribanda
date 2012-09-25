package net.tribanda.bcn4demo;

public class Constants {

	public static final int ACTION_TAKE_VIDEO = 100;
	public static final int ACTION_TAKE_PICTURE = 101;
	public static final int ACTION_VOTE = 102;
	
	public static final String SERVER_NAME = "http://192.168.1.67";
	public static final String UPLOAD_URL = SERVER_NAME + "/handler";
	public static final String LAST_QUESTIONS = SERVER_NAME + "/questions/last";
	public static final String TOP_QUESTIONS = SERVER_NAME + "/questions/top";
	public static final String QESTION_BY_ID= SERVER_NAME + "/questions/";
	public static final String QESTION_POST = SERVER_NAME + "/questions";
}