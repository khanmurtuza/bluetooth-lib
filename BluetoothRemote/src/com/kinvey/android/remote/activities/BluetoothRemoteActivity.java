package com.kinvey.android.remote.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.kinvey.android.activities.BaseRemoteActivity;
import com.kinvey.android.remote.R;

public class BluetoothRemoteActivity extends BaseRemoteActivity {
	private static final String TAG = "BluetoothRemoteActivity";
	
	// Layout Views
	private ListView mConversationView;
	private EditText mOutEditText;
	private Button mSendButton;
	
	// Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote_home);
		initUI();
	}
	
	private void initUI() {
        // Initialize the array adapter for the conversation thread
        mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mConversationArrayAdapter);

        // Initialize the compose field with a listener for the return key
        mOutEditText = (EditText) findViewById(R.id.edit_text_out);
        mOutEditText.setOnEditorActionListener(mWriteListener);

        // Initialize the send button with a listener that for click events
        mSendButton = (Button) findViewById(R.id.button_send);
        mSendButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Send a message using content of the edit text widget
                TextView view = (TextView) findViewById(R.id.edit_text_out);
                String message = view.getText().toString();
                sendMessage(message);
                // clear the edit text field
                mOutEditText.setText(mOutStringBuffer);
            }
        });
	}

	@Override
	public void onDeviceResponse(boolean success) {
		String message = "Response - " + success;
		mConversationArrayAdapter.add(mConnectedDeviceName+":  " + message);
	}
	
	@Override
	public void onWriteSuccess(String message) {
		mConversationArrayAdapter.add("Me:  " + message);
	}
	
	// The action listener for the EditText widget, to listen for the return key
	private TextView.OnEditorActionListener mWriteListener = new TextView.OnEditorActionListener() {
		public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
			// If the action is a key-up event on the return key, send the message
			if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_UP) {
				String message = view.getText().toString();
				sendMessage(message);
			}
			return true;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.connect_scan:
			requestDeviceConnection();
			return true;
		}
		return false;
	}

}
