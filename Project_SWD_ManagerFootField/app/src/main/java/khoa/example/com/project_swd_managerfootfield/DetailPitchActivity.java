package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;

public class DetailPitchActivity extends AppCompatActivity {

    TextView txtName, txtAddress, txtPhone;
    Spinner spTypePitch, spPitchName;
    CheckBox cbSlot;
    LinearLayout linearOfCbSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pitch);

        txtName = findViewById(R.id.txtNamePitch);
        txtAddress = findViewById(R.id.txtAddressPitch);
        txtPhone = findViewById(R.id.txtPhonePitch);

        //set spiner list type pitch
        spTypePitch = findViewById(R.id.spTypePitch);
        List<String> listType = new ArrayList<>();
        listType.add("sân 5 ");
        listType.add("sân 7 ");
        listType.add("sân 11");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listType);
        spTypePitch.setAdapter(adapter);


        spPitchName = findViewById(R.id.spPitchName);
        List<String> listPitchByTypeAndSlot = new ArrayList<>();
        listPitchByTypeAndSlot.add("sân 1");
        listPitchByTypeAndSlot.add("sân 2");
        listPitchByTypeAndSlot.add("sân 3");
        listPitchByTypeAndSlot.add("sân 4");
        listPitchByTypeAndSlot.add("sân 5");
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listPitchByTypeAndSlot);
        spPitchName.setAdapter(adapter2);

        linearOfCbSlot=findViewById(R.id.linearOfCbSlot);
        for (int i=0;i<3;i++){
            CheckBox cbSlot=new CheckBox(this);
            cbSlot.setText("slot "+(i+1));
            linearOfCbSlot.addView(cbSlot);
        }


        Intent intent = getIntent();
        LocationInfoVM obj = (LocationInfoVM) intent.getSerializableExtra("pitch");
        loadInformationOfPitch(obj);

        Toast.makeText(getApplicationContext(), obj.getLocationName(), Toast.LENGTH_SHORT).show();
    }

    public void loadInformationOfPitch(LocationInfoVM location) {
        txtName.setText("NAME: " + location.getLocationName());
        txtAddress.setText("ADDRESS: " + location.getAddress());
        txtPhone.setText("PHONE: " + location.getPhone());
    }

    public void clickToBookField(View view) {
    }
}
