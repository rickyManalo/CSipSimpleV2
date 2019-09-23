package com.csipsimple.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import com.csipsimple.R;

import java.util.HashMap;
import java.util.Map;

public class RuntimePermissionsActivity extends Activity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    private static int SPLASH_TIME_OUT = 11000;
    private String TAG = "tag";
    public SharedPreferences app_preferences;
    public SharedPreferences.Editor editor;
    Boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permissions);

        app_preferences = PreferenceManager.getDefaultSharedPreferences(RuntimePermissionsActivity.this);
        editor = app_preferences.edit();
        isFirstTime = app_preferences.getBoolean("isFirstTime", true);

        if (isFirstTime && needToAskPermission()) {
            askForPermissions();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    editor.putBoolean("isFirstTime", false);
                    editor.commit();
                    Intent i = new Intent(RuntimePermissionsActivity.this, SipHome.class);
                    startActivity(i);
                    finish();

                }
            }, SPLASH_TIME_OUT);

        } else {
            Log.d(TAG, "runTimePerm skip askPerm");
            Intent i = new Intent(RuntimePermissionsActivity.this, SipHome.class);
            startActivity(i);
            finish();
        }
    }

    void askForPermissions() {
//TODO: check why mute and other inCallControls doesn't work
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.USE_SIP) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.USE_SIP,
                        Manifest.permission.WRITE_CALL_LOG,
                        Manifest.permission.READ_CALL_LOG
                    },
                        REQUEST_ID_MULTIPLE_PERMISSIONS
                );
            }
        }
    }

    boolean needToAskPermission() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        Log.d(TAG, "Permission callback called-------");
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS: {

                Map<String, Integer> perms = new HashMap<>();
                // Initialize the map with both permissions
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.RECORD_AUDIO, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.USE_SIP, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_CALL_LOG, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_CALL_LOG, PackageManager.PERMISSION_GRANTED);
                // Fill with actual results from user
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i++)
                        perms.put(permissions[i], grantResults[i]);
                    // Check for both permissions
                    if (perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.USE_SIP) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.WRITE_CALL_LOG) == PackageManager.PERMISSION_GRANTED
                            && perms.get(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
                        Log.d(TAG, "sms & location services permission granted");

                    } else {
                        Log.d(TAG, "Some permissions are not granted ask again ");
                        //permission is denied (this is the first time, when "never ask again" is not checked) so ask again explaining the usage of permission

                    }
                }
            }
        }

    }
}