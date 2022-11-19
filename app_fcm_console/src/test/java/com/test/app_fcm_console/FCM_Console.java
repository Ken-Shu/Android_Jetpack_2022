package com.test.app_fcm_console;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.Random;

//生成一個新密鑰(json)

public class FCM_Console {
    @Test
    public void main() throws Exception{
        String path = "src/test/java/com/test/app_fcm_console/firebase-adminsdk.json";
        FileInputStream serviceAccount =
                new FileInputStream(path);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                //.setDatabaseUrl("https://android-2022-11-12-default-rtdb.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);

        // 要推播裝置的 Token
        String regToken = "dkzyfoQhRsGhjSuIH_jrCb:APA91bGx97b4bCLcvqG6h4EePrRaidAEiiSGWo-ArA8ynESt-B8e0IzAfAdDKpy_oAX3sWppBXyWa762q7m7H6EQ0J-yp9qYCeQ0fnZjRfHsfTdyD1bFrrr-v5-8iIlwco7d0ZtWbcrh";
        Message message = Message.builder()
                .putData("title","Java 爪哇")
                .putData("body", "Android " + new Random().nextInt(1000))
                .setToken(regToken)
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully send message " + response);
    }
}
