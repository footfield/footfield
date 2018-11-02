package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    Call<Integer> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!checkTokenIsLive()) {
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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    public boolean setToken(String username, String password, int userId) {
        LoginVM loginVM = new LoginVM(username, md5(password));
        if (loginVM != null) {
            SharedPreferences share = getSharedPreferences("com.khoa.filetoken", MODE_PRIVATE);
            SharedPreferences.Editor editor = share.edit();
            String token = md5(password);
            editor.putString("token", token);
            editor.putString("userid", String.valueOf(userId));
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean checkTokenIsLive() {
        SharedPreferences share = getSharedPreferences("com.khoa.filetoken", MODE_PRIVATE);
        String token = share.getString("token", null);
        String result = share.getString("userid", null);
        if (token == null) {
            return false;
        }
        Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
        intent.putExtra("userid", result);
        startActivity(intent);
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
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                int result = response.body();
                if (result != 0) {
                    setToken(loginVM.getUsername(), loginVM.getPassword(), result);
                    Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                    intent.putExtra("userid", result+"");
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
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void onclickForgetPass(View view) {
        Intent intent = new Intent(MainActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
