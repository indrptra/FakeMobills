package com.indrap.FakeMobills;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.indrap.navigation.MainActivity;
import com.indrap.navigation.R;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText etemail, etpass;
    Button btnlogin;
    private Context context;
    private UserApi user_api;
    private Call<User> call;
    TextView tvregis;
    AwesomeValidation mAwesomeValidation;
    Retrofit retrofit;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        etemail = (EditText) findViewById(R.id.et_email);
        etpass = (EditText) findViewById(R.id.et_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        tvregis = (TextView) findViewById(R.id.textView2) ;
        btnlogin.setOnClickListener(this);
        tvregis.setOnClickListener(this);

        context = this;
        Gson gson = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://private-fdf858-users180.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(etemail, android.util.Patterns.EMAIL_ADDRESS, "Masukan Email");
        mAwesomeValidation.addValidation(etpass, "^.*(?=.{8,}).*$", "Masukan Password");
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    if (mAwesomeValidation.validate() == true) {
                        final AlertDialog dialog = new SpotsDialog(context);
                        dialog.show();
                        // // implement interface for get all user
                        user_api = retrofit.create(UserApi.class);
                        call = user_api.getUser();
                        call.enqueue(new Callback<User>() {

                            @Override
                            public void onResponse(Call<User> call, Response<User> response) {
                                int status = response.code();
                                String email = String.valueOf(response.body().getEmail());
                                String password = String.valueOf(response.body().getPassword());
                                if (etemail.getText().toString().equals(email) && etpass.getText().toString().equals(password)) {
                                    SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putString("email", etemail.getText().toString());
                                    editor.apply();

                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    startActivity(i);
                                    dialog.dismiss();
                                    finish();
                                } else {
                                    AlertDialog alertDialog = new AlertDialog.Builder(
                                            Login.this).create();
                                    // Setting Dialog Title
                                    alertDialog.setTitle("Alert Dialog");
                                    // Setting Dialog Message
                                    alertDialog.setMessage("Incorrect Email or Password");
                                    // Setting OK Button
                                    alertDialog.setButton("Try", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Write your code here to execute after dialog closed
                                            Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    // Showing Alert Message
                                    alertDialog.show();
                                    dialog.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {
                                //tv_respond.setText(String.valueOf(t));
                                AlertDialog alertDialog = new AlertDialog.Builder(
                                        Login.this).create();

                                // Setting Dialog Message
                                alertDialog.setMessage("Error your Call");
                                // Setting OK Button
                            }
                        });
                    }
                    break;
                case R.id.textView2:
                    Intent i = new Intent(Login.this, Register.class);
                    startActivity(i);
                    break;
            }

    }
}
