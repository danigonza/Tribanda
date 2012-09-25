package net.tribanda.bcn4demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuInflater;

import model.question;
import net.tribanda.bcn4demo.net.UploadUtil;
import net.tribanda.bcn4demo.video.VideoUtil;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.Video;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CommentListActivity extends SherlockListActivity {

	private static final String TAG = "MainActivity";

	List<String> items= new ArrayList<String>();
	String[] ids = new String[0];	
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {			
		String[] s={"lorem", "ipsum", "dolor", "sit", "amet",
				"consectetuer", "adipiscing", "elit", "morbi", "vel",
				"ligula", "vitae", "arcu", "aliquet", "mollis",
				"etiam", "vel", "erat", "placerat", "ante",
				"porttitor", "sodales", "pellentesque", "augue",
		"purus"};			

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_list);

		Boolean orderByTop = true;

		Bundle extras = this.getIntent().getExtras();
		if ( extras != null && extras.containsKey("orderByTop") ) {
			orderByTop = extras.getBoolean("orderByTop");
		}



		AsyncTask<Void, Void, List<question>> t = new AsyncTask<Void, Void, List<question>>() {
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected List<question> doInBackground(Void... params) {
				try
				{
					List<question> q = new ArrayList<question>();
					q = question.getTopQuestions();
					return q;
				}catch(Exception e)
				{
					Log.e(TAG, "Error uploading video... " + e.toString());
				}
				return null;
			}

			@Override
			protected void onPostExecute(List<question> q) {
				LinkedList<String> p = new LinkedList<String>();
				for(question qq : q)
				{
					p.add(qq.title);
				}

				adapter = new ArrayAdapter<String>(CommentListActivity.this,
						R.layout.row_layout, R.id.label,
						p);
				setListAdapter(adapter);             

			}
		};
		t.execute();
		//s = 

	}

	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		MenuInflater inflater = this.getSupportMenuInflater();
		inflater.inflate(R.menu.activity_list, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		VideoUtil.callVideoApp(this);
		return super.onMenuItemSelected(featureId, item);
	}

	public void onListItemClick(ListView parent, View v,
			int position, long id) {    	
		Toast.makeText(this, "You want to view "+position, Toast.LENGTH_SHORT).show();

		Intent i = new Intent(CommentListActivity.this, DetailActivity.class);

		i.putExtra("rowId", position); // TODO id aqui
		i.putExtra("listItemId", position);

		startActivityForResult(i, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Returning form edition?  Must refresh!
		Uri uri = VideoUtil.videoIntentParce(data, requestCode);
		final InputStream is = VideoUtil.getVideoStream(this, uri);
		
		if (requestCode == Constants.ACTION_TAKE_VIDEO) {
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
						String path = UploadUtil.uploadVideo(is);
						question q = new question();
						q.videoUrl = path;
						q.createOnServer();
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
	}
}
