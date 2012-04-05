package com.kinvey.android.device;

public class FlipUpCommand implements Command {

	private Device mDevice;
	
	public FlipUpCommand(Device device) {
		mDevice = device;
	}
	
	public void execute() {
		mDevice.turnOn();
	}

	public Device getDevice() {
		return mDevice;
	}

}
