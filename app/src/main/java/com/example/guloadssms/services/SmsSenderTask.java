package com.example.guloadssms.services;

import android.app.Application;
import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.guloadssms.models.SMSMessages;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SmsSenderTask {
    private Timer timer;
    private TimerTask timerTask;
    private Context context;
    private FirebaseFirestore db;

    public SmsSenderTask(Context context) {
        this.context = context;
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask,1000,1000);
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                //send sms messages
                System.out.println("SENDSMS");
                findSMSMessages();
            }
        };
    }

    public void findSMSMessages(){

        db = FirebaseFirestore.getInstance();
        db.collection("messages")
                .whereEqualTo("status", false).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot:task.getResult()){
                            SMSMessages smsMessages = new SMSMessages();//querySnapshot.toObject(RoomSMSMessages.class);
                            smsMessages.setMessage(querySnapshot.getString("message"));
                            smsMessages.setReceiver(querySnapshot.getString("receiver"));
                            smsMessages.setSender(querySnapshot.getString("sender"));
                            smsMessages.setStatus(querySnapshot.getBoolean("status"));
                            sendSMS(smsMessages);
                        }
                    }
                });
    }

    public void sendSMS(SMSMessages smsMessages) {
        System.out.println("RECEIVER: "+smsMessages.getId());
       try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(smsMessages.getReceiver(), "Ride ads", smsMessages.getMessage(), null, null);
            updateDatabase(smsMessages.getId(),smsMessages);
        } catch (Exception ex) {
            System.out.println("Something is not Good:");
            ex.printStackTrace();
        }
    }

    public void updateDatabase(String id,SMSMessages smsMessages){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("status",true);
        map.put("message",smsMessages.getMessage());
        map.put("receiver",smsMessages.getReceiver());
        map.put("sender",smsMessages.getSender());
        db.collection("messages").document(id)
                .update(map);

    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}
