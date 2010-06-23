package com.mikedg.android.sudoku.service;

import java.util.Vector;

public abstract class StatusThread implements Runnable {
	public static final int TRIGGER_FINISHED_DAILY = 0;
	
	protected int mStatus;
	protected Vector<StatusThread> mStatusThreads; //Additional threads to execute...
	protected int mTriggerStatus = 0;
	
	//For now, only the daily trigger
	public StatusThread(Vector<StatusThread> statusThreads) {
		mStatusThreads = statusThreads;
		mTriggerStatus = TRIGGER_FINISHED_DAILY;
	}

	public StatusThread(int triggerStatus) {
		mTriggerStatus = triggerStatus;
	}
	
	public int getTriggerStatus() {
		return mTriggerStatus;
	}
}
