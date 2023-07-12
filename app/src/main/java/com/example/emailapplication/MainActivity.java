package com.example.emailapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.to_email);
        editTextSubject = findViewById(R.id.subject);
        editTextMessage = findViewById(R.id.content);
        Button sendButton = findViewById(R.id.sendButton);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject, content, to_email;
                to_email = editTextEmail.getText().toString();
                subject = editTextSubject.getText().toString();
                content = editTextMessage.getText().toString();
                if (subject.equals("") && content.equals("") && to_email.equals("")){
                    Toast.makeText(MainActivity.this, "Все поля обязательные", Toast.LENGTH_SHORT).show();
                }
                else {
                    sendEmail(subject, content, to_email);
                }
            }
        });

        editTextEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextEmail.setBackgroundResource(R.drawable.edittext_background_focused);
                    editTextEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_email, 0, 0, 0);
                } else {
                    editTextEmail.setBackgroundResource(R.drawable.edittext_background_normal);
                    editTextEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_email_gray, 0, 0, 0);
                }
            }
        });

        editTextSubject.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextSubject.setBackgroundResource(R.drawable.edittext_background_focused);
                    editTextSubject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_subject, 0, 0, 0);
                } else {
                    editTextSubject.setBackgroundResource(R.drawable.edittext_background_normal);
                    editTextSubject.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_subject_gray, 0, 0, 0);
                }
            }
        });

        editTextMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    editTextMessage.setBackgroundResource(R.drawable.edittext_background_focused);
                    editTextMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_message, 0, 0, 0);
                } else {
                    editTextMessage.setBackgroundResource(R.drawable.edittext_background_normal);
                    editTextMessage.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_message_gray, 0, 0, 0);
                }
            }
        });
    }
    public void sendEmail(String subject, String content, String to_email){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose email client"));
    }
}