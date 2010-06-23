package com.mikedg.android.sudoku.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class ServiceStatusReceiver extends BroadcastReceiver {
	public static final String BROADCAST_STATUS = "com.mikedg.android.sudoku.ServiceStatusBroadcast";
	
	private StatusThread mRunnable;
	private Activity mActivity;
	
	public ServiceStatusReceiver(Activity activity, StatusThread runnable) {
		mRunnable = runnable;
		mActivity = activity;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		//For now it's always download is done
//		Bundle extras = intent.getExtras();
//		mRunnable.mStatus = extras.getInt(HungService.EXTRA_METHOD);
		mActivity.runOnUiThread(mRunnable);
//		new Thread(mRunnable).start(); //Let it work it's magic!
	}
}
