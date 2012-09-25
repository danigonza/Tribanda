package net.tribanda.bcn4demo;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CommentListActivity extends ListActivity {
	List<String> items= new ArrayList<String>();
	String[] ids = new String[0];	
	private ArrayAdapter<String> adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_list);

		adapter = new ArrayAdapter<String>(this,
				R.layout.row_layout, R.id.label,
				items);
		setListAdapter(adapter);             
	}

	public void onListItemClick(ListView parent, View v,
			int position, long id) {    	
		Toast.makeText(this, "You want to view "+items.get(position), Toast.LENGTH_SHORT).show();

		Intent i = new Intent(CommentListActivity.this, DetailActivity.class);
		i.putExtra("rowId", Long.valueOf(ids[position]));
		i.putExtra("listItemId", position);

		startActivityForResult(i, 1);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Returning form edition?  Must refresh!

		if (requestCode == 1) {

			if(resultCode == RESULT_OK){

				String name = data.getStringExtra("resultName");
				int position = data.getIntExtra("listItemId",-1);

				if (position != -1) {
					adapter.remove(adapter.getItem(position));
					adapter.insert(name, position);
					adapter.notifyDataSetChanged();
				}
			}
		}
	}
}