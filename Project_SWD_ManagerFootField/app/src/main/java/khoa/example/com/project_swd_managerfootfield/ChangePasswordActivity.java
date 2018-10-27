package khoa.example.com.project_swd_managerfootfield;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText edtOldPass, edtNewPass, edtConfirmPass;
    Button btnUpdatePass;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        edtOldPass = findViewById(R.id.edtChangeOldPass);
        edtNewPass = findViewById(R.id.edtChangeNewPass);
        edtConfirmPass = findViewById(R.id.edtChangeConfirmPass);

        Intent intent = getIntent();
        pass = intent.getStringExtra("pass");
        edtOldPass.setText(pass);

        btnUpdatePass = findViewById(R.id.btnUpdatePass);

        btnUpdatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidUpdatePass();
            }
        });
    }

    public boolean checkOldPass(String oldPass) {
        if (!edtOldPass.getText().toString().equals(oldPass)) {
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
                    //update pass
                    Toast.makeText(getApplicationContext(), "Change ok", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
