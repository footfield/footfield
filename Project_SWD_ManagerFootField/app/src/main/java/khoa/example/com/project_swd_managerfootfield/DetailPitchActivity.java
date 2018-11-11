package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;
import khoa.example.com.project_swd_managerfootfield.VM.SlotOfPitchVM;
import khoa.example.com.project_swd_managerfootfield.VM.TypePitchVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPitchActivity extends AppCompatActivity {

    TextView txtName, txtAddress, txtPhone;
    Spinner spTypePitch, spPitchName;
    GridLayout gridOfCbSlot;

    DataClient dataClient;
    Call<List<TypePitchVM>> typeCall;
    Call<List<SlotOfPitchVM>> slotCall;
    Call<List<PitchDetail>> pitchCall;

    List<TypePitchVM> listTypePitch;
    List<String> descriptionOfTypePitch;
    Map<Integer, String> mapTypePitch;

    List<SlotOfPitchVM> listSlot;

    List<PitchDetail> listPitchByTypeAndSlot;
    List<String> listNameOfPitchByTypeAndSlot;
    Map<Integer, String> mapPitchByTypeAndSlot;

    com.rey.material.widget.CheckBox cbSlot;
    List<Integer> listIdOfSlot;
    int keyOfTypePitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pitch);

        txtName = findViewById(R.id.txtNamePitch);
        txtAddress = findViewById(R.id.txtAddressPitch);
        txtPhone = findViewById(R.id.txtPhonePitch);

        listIdOfSlot = new ArrayList<>();

        Intent intent = getIntent();
        LocationInfoVM obj = (LocationInfoVM) intent.getSerializableExtra("pitch");
        loadInformationOfPitch(obj);

        //set spiner list type pitch
        spTypePitch = findViewById(R.id.spTypePitch);

        System.out.println("+++++++++++++++++++1" + obj.getLocationID());
        // tra 1 list type pitch
        loadListType(obj.getLocationID());


        gridOfCbSlot = findViewById(R.id.gridOfCbSlot);
        loadListSlot();


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


        Toast.makeText(getApplicationContext(), obj.getLocationName(), Toast.LENGTH_SHORT).show();
    }

    private void loadListType(int locationId) {
        dataClient = ApiUtils.getData();
        typeCall = dataClient.getFieldTypeLocation(locationId);
        typeCall.enqueue(new Callback<List<TypePitchVM>>() {
            @Override
            public void onResponse(Call<List<TypePitchVM>> call, Response<List<TypePitchVM>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("+++++++++++++++++++fail");
                    return;
                }

                listTypePitch = response.body();
                System.out.println("+++++++++++++++++++2" + listTypePitch.size());
                descriptionOfTypePitch = new ArrayList<>();
                mapTypePitch = new HashMap<>();
                for (int i = 0; i < listTypePitch.size(); i++) {
                    descriptionOfTypePitch.add(listTypePitch.get(i).getDescription());
                    mapTypePitch.put(listTypePitch.get(i).getId(), listTypePitch.get(i).getDescription());
                }

                ArrayAdapter adapter = new ArrayAdapter(DetailPitchActivity.this, android.R.layout.simple_spinner_item, descriptionOfTypePitch);
                spTypePitch.setAdapter(adapter);
                spTypePitch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        //return key of pitch
                        keyOfTypePitch = returnKeyOfPitchType(mapTypePitch, spTypePitch);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<TypePitchVM>> call, Throwable t) {
                System.out.println("+++++++++++++++++++fail1" + t.getMessage());
            }
        });

    }

    private void loadListSlot() {
        dataClient = ApiUtils.getData();
        slotCall = dataClient.getAllSlot();
        slotCall.enqueue(new Callback<List<SlotOfPitchVM>>() {
            @Override
            public void onResponse(Call<List<SlotOfPitchVM>> call, Response<List<SlotOfPitchVM>> response) {
                if (!response.isSuccessful()) return;
                listSlot = response.body();
                for (SlotOfPitchVM s : listSlot) {
                    // com.rey.material.widget.CheckBox cbSlot = new com.rey.material.widget.CheckBox(DetailPitchActivity.this);
                    cbSlot = new com.rey.material.widget.CheckBox(DetailPitchActivity.this);
                    cbSlot.setText(s.getDescription());
                    cbSlot.setId(s.getId());

                    cbSlot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (b) {
                                listIdOfSlot.add(compoundButton.getId());

                                //goi ham load pitch khi chon slot xong o day
                                // key type pitch la keyOfTypePitch
                                // co listId voi keyOfTypePitch r
                            }
                        }
                    });

                    gridOfCbSlot.addView(cbSlot);
                }
            }

            @Override
            public void onFailure(Call<List<SlotOfPitchVM>> call, Throwable t) {

            }
        });
    }


    private void loadListPitchDetail(int typeId, List<Integer> slotIds) {

        dataClient = ApiUtils.getData();
        pitchCall = dataClient.getPitch(slotIds, typeId);
        pitchCall.enqueue(new Callback<List<PitchDetail>>() {
            @Override
            public void onResponse(Call<List<PitchDetail>> call, Response<List<PitchDetail>> response) {
                if (!response.isSuccessful()) return;
                listPitchByTypeAndSlot = response.body();
            }

            @Override
            public void onFailure(Call<List<PitchDetail>> call, Throwable t) {

            }
        });

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
