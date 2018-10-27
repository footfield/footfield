package khoa.example.com.project_swd_managerfootfield;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import khoa.example.com.project_swd_managerfootfield.VM.RegisterVM;

public class InformationUserActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    ImageView imgView;
    Button btnChooseImg, btnSetting, btnUpdate, btnChangePass;
    Uri imgUri;
    EditText edtFirstNameUpdate, edtLastNameUpdate, edtPhoneUpdate, edtAddressUpdate, edtPassUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);

        RegisterVM test = new RegisterVM("khoa", "123", "khoa", "bach",
                "bkhoa0401@gmail.com", "olala", "0905040197");

        imgView = findViewById(R.id.imgAvatar);
        btnChooseImg = findViewById(R.id.btnChooseFile);

        edtFirstNameUpdate = findViewById(R.id.edtFirstNameUpdate);
        edtFirstNameUpdate.setText(test.getFirstname());
        edtFirstNameUpdate.setEnabled(false);

        edtLastNameUpdate = findViewById(R.id.edtLastNameUpdate);
        edtLastNameUpdate.setText(test.getLastname());
        edtLastNameUpdate.setEnabled(false);

        edtPhoneUpdate = findViewById(R.id.edtPhoneUpdate);
        edtPhoneUpdate.setText(test.getPhone());
        edtPhoneUpdate.setEnabled(false);

        edtAddressUpdate = findViewById(R.id.edtAddressUpdate);
        edtAddressUpdate.setText(test.getAddress());
        edtAddressUpdate.setEnabled(false);


//        edtPassUpdate = findViewById(R.id.edtPassUpdate);
//        edtPassUpdate.setText(test.getPassword());
//        edtPassUpdate.setEnabled(false);

        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnUpdate = findViewById(R.id.btnUpdate);
        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtFirstNameUpdate.setEnabled(true);
                edtLastNameUpdate.setEnabled(true);
                edtPhoneUpdate.setEnabled(true);
                edtAddressUpdate.setEnabled(true);
                //  edtPassUpdate.setEnabled(true);
                btnUpdate.setVisibility(View.VISIBLE);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidInfor();
            }
        });

        btnChangePass = findViewById(R.id.btnChangePass);
        final String pass = test.getPassword();
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                intent.putExtra("pass", pass);
                startActivity(intent);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    public boolean checkValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkValidLocation(String location) {
        String pattern = "\\w{6,15}";
        if (!location.matches(pattern)) {
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


    public void checkValidInfor() {
        boolean checkFirstName = checkValidName(edtFirstNameUpdate.getText().toString());
        boolean checkLastName = checkValidName(edtLastNameUpdate.getText().toString());
        boolean checkPhone = checkValidPhone(edtPhoneUpdate.getText().toString());
        boolean checkLocation = checkValidLocation(edtAddressUpdate.getText().toString());
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(InformationUserActivity.this);
        if (checkFirstName == false || checkLastName == false) {
            dlgAlert.setMessage("First name  or Last name cann't null!!!");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.create().show();
            edtFirstNameUpdate.requestFocus();
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
                edtPhoneUpdate.requestFocus();
                return;
            } else {
                if (checkLocation == false) {
                    dlgAlert.setMessage("Location invalid!!!");
                    dlgAlert.setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    dlgAlert.create().show();
                    edtAddressUpdate.requestFocus();
                    return;
                } else {
                    // can 1 field de luu hinh khi update
                    RegisterVM register = new RegisterVM(null, edtPassUpdate.getText().toString(),
                            edtFirstNameUpdate.getText().toString(), edtLastNameUpdate.getText().toString(), null, edtAddressUpdate.getText().toString(), edtPhoneUpdate.getText().toString());
                    Toast.makeText(InformationUserActivity.this, "OK", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void openGallery() {
        Intent open = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(open, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imgUri = data.getData();
            imgView.setImageURI(imgUri);
        }
    }
}
