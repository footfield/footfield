package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BookedActivity extends AppCompatActivity {

    TextView txtLocationName,txtAddress,txtPhone,txtTypeOfPitch,txtNameOfPitch,
    txtDateBook,txtDatePicked,txtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);

        Intent intent=getIntent();

        txtLocationName=findViewById(R.id.txtLocationName);
        txtAddress=findViewById(R.id.txtAddtess);
        txtPhone=findViewById(R.id.txtPhone);
        txtTypeOfPitch=findViewById(R.id.txtTypeOfPitch);
        txtNameOfPitch=findViewById(R.id.txtNameOfPitch);
        txtDateBook=findViewById(R.id.txtDateBook);
        txtDatePicked=findViewById(R.id.txtDatePicked);
        txtPrice=findViewById(R.id.txtPrice);

        txtLocationName.setText(intent.getStringExtra("locationName"));
        txtAddress.setText(intent.getStringExtra("address"));
        txtPhone.setText(intent.getStringExtra("phone"));
        txtTypeOfPitch.setText(intent.getStringExtra("typeOfPitch"));
        txtNameOfPitch.setText(intent.getStringExtra("nameOfPitch"));
        txtDateBook.setText(intent.getStringExtra("dateNow"));
        txtDatePicked.setText(intent.getStringExtra("datePicked"));
        txtPrice.setText(intent.getStringExtra("price"));
    }
}
