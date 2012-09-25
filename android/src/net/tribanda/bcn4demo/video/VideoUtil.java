package net.tribanda.bcn4demo.video;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import net.tribanda.bcn4demo.Constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class VideoUtil {

	private static final String TAG = "VideoUtil";

	public static boolean callVideoApp(Activity act)
	{
		if(! isIntentAvailable(act, MediaStore.ACTION_VIDEO_CAPTURE))
			return false;
		
		Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60*3);
		act.startActivityForResult(takeVideoIntent, Constants.ACTION_TAKE_VIDEO);
		
		return true;
	}
	
	public static boolean isIntentAvailable(Context context, String action) {
	    final PackageManager packageManager = context.getPackageManager();
	    final Intent intent = new Intent(action);
	    List<ResolveInfo> list =
	        packageManager.queryIntentActivities(intent,
	            PackageManager.MATCH_DEFAULT_ONLY);
	    return list.size() > 0;
	}
	
	public static Uri videoIntentParce(Intent intent, int responsecode)
	{
		Uri uri= null;
		if( responsecode == Constants.ACTION_TAKE_VIDEO)
		{
			uri= intent.getData();
			Log.d(TAG, "video recorded at: " + uri.toString());
		}else if (responsecode == Constants.ACTION_TAKE_PICTURE){
			
		}
		
		return uri;
	}
	
	public static InputStream getVideoStream(Context ctx, Uri uri)
	{
		try {
			return ctx.getContentResolver().openInputStream(uri);
		} catch (FileNotFoundException e) {
			Log.d(TAG, "File not found exception:" + e.toString());
			return null;
		}
	}
	
}
