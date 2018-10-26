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
    Button btnChooseImg, btnSetting, btnUpdate;
    Uri imgUri;
    EditText edtNameUpdate, edtPhoneUpdate, edtAddressUpdate, edtPassUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);

        RegisterVM test = new RegisterVM("khoa", "123", null, null,
                "bkhoa0401@gmail.com", "olala", "0905040197");

        imgView = findViewById(R.id.imgAvatar);
        btnChooseImg = findViewById(R.id.btnChooseFile);

        edtNameUpdate = findViewById(R.id.edtNameUpdate);
        edtNameUpdate.setText(test.getUsername());
        edtNameUpdate.setEnabled(false);

        edtPhoneUpdate = findViewById(R.id.edtPhoneUpdate);
        edtPhoneUpdate.setText(test.getPhone());
        edtPhoneUpdate.setEnabled(false);

        edtAddressUpdate = findViewById(R.id.edtAddressUpdate);
        edtAddressUpdate.setText(test.getAddress());
        edtAddressUpdate.setEnabled(false);


        edtPassUpdate = findViewById(R.id.edtPassUpdate);
        edtPassUpdate.setText(test.getPassword());
        edtPassUpdate.setEnabled(false);

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
                edtNameUpdate.setEnabled(true);
                edtPhoneUpdate.setEnabled(true);
                edtAddressUpdate.setEnabled(true);
                edtPassUpdate.setEnabled(true);
                btnUpdate.setVisibility(View.VISIBLE);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidInfor();
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

    public boolean checkValidPass(String pass) {
        String pattern = "\\w{6,15}";
        if (!pass.matches(pattern)) {
            return false;
        }
        return true;
    }

    public void checkValidInfor() {
        boolean checkName = checkValidName(edtNameUpdate.getText().toString());
        boolean checkPhone = checkValidPhone(edtPhoneUpdate.getText().toString());
        boolean checkPass = checkValidPass(edtPassUpdate.getText().toString());
        boolean checkLocation = checkValidLocation(edtAddressUpdate.getText().toString());
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(InformationUserActivity.this);
        if (checkName == false) {
            dlgAlert.setMessage("Name cann't null!!!");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.create().show();
            edtNameUpdate.requestFocus();
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
                    if (checkPass == false) {
                        dlgAlert.setMessage("Password must 6-15 character!!!");
                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //dismiss the dialog
                                    }
                                });
                        dlgAlert.create().show();
                        edtPassUpdate.requestFocus();
                        return;
                    } else {
                        // can 1 field de luu hinh khi update
                        RegisterVM register = new RegisterVM(edtNameUpdate.getText().toString(), edtPassUpdate.getText().toString(),
                                null, null, null, edtAddressUpdate.getText().toString(), edtPhoneUpdate.getText().toString());
                        Toast.makeText(InformationUserActivity.this, "OK", Toast.LENGTH_SHORT).show();
                    }
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
