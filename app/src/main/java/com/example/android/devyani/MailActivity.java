package com.example.android.devyani;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.games.request.Requests;
import com.parse.ParseUser;

import java.util.Properties;
import java.util.concurrent.RunnableFuture;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailActivity extends AppCompatActivity {


    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    TextView feedbackText;

    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);


        sendButton = (Button) findViewById(R.id.sendButton);
        feedbackText =(TextView) findViewById(R.id.feedbackText);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Integer,Integer>() {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        try {
                            generateAndSendEmailToAcceptor();
                            Toast.makeText(MailActivity.this,"Feedback sent! Thankyou",Toast.LENGTH_SHORT).show();
                        } catch (MessagingException e1) {
                            e1.printStackTrace();
                        }
                        return 0;
                    }


                }.execute();

                Intent i = new Intent(MailActivity.this,MainActivity.class);
                startActivity(i);
            }

        });
    }




    public void generateAndSendEmailToAcceptor() throws MessagingException {

// Get mailserver properties
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "25");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        // get session for email
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("sanket.selokar@gmail.com"));
        generateMailMessage.setSubject("Feedback Parking App");
        // HTML format of the email
        String emailBody = "Here is feedback <br>"
               +feedbackText.getText()
                +". <br> Thankyou, <br> user";
        // Define type
        generateMailMessage.setContent(emailBody, "text/html");
        //Start Transport
        Transport transport = getMailSession.getTransport("smtp");
        transport.connect("smtp.gmail.com", "sanket.selokar@gmail.com", "Sanrad$1992");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        //Close connection
        transport.close();
    }
}
