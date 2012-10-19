package fr.hyperfiction;

import	::APP_PACKAGE::.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Build.VERSION_CODES;
import android.os.Build;
import android.util.Log;
import android.app.Dialog;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import org.haxe.nme.GameActivity;
import android.view.ViewGroup.LayoutParams;


/**
 * ...
 * @author shoe[box]
 */

class HypSystem{

	private static String TAG = "HypSystem";

	private static LoadingDialog dialog_progress;

	// -------o constructor
		
		/**
		* constructor
		*
		* @param	
		* @return	void
		*/
		private void HypSystem() {
			Log.i( TAG, " constructor" );
		}
	
	// -------o public
				
		/**
		* 
		* 
		* @public
		* @return	void
		*/
		static public boolean isConnectedtoInternet() {

			trace("activity ::: "+GameActivity.getInstance( ));
			ConnectivityManager conMgr = (ConnectivityManager) GameActivity.getContext( ).getSystemService(Context.CONNECTIVITY_SERVICE);
		    Boolean res = false;
		    if (conMgr.getActiveNetworkInfo() != null
		            && conMgr.getActiveNetworkInfo().isAvailable()
		            && conMgr.getActiveNetworkInfo().isConnected()) {
		        trace("Internet Connection  Present");
		        res=true;
		    } else {
		        trace("Internet Connection Not Present");
		        res= false;
		    }
		    trace("isConnectedtoInternet ::: "+res);
		    return res;

		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		static public void show_loading( ){
			GameActivity.getInstance( ).runOnUiThread(
				new Runnable(){
	                @Override
	                public void run() {
	                	if( dialog_progress == null )
	                    	dialog_progress = new LoadingDialog( GameActivity.getContext( ) );
							dialog_progress.show( );
	                }                   
		        }
            );			
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		static public void hide_loading( ){
			trace("hide_loading");
			GameActivity.getInstance( ).runOnUiThread(
				new Runnable(){
	                @Override
	                public void run() {
	                	if( dialog_progress != null )
	                    	dialog_progress.hide( );
	                }                   
		        }
            );			
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		static public void dismiss_loading( ){
			trace("dismiss_loading");
			GameActivity.getInstance( ).runOnUiThread(
				new Runnable(){
	                @Override
	                public void run() {
	                	if( dialog_progress != null )
	                    	dialog_progress.dismiss( );
	                    	dialog_progress = null;
	                }                   
		        }
            );			
		}


	// -------o protected
	
		

	// -------o misc
		
		public static void trace( String s ){
			Log.i( TAG, s );
		}

	static class LoadingDialog extends Dialog{

		ProgressBar pb;

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		public LoadingDialog( final Context context ){
			super( context , R.style.CustomDialogTheme );

			addContentView( pb = new ProgressBar( GameActivity.getContext( ) ) ,  new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

			//getWindow().setBackgroundDrawableResource(R.color.bg_tran);
		}
	}
}