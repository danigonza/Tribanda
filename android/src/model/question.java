package model;

import java.util.List;

import android.util.Log;

import net.tribanda.bcn4demo.Constants;
import net.tribanda.bcn4demo.net.HttpUtils;

public class question {
	private static final String TAG = "Question";
	String videoUrl;
	int votes;
	String userName;
	
	List<answer> answers;
	
	public static List<question> getLastQuestions()
	{
		try
		{
			String response = HttpUtils.Get(Constants.LAST_QUESTIONS);
			return null;
		}catch(Exception e){
			Log.e(TAG, "Error retrieving or download data..:");
			return null;
		}
	}
	
	public static List<question> getTopQuestions()
	{
		try
		{
			String response = HttpUtils.Get(Constants.TOP_QUESTIONS);
			return null;
		}catch(Exception e){
			Log.e(TAG, "Error retrieving or download data..:");
			return null;
		}
	}
}
