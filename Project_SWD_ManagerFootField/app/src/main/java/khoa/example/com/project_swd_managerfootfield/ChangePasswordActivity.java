package khoa.example.com.project_swd_managerfootfield;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.PasswordChangeVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText edtOldPass, edtNewPass, edtConfirmPass;
    Button btnUpdatePass;
    String pass;

    DataClient dataClient;
    Call<Boolean> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtOldPass = findViewById(R.id.edtChangeOldPass);
        edtNewPass = findViewById(R.id.edtChangeNewPass);
        edtConfirmPass = findViewById(R.id.edtChangeConfirmPass);

        Intent intent = getIntent();
        pass = intent.getStringExtra("pass");

        btnUpdatePass = findViewById(R.id.btnUpdatePass);

        btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidUpdatePass();
            }
        });
    }

    public boolean checkOldPass(String oldPass) {
        if (!md5(edtOldPass.getText().toString()).equals(oldPass)) {
            return false;
        }
        return true;
    }

    public boolean checkCofirmPass(String newPass, String confirmPass) {
        if (!newPass.equals(confirmPass)) {
            return false;
        }
        return true;
    }

    public boolean checkValidPass(String pass) {
        String pattern = "\\w{6,15}";
        if (!pass.matches(pattern)) {
            return false;
        }
        return true;
    }

    public void checkValidUpdatePass() {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(ChangePasswordActivity.this);
        boolean checkOldPass = checkOldPass(pass);
        boolean checkValidNewPass = checkValidPass(edtNewPass.getText().toString());
        boolean checkConfirmPass = checkCofirmPass(edtNewPass.getText().toString(), edtConfirmPass.getText().toString());
        if (checkOldPass == false) {
            dlgAlert.setMessage("Old Pass not correct!!!");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.create().show();
            edtOldPass.requestFocus();
        } else {
            if (checkValidNewPass == false) {
                dlgAlert.setMessage("New Pass must 6-15 character!!!");
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.create().show();
                edtNewPass.requestFocus();
            } else {
                if (checkConfirmPass == false) {
                    dlgAlert.setMessage("Password not matched!!!");
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    dlgAlert.create().show();
                    edtConfirmPass.requestFocus();
                } else {
                    //String userId = this.getIntent().getStringExtra("userid");
                    SharedPreferences sharedPreferences = getSharedPreferences("com.khoa.filetoken",MODE_PRIVATE);
                   String userId=sharedPreferences.getString("userid","");
                    updatePassword(new PasswordChangeVM(Integer.parseInt(userId), md5(edtNewPass.getText().toString())));
                    Intent intent = new Intent(ChangePasswordActivity.this, InformationUserActivity.class);
                    intent.putExtra("userid", userId+"");
                    startActivity(intent);
                }
            }
        }
    }

    private void updatePassword(PasswordChangeVM vm) {
        dataClient = ApiUtils.getData();
        call = dataClient.updatePassword(vm);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (!response.isSuccessful()) return;
                if (response.body()) {
                    Toast.makeText(getApplicationContext(), "Change success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Change fail!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
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

}
