package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.SendEmail.EmailSender;
import khoa.example.com.project_swd_managerfootfield.VM.PasswordForgotVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {

    EditText edtEmail, edtUser;
    Button btnSend;
    private final String senderUsername = "footfield123@gmail.com";
    private final String senderPassword = "!23456789qwe";
    DataClient dataClient;
    Call<PasswordForgotVM> call;
    PasswordForgotVM newPass;
    TextView txtNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        edtEmail = findViewById(R.id.edtEmailForget);
        edtUser = findViewById(R.id.edtNameForget);
        txtNotification = findViewById(R.id.textNotification);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String username = edtUser.getText().toString();
                PasswordForgotVM forget = new PasswordForgotVM(username + "", email + "", "");
                getNewPassword(forget);

            }
        });
    }

    private void getNewPassword(PasswordForgotVM vm) {
        dataClient = ApiUtils.getData();
        call = dataClient.getNewPassword(vm);
        System.out.println("+++++++++++++newpass1: " + newPass);
        call.enqueue(new Callback<PasswordForgotVM>() {
            @Override
            public void onResponse(Call<PasswordForgotVM> call, Response<PasswordForgotVM> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                newPass = response.body();
                if (newPass.getPassword().equals("")) {
                    txtNotification.setText("Wrong username or email!");
                    edtEmail.setText("");
                    edtUser.setText("");
                    return;
                }
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            System.out.println("+++++++++++++Sending Email");
                            EmailSender sender = new EmailSender(
                                    senderUsername + "",
                                    senderPassword + "");

                            sender.sendMail("Password Forgot", "Your new password: " + newPass.getPassword(),
                                    senderUsername + "",
                                    newPass.getEmail() + "");
                            System.out.println("++++++++++++Sending ss");
                        } catch (Exception e) {
                            System.out.println("++++++++++++Sending err" + e);
                        }
                    }
                }).start();
                Intent intent = new Intent(ForgetPasswordActivity.this, MainActivity.class);
                Toast.makeText(ForgetPasswordActivity.this, "Check email to get new password!!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<PasswordForgotVM> call, Throwable t) {

            }
        });
    }
}

