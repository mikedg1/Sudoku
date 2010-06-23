package com.mikedg.android.sudoku;

import java.util.Vector;

import android.app.ListActivity;
import android.os.Bundle;

import com.mikedg.android.sudoku.service.ServiceStatusReceiver;
import com.mikedg.android.sudoku.service.StatusThread;

public class SudokuBaseListActivity extends ListActivity {
	public static final String PREF_TIMES_VIEWED = "ptv";
	private ServiceStatusReceiver mServiceStatusReceiver;
	private Vector<StatusThread> mStatusThreads = new Vector<StatusThread>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mServiceStatusReceiver = ActivityHelper.handleOnCreate(this, mStatusThreads);
	}
		 
	@Override
	protected void onPause() {
		super.onPause();
		ActivityHelper.handleOnPause(this, mServiceStatusReceiver);
	}

	@Override
	protected void onResume() {
		super.onResume();

		ActivityHelper.handleOnResume(this, mServiceStatusReceiver);
	}
	
	//Add additional status receivers to be managed by our classes
	protected void manageServiceStatusListener(StatusThread runnable) {
		mStatusThreads.add(runnable);
	}
}
