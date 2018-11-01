package khoa.example.com.project_swd_managerfootfield;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.AppUserVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationUserActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    ImageView imgView;
    Button btnChooseImg, btnSetting, btnUpdate, btnChangePass;
    Uri imgUri;
    EditText edtUsername, edtLastNameUpdate, edtPhoneUpdate, edtAddressUpdate;

    AppUserVM userInfoVM;
    DataClient dataClient;
    Call<AppUserVM> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_user);
        loadUserInfo();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }


    private void loadView() {

        imgView = findViewById(R.id.imgAvatar);
        if (userInfoVM.getImage() != null && !userInfoVM.getImage().equals("")) {
            imgView.setImageURI(Uri.parse(userInfoVM.getImage()));
        }

        btnChooseImg = findViewById(R.id.btnChooseFile);

        edtUsername = findViewById(R.id.edtUsername);
        edtUsername.setText(userInfoVM.getUsername());
        edtUsername.setEnabled(false);

        edtLastNameUpdate = findViewById(R.id.edtLastNameUpdate);
        edtLastNameUpdate.setText(userInfoVM.getLastname());
        edtLastNameUpdate.setEnabled(false);

        edtPhoneUpdate = findViewById(R.id.edtPhoneUpdate);
        edtPhoneUpdate.setText(userInfoVM.getPhone());
        edtPhoneUpdate.setEnabled(false);

        edtAddressUpdate = findViewById(R.id.edtAddressUpdate);
        edtAddressUpdate.setText(userInfoVM.getAddress());
        edtAddressUpdate.setEnabled(false);

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
                edtLastNameUpdate.setEnabled(true);
                edtPhoneUpdate.setEnabled(true);
                edtAddressUpdate.setEnabled(true);
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
        final String pass = userInfoVM.getPassword();
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                intent.putExtra("pass", pass);
                String userId = InformationUserActivity.this.getIntent().getStringExtra("userid");
                intent.putExtra("userid", userId + "");
                startActivity(intent);
            }
        });
    }

    private void loadUserInfo() {
        Intent it = this.getIntent();
        String userId = it.getStringExtra("userid");

        dataClient = ApiUtils.getData();
        call = dataClient.getUserInfo(Integer.parseInt(userId));
        call.enqueue(new Callback<AppUserVM>() {
            @Override
            public void onResponse(Call<AppUserVM> call, Response<AppUserVM> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                userInfoVM = response.body();
                loadView();
            }

            @Override
            public void onFailure(Call<AppUserVM> call, Throwable t) {

            }
        });
    }

    public boolean checkValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkValidLocation(String location) {
        String pattern = "\\w{6,19}";
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
        boolean checkLastName = checkValidName(edtLastNameUpdate.getText().toString());
        boolean checkPhone = checkValidPhone(edtPhoneUpdate.getText().toString());
        boolean checkLocation = checkValidLocation(edtAddressUpdate.getText().toString());
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(InformationUserActivity.this);
        if (checkLastName == false) {
            dlgAlert.setMessage("Last name cann't null!!!");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });
            dlgAlert.create().show();
            edtLastNameUpdate.requestFocus();
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
                    userInfoVM.setLastname(edtLastNameUpdate.getText().toString());
                    userInfoVM.setPhone(edtPhoneUpdate.getText().toString());
                    userInfoVM.setAddress(edtAddressUpdate.getText().toString());
                    if (imgUri != null) {
                        userInfoVM.setImage(imgUri.toString());
                    }
                    updateUser();
                }
            }
        }
    }

    private void updateUser() {
        dataClient = ApiUtils.getData();
        call = dataClient.updateUser(userInfoVM);
        call.enqueue(new Callback<AppUserVM>() {
            @Override
            public void onResponse(Call<AppUserVM> call, Response<AppUserVM> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Toast.makeText(InformationUserActivity.this, "Update success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AppUserVM> call, Throwable t) {

            }
        });
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
            Toast.makeText(getApplicationContext(), imgUri.toString(), Toast.LENGTH_SHORT).show();
//            imgView.setImageURI(imgUri);
            String tmp = imgUri.toString();
            imgView.setImageURI(Uri.parse(tmp));
            System.out.println("========================================" + tmp);
            updateImageUser(imgUri.toString());
        }
    }


    private void updateImageUser(final String img) {

        Intent it = this.getIntent();
        String userId = it.getStringExtra("userid");

        dataClient = ApiUtils.getData();
        call = dataClient.getUserInfo(Integer.parseInt(userId));
        call.enqueue(new Callback<AppUserVM>() {
            @Override
            public void onResponse(Call<AppUserVM> call, Response<AppUserVM> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                userInfoVM = response.body();
                userInfoVM.setImage(img);
                updateUser();
            }

            @Override
            public void onFailure(Call<AppUserVM> call, Throwable t) {

            }
        });
    }
}
