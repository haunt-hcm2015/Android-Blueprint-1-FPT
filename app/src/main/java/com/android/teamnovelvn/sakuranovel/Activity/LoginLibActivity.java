package com.android.teamnovelvn.sakuranovel.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.teamnovelvn.sakuranovel.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import mehdi.sakout.fancybuttons.FancyButton;
import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginLibActivity extends AppCompatActivity {
    private SignInButton btnSignInGoogle;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "MAIN_ACTIVITY";
    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener loginGoogleChangeListener, loginEmailAndPasswordListener;

    TextView tvForgotPassword;
    String email, password, confirmPassword;
    FancyButton btn;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_lib);

//        btn = (FancyButton)findViewById(R.id.btn_Signin_Google);
        tvForgotPassword = (TextView) findViewById(R.id.textview_forgot_password);
        rl = (RelativeLayout)findViewById(R.id.activity_login_lib);



        btnSignInGoogle = (SignInButton)findViewById(R.id.button_google);
        mAuth = FirebaseAuth.getInstance();

        SignIn_Google();
        loginEmailAndPassword();

        final MaterialLoginView login = (MaterialLoginView) findViewById(R.id.login);
        ((DefaultLoginView)login.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                //Handle login
                email = loginUser.getEditText().getText().toString().trim();
                if (email.isEmpty()) {
                    loginUser.setError("User name can't be empty");
                    return;
                }
                loginUser.setError("");

                password = loginPass.getEditText().getText().toString().trim();
                 if (password.isEmpty()) {
                   loginPass.setError("Password can't be empty");
                  return;
                }
                loginPass.setError("");

                signIn(email, password);
                //Snackbar.make(login, "Login success!", Snackbar.LENGTH_LONG).show();
            }
        });

        ((DefaultRegisterView)login.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                //Handle register
                email = registerUser.getEditText().getText().toString().trim();
                if (email.isEmpty()) {
                    registerUser.setError("User name can't be empty");
                    return;
                }
                registerUser.setError("");

                password = registerPass.getEditText().getText().toString().trim();
                if (password.isEmpty()) {
                    registerPass.setError("Password can't be empty");
                    return;
                }
                registerPass.setError("");

                confirmPassword = registerPassRep.getEditText().getText().toString().trim();
                if (!password.equals(confirmPassword)) {
                    registerPassRep.setError("Passwords are different");
                    return;
                }
                registerPassRep.setError("");
                signUp(email, password);
                //Snackbar.make(login, "Register success!", Snackbar.LENGTH_LONG).show();
            }
        });


        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginLibActivity.this);
                dialog.setTitle("Reset password by email");
                dialog.setContentView(R.layout.dialog_forgot_password);
                final EditText edtEmail = (EditText) dialog.findViewById(R.id.edittext_email_user);
                Button btnReset = (Button) dialog.findViewById(R.id.button_reset_password);
                dialog.show();

                btnReset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = edtEmail.getText().toString().trim();

                        if (TextUtils.isEmpty(email)) {
//                            Toast.makeText(LoginLibActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            Snackbar.make(rl, "Enter your registered email id", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Toast.makeText(LoginLibActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                    Snackbar.make(rl, "We have sent you instructions to reset your password!", Snackbar.LENGTH_LONG).show();
                                } else {
//                                    Toast.makeText(LoginLibActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                    Snackbar.make(rl, "Failed to send reset email!", Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });

                        dialog.dismiss();
                    }
                });

            }
        });
    }
    // Sign in

    // register by email & password
    private void loginEmailAndPassword() {
        loginEmailAndPasswordListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser getInfoUser = firebaseAuth.getCurrentUser();
                if (getInfoUser != null) {
                    // Success
                } else {
                    // if failed
                }
            }
        };
    }
    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Toast.makeText(LoginLibActivity.this, "Register success!", Toast.LENGTH_SHORT).show();
                            Snackbar.make(rl, "Register success!", Snackbar.LENGTH_LONG).show();
                        }
                        else {
//                            Toast.makeText(LoginLibActivity.this
//                                    , "This email was exist or transmission error and the password more 6 character" +
//                                            ".\nPlease try again."
//                                    , Toast.LENGTH_SHORT)
//                                    .show();

                            Snackbar.make(rl, "This email was exist or transmission error and the password more 6 character" + ".\nPlease try again.", Snackbar.LENGTH_LONG).show();
                        }

                    }
                });
    }

    private void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(    new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    // if login success
                    //Toast.makeText(LoginLibActivity.this, "Login success", Toast.LENGTH_LONG).show();
                    Snackbar.make(rl, "Login success!", Snackbar.LENGTH_LONG).show();
                    FirebaseUser user = task.getResult().getUser();
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    String uId   = user.getUid();
                    //Toast.makeText(LoginLibActivity.this,name,Toast.LENGTH_LONG).show();
                    //Uri urlImage = user.getPhotoUrl();
                    // Code (if you have)
                }
                else
                {
                    // if login failed
                    //Toast.makeText(LoginLibActivity.this, "Login failed. You can wrong password or email.\nPlease try again.", Toast.LENGTH_LONG).show();
                    Snackbar.make(rl, "Login failed. You can wrong password or email.\nPlease try again.", Snackbar.LENGTH_LONG).show();
                    // Code (if you have)
                }
            }
        });
    }

    // Đăng nhập bằng google
    public void SignIn_Google(){

        loginGoogleChangeListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //        if (firebaseAuth.getCurrentUser() != null)
                //         Toast.makeText(LoginLibActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(LoginLibActivity.this)
                .enableAutoManage(LoginLibActivity.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        //Toast.makeText(LoginLibActivity.this, "Authentication Some failure",Toast.LENGTH_LONG).show();
                        Snackbar.make(rl, "Authentication Some failure", Snackbar.LENGTH_LONG).show();
                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        btnSignInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(loginGoogleChangeListener);
        mAuth.addAuthStateListener(loginEmailAndPasswordListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Kết thúc activity sẽ chạy vào onStop()
        if (mAuth != null) {
            mAuth.removeAuthStateListener(loginGoogleChangeListener);
            mAuth.removeAuthStateListener(loginEmailAndPasswordListener);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                //Toast.makeText(LoginLibActivity.this,"Login failed", Toast.LENGTH_SHORT).show();
                Snackbar.make(rl, "Login failed", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(LoginLibActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful())
                            Snackbar.make(rl, "Login success!", Snackbar.LENGTH_LONG).show();

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
//                            Toast.makeText(LoginLibActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                            Snackbar.make(rl, "Authentication failed.", Snackbar.LENGTH_LONG).show();
                        }
                        // ...
                    }
                });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        mGoogleApiClient.connect();
    }
}
