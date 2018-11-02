package khoa.example.com.project_swd_managerfootfield;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.RegisterVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText edtName, edtEmail, edtPhone, edtPass, edtConfirmPass;

    Button btnRegisterForm;

    DataClient dataClient;
    Call<RegisterVM> registerVMCall;
    RegisterVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtPass = findViewById(R.id.edtPass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);

        btnRegisterForm = findViewById(R.id.btnRegisterForm);
        btnRegisterForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidInfor();
            }
        });

    }


    public boolean checkValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkValidEmail(String email) {
        String pattern = "\\w+@\\w+[.]\\w+";
        if (!email.matches(pattern)) {
            return false;
        }
        return true;
    }

    public boolean checkValidPhone(String phone) {
        String pattern = "\\d{10}";
        if (!phone.matches(pattern)) {
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

    public boolean checkValidConformPass(String pass, String confirmPass) {
        if (!pass.equals(confirmPass)) {
            return false;
        }
        return true;
    }

    public void checkValidInfor() {
        boolean checkName = checkValidName(edtName.getText().toString());
        boolean checkEmail = checkValidEmail(edtEmail.getText().toString());
        boolean checkPhone = checkValidPhone(edtPhone.getText().toString());
        boolean checkPass = checkValidPass(edtPass.getText().toString());
        boolean checkConfirmPass = checkValidConformPass(edtPass.getText().toString(), edtConfirmPass.getText().toString());
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(RegisterActivity.this);
        if (checkName == false) {
            dlgAlert.setMessage("Name cann't null!!!");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.create().show();
            edtName.requestFocus();
        } else {
            if (checkEmail == false) {
                dlgAlert.setMessage("Email must like abc@gmail.com!!!");
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                            }
                        });
                dlgAlert.create().show();
                edtEmail.requestFocus();
                return;
            } else {
                if (checkPhone == false) {
                    dlgAlert.setMessage("Phone must 10 numbers!!!");
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    dlgAlert.create().show();
                    edtPhone.requestFocus();
                    return;
                } else {
                    if (checkPass == false) {
                        dlgAlert.setMessage("Password must 6-15 character!!!");
                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });
                        dlgAlert.create().show();
                        edtPass.requestFocus();
                        return;
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
                            return;
                        } else {
                            RegisterVM register=new RegisterVM(edtName.getText().toString(),md5(edtPass.getText().toString()),
                                    "", "",edtEmail.getText().toString(),"",edtPhone.getText().toString());
                            createUser(register);
                            Toast.makeText(RegisterActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        }
    }

    private void createUser(RegisterVM registerVM){

        dataClient = ApiUtils.getData();
        registerVMCall = dataClient.createUser(registerVM);
        registerVMCall.enqueue(new Callback<RegisterVM>() {
            @Override
            public void onResponse(Call<RegisterVM> call, Response<RegisterVM> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Register faill!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                vm = response.body();
                if (vm == null) {
                    Toast.makeText(RegisterActivity.this, "Register faill!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<RegisterVM> call, Throwable t) {

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
