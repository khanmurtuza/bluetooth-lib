package com.kinvey.android.activities;

public abstract class BaseRemoteActivity extends BaseConnectActivity {

	@Override
	public final void onNewMessage(String message) {
		boolean success = false;
		if(message != null) {
			success = Boolean.valueOf(message);
		}
		onDeviceResponse(success);
	}
	
	public abstract void onDeviceResponse(boolean success);

}
