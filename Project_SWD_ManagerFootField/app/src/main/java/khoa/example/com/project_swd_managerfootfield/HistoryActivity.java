package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import khoa.example.com.project_swd_managerfootfield.Retrofit2.ApiUtils;
import khoa.example.com.project_swd_managerfootfield.Retrofit2.DataClient;
import khoa.example.com.project_swd_managerfootfield.VM.HistoryVM;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    ListView listViewHistory;
    TextView txtHistory;

    DataClient dataClient;
    Call<List<HistoryVM>> historyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listViewHistory = findViewById(R.id.listViewHistory);
        txtHistory = findViewById(R.id.txtHistory);
        SharedPreferences share = getSharedPreferences("com.khoa.filetoken", MODE_PRIVATE);
        String userId = share.getString("userid", null);
        loadHistory(Integer.parseInt(userId));

    }

    private void loadHistory(int userId) {
        dataClient = ApiUtils.getData();
        historyCall = dataClient.getHistory(userId);
        historyCall.enqueue(new Callback<List<HistoryVM>>() {
            @Override
            public void onResponse(Call<List<HistoryVM>> call, Response<List<HistoryVM>> response) {
                if (!response.isSuccessful()) return;
                final List<HistoryVM> vms = response.body();

                if (vms == null) {
                    txtHistory.setText("HistoryVM is empty");
                } else {
                    CustomAdapterHistory adapter = new CustomAdapterHistory(HistoryActivity.this, R.layout.row_listviewhistory, vms);
                    listViewHistory.setAdapter(adapter);

                    listViewHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //them moi
                            Intent intent = new Intent(HistoryActivity.this, BookedActivity.class);
                            intent.putExtra("orderId", String.valueOf(vms.get(i).getOrderId()));
                            startActivity(intent);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<List<HistoryVM>> call, Throwable t) {

            }
        });
    }
}
