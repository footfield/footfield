package khoa.example.com.project_swd_managerfootfield;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;

public class FindPitchActivity extends AppCompatActivity {

    Spinner spLocation;
    ListView listPitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pitch);

        listPitch = findViewById(R.id.listView);

        spLocation = findViewById(R.id.spLocation);
        final List<String> listLocation = new ArrayList<>();
        listLocation.add("q1");
        listLocation.add("q2");
        listLocation.add("q3");
        listLocation.add("q4");
        listLocation.add("q5");
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, listLocation);
        spLocation.setAdapter(adapter);


        spLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int position = spLocation.getSelectedItemPosition() + 1;
                Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();

                List<LocationInfoVM> listPitchByLocation = generationListTest(position);

                CustomAdapter custom = new CustomAdapter(getApplicationContext(), R.layout.row_listview, listPitchByLocation);
                listPitch.setAdapter(custom);

                listPitch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        LocationInfoVM obj = (LocationInfoVM) listPitch.getItemAtPosition(i);
                        Toast.makeText(getApplicationContext(), obj.getLocationName(), Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public List<LocationInfoVM> getListByLocationId(int potision) {
        List<LocationInfoVM> list = new ArrayList<>();
        return list;
    }

    public List<LocationInfoVM> generationListTest(int position) {
        if (position == 1) {
            List<LocationInfoVM> listPitchByLocation = new ArrayList<>();
            LocationInfoVM location1 = new LocationInfoVM(1L, "san 1", "aaa", "https://imgur.com/ErTKgeL", "olala", "123123123");
            LocationInfoVM location2 = new LocationInfoVM(2L, "san 2", "bbb", "https://imgur.com/ErTKgeL", "olala", "123123123");
            LocationInfoVM location3 = new LocationInfoVM(3L, "san 3", "ccc", "https://imgur.com/ErTKgeL", "olala", "123123123");
            LocationInfoVM location4 = new LocationInfoVM(4L, "san 4", "ddd", "a.jpg", "olala", "123123123");
            LocationInfoVM location5 = new LocationInfoVM(4L, "san 5", "ddd", "dm.jpg", "olala", "123123123");
            LocationInfoVM location6 = new LocationInfoVM(4L, "san 6", "ddd", "dm.jpg", "olala", "123123123");
            LocationInfoVM location7 = new LocationInfoVM(4L, "san 7", "ddd", "dm.jpg", "olala", "123123123");
            LocationInfoVM location8 = new LocationInfoVM(4L, "san 8", "ddd", "dm.jpg", "olala", "123123123");

            listPitchByLocation.add(location1);
            listPitchByLocation.add(location2);
            listPitchByLocation.add(location3);
            listPitchByLocation.add(location4);
            listPitchByLocation.add(location5);
            listPitchByLocation.add(location6);
            listPitchByLocation.add(location7);
            listPitchByLocation.add(location8);

            return listPitchByLocation;
        } else if (position == 2) {
            List<LocationInfoVM> listPitchByLocation = new ArrayList<>();
            LocationInfoVM location1 = new LocationInfoVM(1L, "san 9", "aaa", "dm.jpg", "olala", "123123123");
            LocationInfoVM location2 = new LocationInfoVM(2L, "san 10", "bbb", "dm.jpg", "olala", "123123123");
            LocationInfoVM location3 = new LocationInfoVM(3L, "san 11", "ccc", "dm.jpg", "olala", "123123123");
            LocationInfoVM location4 = new LocationInfoVM(4L, "san 12", "ddd", "dm.jpg", "olala", "123123123");
            LocationInfoVM location5 = new LocationInfoVM(4L, "san 13", "ddd", "dm.jpg", "olala", "123123123");

            listPitchByLocation.add(location1);
            listPitchByLocation.add(location2);
            listPitchByLocation.add(location3);
            listPitchByLocation.add(location4);
            listPitchByLocation.add(location5);

            return listPitchByLocation;
        }
        return null;
    }
}
