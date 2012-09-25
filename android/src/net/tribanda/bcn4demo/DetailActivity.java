package net.tribanda.bcn4demo;

import model.question;
import net.tribanda.bcn4demo.video.VideoUtil;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

public class DetailActivity extends Activity {

	private static final String TAG = "DetailActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Bundle extras = this.getIntent().getExtras();
    	
        super.onCreate(savedInstanceState);
        
        if(extras.containsKey("rowId")) {
        	
        }
        setContentView(R.layout.activity_detail); 
        String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
        VideoView myVideoView = (VideoView)findViewById(R.id.videoView1);
        myVideoView.setVideoURI(Uri.parse(SrcPath));
        myVideoView.setMediaController(new MediaController(this));
        myVideoView.requestFocus();
        myVideoView.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detail, menu);
        return true;
    }

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		if (featureId == Constants.ACTION_VOTE) {
			AsyncTask<Void, Void, Void> t = new AsyncTask<Void, Void, Void>() {
				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
				}

				@Override
				protected Void doInBackground(Void... params) {
					try
					{
						// UploadUtil.uploadVideo(is);
						// question.getLastQuestions();
					}catch(Exception e)
					{
						Log.e(TAG, "Error uploading video... " + e.toString());
					}
					return null;
				}

				@Override
				protected void onPostExecute(Void result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);
				}
			};
			t.execute();
		}
		
		return super.onMenuItemSelected(featureId, item);
	}
	
}
