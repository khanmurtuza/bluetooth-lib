package com.kinvey.android.device;


public class CommandFactory {

	/**
	 * Create a new DeviceCommand instance.
	 * @return Command
	 */
	public static Command getDeviceCommand(String commandString) {
		if (commandString != null) {
			if(commandString.equals(Device.STATE_ON)) {
				return new FlipUpCommand(new Device());
			} else if(commandString.equals(Device.STATE_OFF)) {
				return new FlipDownCommand(new Device());
			}
		}
		return null;
	}

}
