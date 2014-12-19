/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package android_serialport_api.sample;

import java.io.IOException;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class ConsoleActivity extends SerialPortActivity {

	EditText mReception;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.console);

		Log.v("LZP", "OnCreate");
//		setTitle("Loopback test");
		mReception = (EditText) findViewById(R.id.EditTextReception);

		Button button = (Button)findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					try {
			//			 int[] arr = new int[]{0xAA,0x55,0x00,0x2e,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x12,0x4B,0x00,0x02,0x8A,0x95,0x82,0x00,0x00,0x00,0x00,0x00,0x00,0xc8,0x60,0x00,0x02,0x6f,0xa2,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0xff,0x00,0x00,0xF2,0x5D};
						 int[] arr = new int[]{0xAA,0x55,0x00,0x2e,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x12,0x4B,0x00,0x02,0x8A,0x95,0x82
									,0x00,0x00,0x00,0x00,0x00,0x00,0xc8,0x60,0x00,0x02,0x6f,0xa2,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0xff,0x00,0x00,0xF2,0x5D};   
						 byte[] byteArr = new byte[arr.length];
				            for (int j = 0; j < arr.length; j++) {
				            	byteArr[j] = (byte)arr[j];
							}
				            String aa = " ";
				            for (int j = 0; j < byteArr.length; j++) {
								aa +=byteArr[j]+" ";
							}
				            Log.v("LZP", "String:"+aa);
						mOutputStream.write(byteArr);
//							mOutputStream.write('\n');
					} catch (IOException e) {
					//	e.printStackTrace();
						Log.v("LZP", e.toString());
					}
			}
		});
		EditText Emission = (EditText) findViewById(R.id.EditTextEmission);
	/*	Emission.setOnEditorActionListener(new OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				int i;
				CharSequence t = v.getText();
				char[] text = new char[t.length()];
				for (i=0; i<t.length(); i++) {
					text[i] = t.charAt(i);
				}
				try {
					 int[] arr = new int[]{0xAA,0x55,0x00,0x2e,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x12,0x4B,0x00,0x02,0x8A,0x95,0x82,0x00,0x00,0x00,0x00,0x00,0x00,0xc8,0x60,0x00,0x02,0x6f,0xa2,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x01,0xff,0x00,0x00,0xF2,0x5D};
			            byte[] byteArr = new byte[arr.length];
			            for (int j = 0; i < arr.length; j++) {
			            	byteArr[j] = (byte)arr[j];
						}
			            String aa = " ";
			            for (int j = 0; j < byteArr.length; j++) {
							aa +=byteArr[j]+" ";
						}
			            Log.v("LZP", "String:"+aa);
					mOutputStream.write(byteArr);
	//				mOutputStream.write('\n');
				} catch (IOException e) {
				//	e.printStackTrace();
					Log.v("LZP", e.toString());
				}
				return false;
			}
		});*/

	}

	private int j=0;
	@Override
	protected void onDataReceived(final byte[] buffer, final int size) {
		runOnUiThread(new Runnable() {
			public void run() {
				if (mReception != null) {
					String str="";
					for (int i = 0; i < buffer.length; i++) {
						str += Integer.toHexString(buffer[i] & 0xff)+" ";
					}
					j++;
					Log.v("LZP", "Data Seceive:"+ j +" "+str);
				
					mReception.append(str.toUpperCase());
				}
			}
		});
	}
}
