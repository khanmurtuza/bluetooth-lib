package com.kinvey.android.device;

import android.util.Log;

public class Device {
	private static final String TAG = "BluetoothDeviceActivity";
	
	public static String STATE_ON = "on";
	public static String STATE_OFF = "off";
	
	private String deviceState;
	
	public void turnOn() {
		Log.i(TAG, "The Device is on");
		deviceState = STATE_ON;
	}
	
	public void turnOff() {
		Log.i(TAG, "The Device is off");
		deviceState = STATE_OFF;
	}
	
	public String getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(String deviceState) {
		this.deviceState = deviceState;
	}
	
}
