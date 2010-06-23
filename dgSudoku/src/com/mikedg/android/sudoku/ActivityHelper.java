package com.mikedg.android.sudoku;

import java.util.Vector;

import android.app.Activity;
import android.content.IntentFilter;
import android.view.Window;

import com.mikedg.android.sudoku.service.ServiceStatusReceiver;
import com.mikedg.android.sudoku.service.StatusThread;
import com.mikedg.android.sudoku.service.SudokuService;

//This class contains all of the shared methods that Hungactivity and HungListActivity should have.
//This prevents the code from desynchronizing as much as possible.
public class ActivityHelper {
	protected static IntentFilter sServiceStatusFiler = new IntentFilter(ServiceStatusReceiver.BROADCAST_STATUS);
	
	protected static ServiceStatusReceiver handleOnCreate(final Activity activity, Vector<StatusThread> statusThreads) {
		activity.getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		activity.setProgressBarIndeterminate(true);
		
		//Default status listener, turns the statuses off.
		//If you want more functionality here, then add another listener.
		//The indeterminate status is the constant for all network activity
		return new ServiceStatusReceiver(activity, new StatusThread(statusThreads) {
			
			@Override
			public void run() {
				//super.run();
				//Cant pass parameters in here.... or any status right?
				//So we call setstauses which checks via ServiceHelper
				ActivityHelper.setStatuses(activity);
				
				for(StatusThread statusThread : mStatusThreads) {
					if (mStatus == statusThread.getTriggerStatus()) {
						activity.runOnUiThread(statusThread); //TODO: is this restartable?
					}
				}
			}
		});
	}

	protected static void handleOnPause(Activity activity, ServiceStatusReceiver serviceStatusReceiver) {
		if (serviceStatusReceiver!=null) {
			activity.unregisterReceiver(serviceStatusReceiver);
		}
	}
	
	protected static void setStatuses(Activity activity) {
		if (SudokuService.isDailyDownloading()) {
			activity.setProgressBarIndeterminateVisibility(true);
		} else {
			activity.setProgressBarIndeterminateVisibility(false);
		}
	}
	protected static void handleOnResume(Activity activity, ServiceStatusReceiver serviceStatusReceiver) {
		setStatuses(activity);
		if (serviceStatusReceiver!=null) {
			activity.registerReceiver(serviceStatusReceiver, sServiceStatusFiler);
		}
	}
	
}
