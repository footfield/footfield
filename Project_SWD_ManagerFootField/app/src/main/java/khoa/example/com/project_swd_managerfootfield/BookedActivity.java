package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;
import khoa.example.com.project_swd_managerfootfield.VM.OrderInfoVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookedActivity extends AppCompatActivity {

    TextView txtLocationName,txtAddress,txtPhone,txtTypeName,
    txtDateBook,txtDatePicked,txtPrice,txtSlot;

    DataClient dataClient;
    Call<OrderInfoVM> orderInfoVMCall;
    Call<LocationInfoVM> locationCall;

    OrderInfoVM orderInfoVM;
    LocationInfoVM locationInfoVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);

        Intent intent=getIntent();

        txtLocationName=findViewById(R.id.txtLocationName);
        txtAddress=findViewById(R.id.txtAddress);
        txtPhone=findViewById(R.id.txtPhone);
        txtTypeName=findViewById(R.id.txtTypeName);
        txtSlot=findViewById(R.id.txtSlot);
        txtDateBook=findViewById(R.id.txtDateBook);
        txtDatePicked=findViewById(R.id.txtDatePicked);
        txtPrice=findViewById(R.id.txtPrice);

        int orderId = Integer.parseInt(intent.getStringExtra("orderId"));
        loadOrderInfo(orderId);
    }
    private void loadOrderInfo(int id){
        dataClient = ApiUtils.getData();
        orderInfoVMCall = dataClient.getOrderInfo(id);
        orderInfoVMCall.enqueue(new Callback<OrderInfoVM>() {
            @Override
            public void onResponse(Call<OrderInfoVM> call, Response<OrderInfoVM> response) {
                if (!response.isSuccessful()){
                    return;
                }
                orderInfoVM = response.body();

                if (orderInfoVM == null) return;
                loadLocation(orderInfoVM.getLocationId());
            }

            @Override
            public void onFailure(Call<OrderInfoVM> call, Throwable t) {
            }
        });
    }

    private void loadLocation(int id){
        dataClient = ApiUtils.getData();
        locationCall = dataClient.getLocation(id);
        locationCall.enqueue(new Callback<LocationInfoVM>() {
            @Override
            public void onResponse(Call<LocationInfoVM> call, Response<LocationInfoVM> response) {
                if (!response.isSuccessful()) return;
                locationInfoVM = response.body();

                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtLocationName.setText(locationInfoVM.getLocationName());
                txtAddress.setText(locationInfoVM.getAddress());
                txtPhone.setText(locationInfoVM.getPhone());
                txtTypeName.setText(orderInfoVM.getFieldName() + " - " +orderInfoVM.getType());
                String slot = "";
                for (String tmp: orderInfoVM.getSlotVMs()) {
                    slot += tmp +"\n";
                }
                txtSlot.setText(slot);
                txtDateBook.setText(simpleDateFormat.format(new Date(orderInfoVM.getDateBook())));
                txtDatePicked.setText(simpleDateFormat.format(new Date(orderInfoVM.getDateTookPlace())));
                txtPrice.setText(String.valueOf(orderInfoVM.getTotalPrice()));
            }

            @Override
            public void onFailure(Call<LocationInfoVM> call, Throwable t) {

            }
        });
    }

    public void clickToBooked(View view) {
        Intent intent=new Intent(BookedActivity.this,ManagerActivity.class);
        startActivity(intent);
        finish();
    }
}
