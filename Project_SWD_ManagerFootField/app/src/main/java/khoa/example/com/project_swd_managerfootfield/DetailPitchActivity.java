package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;

public class DetailPitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pitch);

        Intent intent = getIntent();
        LocationInfoVM obj = (LocationInfoVM) intent.getSerializableExtra("pitch");

        Toast.makeText(getApplicationContext(), obj.getLocationName(), Toast.LENGTH_SHORT).show();
    }
}
