package net.tribanda.bcn4demo;

import java.util.ArrayList;
import java.util.List;

import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.MenuInflater;

import net.tribanda.bcn4demo.video.VideoUtil;

import android.os.Bundle;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CommentListActivity extends SherlockListActivity {
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
		
		if(orderByTop) {
			//s = 
		}
		
		adapter = new ArrayAdapter<String>(this,
				R.layout.row_layout, R.id.label,
				s);
		setListAdapter(adapter);             
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

		if (requestCode == Constants.ACTION_TAKE_VIDEO) {

			if(resultCode == RESULT_OK){
					
			}
		}
	}
}