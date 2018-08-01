package com.example.asome.asome_sourcerequire.Login;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;


import android.view.View;



import com.example.asome.asome_sourcerequire.Main.MainActivity;

import com.example.asome.asome_sourcerequire.R;



public class LogInActivity extends AppCompatActivity{
/*
implements GoogleApiClient.OnConnectionFailedListener{

    private static int SIGN_IN = 150;
private static final String TAG = "LogInActivity";

    private FirebaseAuth mAuth;
    GoogleApiClient mGoogleApiClient;
    FirebaseAuth.AuthStateListener mAuthListener;

    SignInButton signInButton;
    String userName;
    @Override
    public void onStart() {

        super.onStart();

        // 활동을 초기화할 때 사용자가 현재 로그인되어 있는지 확인합니다.

        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

/*
        signInButton = findViewById(R.id.btn_google_signin);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
*/
       // setGoogleLogin();

//        mAuth = FirebaseAuth.getInstance();
    }
  /*  private void setGoogleLogin() {
        FirebaseAuth.getInstance().signOut();

        // GoogleSignInOptions 생성

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder

                (GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestIdToken(getString(R.string.default_web_client_id))

                .requestEmail()

                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)

                .enableAutoManage(this, this )

                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)

                .build();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                startActivity(intent);
            }
        };
    }
  */
  public void onLoginClicked(View view) {
        Intent intent = new Intent(LogInActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void MovetoJoinClicked(View view) {
        Intent intent = new Intent(LogInActivity.this, JoinActivity.class);
        startActivity(intent);
    }
  /*  public void onLoginWithGoogleClicked(View view) {

        Toast.makeText(this, "구글로 이동합니다.", Toast.LENGTH_LONG).show();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN);

    }
*/
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
/*

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {

                // Google Sign In was successful, authenticate with Firebase

                Log.v("알림", "google sign 성공, FireBase Auth.");

                GoogleSignInAccount account = result.getSignInAccount();

                firebaseAuthWithGoogle(account);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);

            } else {

                // Google Sign In failed, update UI appropriately

                Log.v("알림", result.isSuccess() +" Google Sign In failed. Because : " + result.getStatus().toString());

                // ...

            }

        }

    }
    protected void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("알림", "onConnectionFailed");

    }
*/
    }
    }