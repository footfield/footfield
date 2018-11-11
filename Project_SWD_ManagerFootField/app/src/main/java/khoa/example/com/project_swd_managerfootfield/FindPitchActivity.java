package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.DistrictVM;
import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPitchActivity extends AppCompatActivity {

    com.rey.material.widget.Spinner spLocation;
    ListView listPitch;

    DataClient dataClient;
    Call<List<DistrictVM>> disCall;
    Call<List<LocationInfoVM>> loCall;
    List<String> disNames;
    Map<String, Integer> disMap;

    List<LocationInfoVM> listPitchByDis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pitch);

        listPitch = findViewById(R.id.listView);

        spLocation = findViewById(R.id.spLocation);

        getAllDis();

    }

    private void getAllDis() {
        dataClient = ApiUtils.getData();
        disCall = dataClient.getAllDistrict();
        disCall.enqueue(new Callback<List<DistrictVM>>() {
            @Override
            public void onResponse(Call<List<DistrictVM>> call, Response<List<DistrictVM>> response) {
                if (!response.isSuccessful()) return;
                List<DistrictVM> vms = response.body();
                disNames = new ArrayList<>();
                disMap = new HashMap<>();
                for (int i = 0; i < vms.size(); i++) {
                    disNames.add(vms.get(i).getDisName());
                    disMap.put(vms.get(i).getDisName(), vms.get(i).getId());
                }

                loadPage();
            }

            @Override
            public void onFailure(Call<List<DistrictVM>> call, Throwable t) {

            }
        });
    }

    private void loadPage() {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, disNames);
        spLocation.setAdapter(adapter);

        spLocation.setOnItemSelectedListener(new com.rey.material.widget.Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(com.rey.material.widget.Spinner parent, View view, int position, long id) {
                String name = spLocation.getSelectedItem().toString();
                int positions = disMap.get(name);
                generationListTest(positions);
            }
        });

    }

    private void generationListTest(int position) {
        dataClient = ApiUtils.getData();
        loCall = dataClient.getLocationInfo(position);
        loCall.enqueue(new Callback<List<LocationInfoVM>>() {
            @Override
            public void onResponse(Call<List<LocationInfoVM>> call, Response<List<LocationInfoVM>> response) {
                if (response.isSuccessful()) {
                    listPitchByDis = response.body();
                }
                CustomAdapter custom = new CustomAdapter(getApplicationContext(), R.layout.row_listview, listPitchByDis);
                listPitch.setAdapter(custom);

                listPitch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LocationInfoVM obj = (LocationInfoVM) listPitch.getItemAtPosition(i);
                       Intent intent=new Intent(getApplicationContext(),DetailPitchActivity.class);
                        intent.putExtra("pitch",obj);
                        startActivity(intent);

                    }
                });
            }

            @Override
            public void onFailure(Call<List<LocationInfoVM>> call, Throwable t) {

            }
        });

    }
}
