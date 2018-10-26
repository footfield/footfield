package khoa.example.com.project_swd_managerfootfield;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FindPitchActivity extends AppCompatActivity {

    Spinner spLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pitch);

        spLocation = findViewById(R.id.spLocation);
        List<String> listLocation = new ArrayList<>();
        listLocation.add("q1");
        listLocation.add("q2");
        listLocation.add("q3");
        listLocation.add("q4");
        listLocation.add("q5");
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, listLocation);
        spLocation.setAdapter(adapter);
    }
}
