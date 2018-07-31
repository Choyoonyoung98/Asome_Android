package com.example.asome.asome_sourcerequire.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asome.asome_sourcerequire.Main.MainActivity;
import com.example.asome.asome_sourcerequire.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void onLoginClicked(View view) {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void MovetoJoinClicked(View view) {
        Intent intent = new Intent(LogInActivity.this, JoinActivity.class);
        startActivity(intent);
    }
}
