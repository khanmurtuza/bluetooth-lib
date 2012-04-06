package com.kinvey.android.activities;

/**
 * Base activity that can be used by device acting as client
 */
public abstract class BaseRemoteActivity extends BaseConnectActivity {

	@Override
	public final void onNewMessage(String message) {
		// client is only interested in success or failure of command
		boolean success = false;
		if(message != null) {
			success = Boolean.valueOf(message);
		}
		onDeviceResponse(success);
	}
	
	/**
	 * Callback that will be invoked after command is executed on remote device
	 * @param success TRUE is command is supported by server FALSE otherwise
	 */
	public abstract void onDeviceResponse(boolean success);

}
