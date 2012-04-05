package com.kinvey.android.device.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.kinvey.android.activities.BaseDeviceActivity;
import com.kinvey.android.device.Command;
import com.kinvey.android.device.CommandFactory;
import com.kinvey.android.device.R;
import com.kinvey.android.device.Switch;

public class BluetoothDeviceActivity extends BaseDeviceActivity {
	private static final String TAG = "BluetoothDeviceActivity";
	
	private TextView mDeviceStatusText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_home);
        
        mDeviceStatusText = (TextView) findViewById(R.id.device_status);
    }

	@Override
	public void onNewMessage(String message) {
		mDeviceStatusText.setText("Command :" + message);
		
		Command deviceCommand = CommandFactory.getDeviceCommand(message);
		if(deviceCommand != null) {
			Switch mySwitch = new Switch();
			mySwitch.performAction(deviceCommand);
			sendMessage(String.valueOf(Boolean.TRUE));
		} else {
			sendMessage(String.valueOf(Boolean.FALSE));
		}
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.discoverable:
            // Ensure this device is discoverable by others
            ensureDiscoverable();
            return true;
        }
        return false;
    }	
	
}