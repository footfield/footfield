package khoa.example.com.project_swd_managerfootfield;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;
import khoa.example.com.project_swd_managerfootfield.VM.OrderVM;
import khoa.example.com.project_swd_managerfootfield.VM.SlotOfPitchVM;
import khoa.example.com.project_swd_managerfootfield.VM.TypePitchVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPitchActivity extends AppCompatActivity {

    TextView txtName, txtAddress, txtPhone, txtTotal;
    Spinner spTypePitch, spPitchName;
    GridLayout gridOfCbSlot;

    DataClient dataClient;
    Call<List<TypePitchVM>> typeCall;
    Call<List<SlotOfPitchVM>> slotCall;
    Call<List<PitchDetail>> pitchCall;
    Call<Integer> orderCall;

    List<TypePitchVM> listTypePitch;
    List<String> descriptionOfTypePitch;
    Map<Integer, String> mapTypePitch;

    List<SlotOfPitchVM> listSlot;

    List<PitchDetail> listPitchByTypeAndSlot;
    List<String> listNameOfPitchByTypeAndSlot;
    Map<Integer, String> mapPitchByTypeAndSlot;

    com.rey.material.widget.CheckBox cbSlot;
    List<Integer> listIdOfSlot;
    int keyOfTypePitch = 0;
    int fieldDetailID = 0;

    LocationInfoVM obj;

    Button btnBook;

    String dateNow, datePick;
    TextView txtPickDate;

    Date resultNow, resultPick;

    ImageView imgAvatarPitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pitch);

        txtName = findViewById(R.id.txtNamePitch);
        txtAddress = findViewById(R.id.txtAddressPitch);
        txtPhone = findViewById(R.id.txtPhonePitch);
        txtTotal = findViewById(R.id.txtTotal);
        txtPickDate = findViewById(R.id.txtPickDate);
        imgAvatarPitch=findViewById(R.id.imgAvatarPitch);

        btnBook = findViewById(R.id.btnBooked);


        listIdOfSlot = new ArrayList<>();

        Intent intent = getIntent();
        obj = (LocationInfoVM) intent.getSerializableExtra("pitch");
        loadInformationOfPitch(obj);

        //set spiner list type pitch
        spTypePitch = findViewById(R.id.spTypePitch);

        System.out.println("+++++++++++++++++++1" + obj.getLocationID());
        // tra 1 list type pitch
        loadListType(obj.getLocationID());


        gridOfCbSlot = findViewById(R.id.gridOfCbSlot);
        loadListSlot();

        spPitchName = findViewById(R.id.spPitchName);
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
                        keyOfTypePitch = returnKeyOfPitchType(mapTypePitch, spTypePitch);
                        getTotal(keyOfTypePitch, listIdOfSlot);
                        if (keyOfTypePitch != 0 && listIdOfSlot != null && !listIdOfSlot.isEmpty() && resultPick != null) {
                            loadListPitchDetail(keyOfTypePitch, listIdOfSlot, resultPick.getTime());
                        }
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
                            } else {
                                for (int i = 0; i < listIdOfSlot.size(); i++) {
                                    if (listIdOfSlot.get(i) == compoundButton.getId()) {
                                        listIdOfSlot.remove(i);
                                    }
                                }

                            }
                            getTotal(keyOfTypePitch, listIdOfSlot);
                            txtTotal.setText(String.valueOf(getTotal(keyOfTypePitch, listIdOfSlot)));
                            if (keyOfTypePitch != 0 && listIdOfSlot != null && !listIdOfSlot.isEmpty() && resultPick != null) {
                                loadListPitchDetail(keyOfTypePitch, listIdOfSlot, resultPick.getTime());
                            }
                            if (listIdOfSlot.size() == 0) {
                                spPitchName.setAdapter(null);
                                fieldDetailID = 0;
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


    private void loadListPitchDetail(int typeId, List<Integer> slotIds, Long dateTook) {

        dataClient = ApiUtils.getData();
        pitchCall = dataClient.getPitch(slotIds, typeId, dateTook);
        pitchCall.enqueue(new Callback<List<PitchDetail>>() {
            @Override
            public void onResponse(Call<List<PitchDetail>> call, Response<List<PitchDetail>> response) {
                if (!response.isSuccessful()) return;
                listPitchByTypeAndSlot = response.body();
                listNameOfPitchByTypeAndSlot = new ArrayList<>();
                mapPitchByTypeAndSlot = new HashMap<>();

                for (int i = 0; i < listPitchByTypeAndSlot.size(); i++) {
                    listNameOfPitchByTypeAndSlot.add(listPitchByTypeAndSlot.get(i).getDescription());
                    mapPitchByTypeAndSlot.put(listPitchByTypeAndSlot.get(i).getId(), listPitchByTypeAndSlot.get(i).getDescription());
                }

                ArrayAdapter adapter2 = new ArrayAdapter(DetailPitchActivity.this, android.R.layout.simple_spinner_item, listNameOfPitchByTypeAndSlot);
                spPitchName.setAdapter(adapter2);

                spPitchName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        fieldDetailID = returnKeyOfPitchType(mapPitchByTypeAndSlot, spPitchName);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<PitchDetail>> call, Throwable t) {

            }
        });

    }

    public void loadInformationOfPitch(LocationInfoVM location) {
        //load image
        Picasso.get().load(location.getImage()).into(imgAvatarPitch);
        txtName.setText("NAME: " + location.getLocationName());
        txtAddress.setText("ADDRESS: " + location.getAddress());
        txtPhone.setText("PHONE: " + location.getPhone());
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

    public void clickToPickDate(View view) {
        final Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);


        dateNow = simpleDateFormat.format(calendar.getTime());
        try {
            resultNow = simpleDateFormat.parse(dateNow);
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        Toast.makeText(DetailPitchActivity.this, dateNow, Toast.LENGTH_SHORT).show();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                datePick = simpleDateFormat.format(calendar.getTime());
                //txtPickDate.setText(datePick);
                try {
                    //resultPick = calendar.getTime();
                    resultPick = new SimpleDateFormat("dd/MM/yyyy").parse(datePick);
                    if (resultPick.before(resultNow)) {
                        Toast.makeText(DetailPitchActivity.this, "Can't book a day in the past!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        txtPickDate.setText(datePick);
                        if (keyOfTypePitch != 0 && listIdOfSlot != null && !listIdOfSlot.isEmpty() && resultPick != null) {
                            loadListPitchDetail(keyOfTypePitch, listIdOfSlot, resultPick.getTime());
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void clickToBooked(View view) {
        //moi them
        if (fieldDetailID == 0) {
            return;
        }
        bookPitch();
    }

    private void bookPitch() {
        SharedPreferences share = getSharedPreferences("com.khoa.filetoken", MODE_PRIVATE);
        String userId = share.getString("userid", null);
        OrderVM orderVM = new OrderVM(fieldDetailID, listIdOfSlot, Integer.parseInt(userId), resultPick.getTime(), Double.parseDouble(txtTotal.getText().toString()));
        dataClient = ApiUtils.getData();
        orderCall = dataClient.orderPitch(orderVM);
        orderCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                int orderId = response.body();
                if (orderId != 0) {
                    Intent intent = new Intent(DetailPitchActivity.this, BookedActivity.class);
                    intent.putExtra("orderId", orderId + "");
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                System.out.println("+++++++++++++++++++++++++fail:" + t.getMessage());
            }
        });
    }

    private double getTotal(int idTypeOfPitch, List<Integer> listSlotOfPitch) {

        double priceOfTypePitch = 0;
        double total = 0;

        for (int i = 0; i < listTypePitch.size(); i++) {
            if (idTypeOfPitch == listTypePitch.get(i).getId()) {
                priceOfTypePitch = listTypePitch.get(i).getPrice();
                break;
            }
        }
        List<SlotOfPitchVM> list = listSlot;
        for (int i = 0; i < listSlotOfPitch.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (listSlotOfPitch.get(i) == list.get(j).getId()) {
                    total = total + list.get(j).getMutiple();
                }
            }
        }
        total = total * priceOfTypePitch;

        if (total == 0) {
            btnBook.setEnabled(false);
        }else{
            btnBook.setEnabled(true);
        }
        return total;
    }
}
