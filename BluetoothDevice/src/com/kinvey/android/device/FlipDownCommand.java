package com.kinvey.android.device;

public class FlipDownCommand implements Command {

	private Device mDevice;
	
	public FlipDownCommand(Device device) {
		mDevice = device;
	}
	
	public void execute() {
		mDevice.turnOff();
	}
	
	public Device getDevice() {
		return mDevice;
	}

}
