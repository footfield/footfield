package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.LoginVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnLogin, btnRegister;
    TextView txtNotification;

    DataClient dataClient;
    Call<Boolean> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        boolean checkTolenIsLive = checkTokenIsLive();
        if (checkTolenIsLive == true) {
            Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
            txtUsername = findViewById(R.id.txtUsername);
            txtPassword = findViewById(R.id.txtPassword);
            txtNotification = findViewById(R.id.textNotification);

            btnLogin = findViewById(R.id.btnLogin);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String username = txtUsername.getText().toString();
                    String password = txtPassword.getText().toString();
                    LoginVM loginVM = new LoginVM(username, md5(password));
                    checkUserAndPass(loginVM);
                }
            });

            btnRegister = findViewById(R.id.btnRegister);
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }


    }

    public boolean setToken(String username, String password) {
        LoginVM loginVM = new LoginVM(username, md5(password));
        if (loginVM != null) {
            SharedPreferences share = getSharedPreferences("com.khoa.filetoken", MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            String token = md5(password);
            editor.putString("token", token);
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean checkTokenIsLive() {
        SharedPreferences share = getSharedPreferences("com.khoa.filetoken", MODE_PRIVATE);
        String token = share.getString("token", null);
        if (token == null) {
            return false;
        }
        return true;
    }

    private static String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void checkUserAndPass(final LoginVM loginVM) {
        dataClient = ApiUtils.getData();
        call = dataClient.checkLogin(loginVM);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Boolean result = response.body();
                if (result) {
                    setToken(loginVM.getUsername(), loginVM.getPassword());
                    Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    txtNotification.setText("Wrong username or password!");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtUsername.requestFocus();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void onclickForgetPass(View view) {
        Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
