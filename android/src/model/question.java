package model;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import net.tribanda.bcn4demo.Constants;
import net.tribanda.bcn4demo.net.HttpUtils;

public class question {
	private static final String TAG = "Question";
	public String videoUrl;
	public int votes;
	public String userName;
	public String title;
	
	List<answer> answers;
	public int id;
	
	public static List<question> getLastQuestions()
	{
		try
		{
			String response = HttpUtils.Get(Constants.LAST_QUESTIONS);
			return parseListQuestions(response);
		}catch(Exception e){
			Log.e(TAG, "Error retrieving or download data..:");
			return null;
		}
	}
	
	public String toJson() throws Exception
	{
		JSONObject jobj = new JSONObject();
		jobj.put("videoId", this.videoUrl);
		jobj.put("userId", 1);
		jobj.put("title", "New question needs moderation");
		return jobj.toString(4);
	}
	
	public void createOnServer()
	{
		try{
			HttpUtils.Post(Constants.QESTION_POST, this.toJson());
		}catch(Exception e)
		{
			Log.d(TAG, "Error creating question on server...");
		}
	}


	public static List<question> getTopQuestions()
	{
		try
		{
			String response = HttpUtils.Get(Constants.TOP_QUESTIONS);
			return parseListQuestions(response);
		}catch(Exception e){
			Log.e(TAG, "Error retrieving or download data..:");
			return null;
		}
	}
	
	public static question getQuestionById(long id)
	{
		try
		{
			String response = HttpUtils.Get(Constants.QESTION_BY_ID + id);
			JSONObject jobj = new JSONObject(response);
			return parseQuestion(jobj);
		}catch(Exception e){
			Log.e(TAG, "Error retrieving or download data..:");
			return null;
		}
	}
		
	private static List<question> parseListQuestions(String response) throws Exception {
		LinkedList<question> questions = new LinkedList<question>();
		JSONArray js = new JSONArray(response);
		for(int i=0; i<js.length(); i++)
		{
			JSONObject jobj = js.getJSONObject(i);
			questions.add( parseQuestion(jobj));
		}
		return questions;
	}

	private static question parseQuestion(JSONObject jobj) throws Exception {
		int id = jobj.getInt("id");
		String title = jobj.getString("title");
		int nVots = jobj.getInt("numVotes");
		String videoId = jobj.getString("videoId");
		question q = new question();
		q.userName = "UserName fixed";
		q.votes = nVots;
		q.videoUrl = videoId;
		q.title = title;
		q.id = id;
		return q;
		
				
	}

	
}
