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

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btnregis;
    EditText etemail, etpas, etrepas;
    private Call<User> call;
    private Context context;
    private UserApi user_api;
    private Retrofit retrofit;
    private AwesomeValidation mAwesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");
        setSupportActionBar(toolbar);

        btnregis = (Button) findViewById(R.id.btn_regis);
        etemail = (EditText) findViewById(R.id.et_email);
        etpas = (EditText) findViewById(R.id.et_password);
        etrepas = (EditText) findViewById(R.id.et_repassword);

        btnregis.setOnClickListener(this);
        context = this;
        Gson gsonRegister = new GsonBuilder().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://private-fdf858-users180.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create(gsonRegister))
                .build();
        mAwesomeValidation = new AwesomeValidation(BASIC);
        mAwesomeValidation.addValidation(etemail, android.util.Patterns.EMAIL_ADDRESS, "Masukan Email");
        mAwesomeValidation.addValidation(etpas, "^.*(?=.{8,}).*$", "Masukan Password");
        mAwesomeValidation.addValidation(etrepas, "^.*(?=.{8,}).*$", "Pastikan password sama");
    }

    @Override
    public void onClick(View v) {
        if(mAwesomeValidation.validate() == true) {
            final AlertDialog dialog = new SpotsDialog(context);
            dialog.show();
            user_api = retrofit.create(UserApi.class);
            User user = new User(etemail.getText().toString(),etpas.getText().toString());
            call = user_api.saveUser(user);
            call.enqueue(new Callback<User>() {

                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(etpas.getText().toString().equals(etrepas.getText().toString())) {
                        dialog.dismiss();
                        //save data user for using session later
                        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("email", etemail.getText().toString());
                        editor.apply();
                        Toast.makeText(context, "User Created", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Register.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                Register.this).create();
                        // Setting Dialog Title
                        alertDialog.setTitle("Alert Dialog");
                        // Setting Dialog Message
                        alertDialog.setMessage("Missmatch password");
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

                }
            });
        }
    }




}
