package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;

public class DetailPitchActivity extends AppCompatActivity {

    TextView txtName, txtAddress, txtPhone;
    Spinner spTypePitch, spPitchName;
    CheckBox cbSlot;
    LinearLayout linearOfCbSlot;

    List<TypePitch> listTypePitch;
    List<String> descriptionOfTypePitch;
    Map<Integer, String> mapTypePitch;

    List<PitchDetail> listPitchByTypeAndSlot;
    List<String> listNameOfPitchByTypeAndSlot;
    Map<Integer, String> mapPitchByTypeAndSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pitch);

        txtName = findViewById(R.id.txtNamePitch);
        txtAddress = findViewById(R.id.txtAddressPitch);
        txtPhone = findViewById(R.id.txtPhonePitch);

        //set spiner list type pitch
        spTypePitch = findViewById(R.id.spTypePitch);

        // tra 1 list type pitch
        listTypePitch = new ArrayList<>();
        descriptionOfTypePitch = new ArrayList<>();
        mapTypePitch = new HashMap<>();
        for (int i = 0; i < listTypePitch.size(); i++) {
            descriptionOfTypePitch.add(listTypePitch.get(i).getDescription());
            mapTypePitch.put(listTypePitch.get(i).getId(), listTypePitch.get(i).getDescription());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, descriptionOfTypePitch);
        spTypePitch.setAdapter(adapter);
        spTypePitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //return key of pitch
                int key = returnKeyOfPitchType(mapTypePitch, spTypePitch);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spPitchName = findViewById(R.id.spPitchName);
        //return 1 list pitch when filter type pitch and slot
        listPitchByTypeAndSlot = new ArrayList<>();
        listNameOfPitchByTypeAndSlot = new ArrayList<>();
        mapPitchByTypeAndSlot = new HashMap<>();

        for (int i = 0; i < listPitchByTypeAndSlot.size(); i++) {
            listNameOfPitchByTypeAndSlot.add(listPitchByTypeAndSlot.get(i).getDescription());
            mapPitchByTypeAndSlot.put(listPitchByTypeAndSlot.get(i).getId(), listPitchByTypeAndSlot.get(i).getDescription());
        }

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listPitchByTypeAndSlot);
        spPitchName.setAdapter(adapter2);

        spPitchName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //return key of pitch when filter type pitch and slot
                int key = returnKeyOfPitchType(mapPitchByTypeAndSlot, spPitchName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        linearOfCbSlot = findViewById(R.id.linearOfCbSlot);
        for (int i = 0; i < 3; i++) {
            CheckBox cbSlot = new CheckBox(this);
            cbSlot.setText("slot " + (i + 1));
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

    public int returnKeyOfPitchType(Map<Integer, String> map, Spinner spinner) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            String description = spinner.getSelectedItem().toString();
            if (entry.getValue().equals(description)) {
                return entry.getKey();
            }
        }
        return 0;
    }
}
