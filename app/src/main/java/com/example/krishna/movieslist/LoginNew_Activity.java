package com.example.krishna.movieslist;


import android.accessibilityservice.AccessibilityService;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.Arrays;


public class LoginNew_Activity extends AppCompatActivity {

    GoogleSignInClient mGoogleSignInClient;
    final int RC_SIGN_IN = 12345;
    CallbackManager callbackManager;
    Bundle parameters;
    Profile profile;

    SharedPreferences mSettings;
    SharedPreferences.Editor editor;

    ImageView bg;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = mSettings.edit();

        /*if(mSettings.getBoolean("signed_in",false)) {
            Intent i = new Intent(getBaseContext(), LoginNew_Activity.class);
            startActivity(i);
            finish();
        }*/
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            LoginManager.getInstance().logOut();
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        super.onCreate(savedInstanceState);


        try {
            profile = new Profile();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        setContentView(R.layout.activity_loginnew);
        bg = findViewById(R.id.background);
        final ImageView logo = findViewById(R.id.logo);
        Picasso.get().load(R.drawable.backimage).resize(400, 800).centerCrop().into(bg);
        Picasso.get().load(R.drawable.movielogo).resize(400, 400).into(logo);
        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doTransitions();
            }
        });
        findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MovieList_Activity.class);
                startActivity(i);
                finish();
            }
        });
        bg.setOnTouchListener(new OnTouchListener() {
                                  @Override
                                  public boolean onTouch(View v, MotionEvent event) {
                                      hideSystemUI();
                                      return false;
                                  }
                              }
        );
        final EditText email = findViewById(R.id.email);
        email.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideSystemUI();
                return false;
            }
        });
        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                hideSystemUI();
                return false;
            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideSystemUI();
            }
        });
        EditText password = findViewById(R.id.password);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideSystemUI();
            }
        });


        //Google sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        googleAdapt(account);
        TextView google = findViewById(R.id.google_icon_button);
        ImageView g = findViewById(R.id.google_icon);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        //fb
        //Facebook login
        final LoginButton loginButton = findViewById(R.id.facebook_sign_in);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code

                                //String email = object.getString("email");


                                    String name = me.optString("name");
                                    String user_gender = me.optString("gender");
                                    String email =response.getJSONObject().optString("email");
                                    String user_birthdy =response.getJSONObject().optString("birthday");
                                    String user_id =response.getJSONObject().optString("id");

                                updatemail(name,email);
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,gender,email,birthday,id");
                request.setParameters(parameters);
                request.executeAsync();



                com.facebook.Profile fbprofile = com.facebook.Profile.getCurrentProfile();
                //profile.setName(fbprofile.getName());
                //profile.setEmail(fbprofile.getEmail());

                // profile.setEmail(parameters.getString("email"));


                try {
                    profile.setPicture(fbprofile.getProfilePictureUri(500, 500));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LoginManager.getInstance().logOut();

                updateUI();

            }

            @Override
            public void onCancel() {
                // App cod
                Toast.makeText(getBaseContext(), "Fb cancelled", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            LoginManager.getInstance().logOut();
        }


        //Sign_in
        Button sign_in = findViewById(R.id.sign_in_button);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile.setEmail(email.getText().toString());
                updateUI();
            }
        });


    }

    private void updatemail(String name, String email) {
        Intent main=new Intent(LoginNew_Activity.this,ProfileActivity.class);
        main.putExtra("name", name);
        main.putExtra("mail", email);
        startActivity(main);

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            googleAdapt(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("SIGNIN", "signInResult:failed code=" + e.getStatusCode());
            googleAdapt(null);
        }

    }

    private void fbAdapt(AccessToken a) {
        com.facebook.Profile fbprofile = com.facebook.Profile.getCurrentProfile();
        //profile.setName(fbprofile.getName());
        //profile.setEmail(fbprofile.getEmail());

       // profile.setEmail(parameters.getString("email"));


        try {
            profile.setPicture(fbprofile.getProfilePictureUri(500, 500));
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoginManager.getInstance().logOut();

        updateUI();
    }


    private void googleAdapt(GoogleSignInAccount account) {
        if (account == null) {
            Toast.makeText(this, "Choose sign in, properly", Toast.LENGTH_SHORT).show();
        } else {
            try {
                profile.setPicture(account.getPhotoUrl());
               /* profile.setName(account.getDisplayName());
                profile.setEmail(account.getEmail());
*/


                String name = account.getDisplayName();

                String email =account.getEmail();
                Log.i("nameggd",email);
                updatemail(name,email);


            } catch (Exception e) {
                e.printStackTrace();
            }
            mGoogleSignInClient.signOut();
            updateUI();
        }
    }

    private void updateUI() {

        Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ProfileActivity.class);
        try {
            editor.putString("picture", profile.getPicture().toString());
        } catch (Exception e) {
            ;
        }
        /*editor.putString("name", profile.getName());
        editor.putString("email", profile.getEmail());*/
        editor.putBoolean("signed_in", true);
        editor.apply();
        finish();
        hideSystemUI();

    }

    private void doTransitions() {

        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final TextView already = findViewById(R.id.already_account);
        final Button sign_in = findViewById(R.id.sign_in_button);
        final TextView google = findViewById(R.id.google_icon_button);
        final TextView facebook = findViewById(R.id.facebook_icon_button);
        final TextView fly = findViewById(R.id.login_fly);
        final ImageView g = findViewById(R.id.google_icon);
        final ImageView f = findViewById(R.id.facebook_icon);
        final LoginButton fb = findViewById(R.id.facebook_sign_in);
        google.setVisibility(View.INVISIBLE);
        fb.setVisibility(View.INVISIBLE);


        email.animate().translationXBy(-bg.getWidth()).alpha(0.0f).setDuration(0);
        password.animate().translationXBy(bg.getWidth()).alpha(0.0f).setDuration(0);
        already.animate().translationXBy(-bg.getWidth()).setDuration(0);
        sign_in.animate().translationXBy(bg.getWidth()).setDuration(0);
        google.animate().translationXBy(-bg.getWidth() / 2).setDuration(0);
        g.animate().translationXBy(-bg.getWidth() / 2).setDuration(0);
        facebook.animate().translationXBy(bg.getWidth() / 2).setDuration(0);
        f.animate().translationXBy(bg.getWidth() / 2).setDuration(0);
        fly.animate().translationYBy(bg.getHeight()).setDuration(0);


        final TextView title = findViewById(R.id.title);
        title.animate().setDuration(500)
                .translationXBy(bg.getWidth()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                title.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        final TextView desc = findViewById(R.id.desc);
        desc.animate().setDuration(500)
                .translationXBy(-1 * bg.getWidth()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                desc.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        final View linlay = findViewById(R.id.linlay);
        linlay.animate().setDuration(500)
                .translationYBy(linlay.getHeight()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                linlay.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        final View logo = findViewById(R.id.logo);
        logo.animate().scaleX(0.7f).scaleY(0.7f).translationYBy(-1 * bg.getHeight() / 10).setDuration(500);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                email.setVisibility(View.VISIBLE);
                password.setVisibility(View.VISIBLE);
                already.setVisibility(View.VISIBLE);
                sign_in.setVisibility(View.VISIBLE);
                google.setVisibility(View.VISIBLE);
                g.setVisibility(View.VISIBLE);
                facebook.setVisibility(View.VISIBLE);
                f.setVisibility(View.VISIBLE);
                fly.setVisibility(View.VISIBLE);
                fb.setVisibility(View.VISIBLE);
                fb.animate().alpha(0.0f).setDuration(0);
                google.animate().translationXBy(bg.getWidth() / 2).setDuration(700);
                email.animate().setDuration(700).translationXBy(bg.getWidth()).alpha(1.0f);
                password.animate().translationXBy(-bg.getWidth()).alpha(1.0f).setDuration(700);
                already.animate().translationXBy(bg.getWidth()).setDuration(700);
                sign_in.animate().translationXBy(-bg.getWidth()).setDuration(700);
                g.animate().translationXBy(bg.getWidth() / 2).setDuration(700);
                facebook.animate().translationXBy(-bg.getWidth() / 2).setDuration(700);
                f.animate().translationXBy(-bg.getWidth() / 2).setDuration(700);

                fly.animate().translationYBy(-bg.getHeight()).setDuration(700);

            }
        }, 10);


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        } else
            showSystemUI();
    }

    public void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}
