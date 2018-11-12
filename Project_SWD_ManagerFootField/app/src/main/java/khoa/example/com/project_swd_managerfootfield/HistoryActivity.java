package khoa.example.com.project_swd_managerfootfield;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ListView listViewHistory;
    TextView txtHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listViewHistory=findViewById(R.id.listViewHistory);
        txtHistory=findViewById(R.id.txtHistory);

        List<History> list=new ArrayList<>();
        list.add(new History(1,"nghia thao","11/11/2018","123"));
        list.add(new History(2,"nghia thao","11/11/2018","123"));
        list.add(new History(3,"nghia thao","11/11/2018","123"));
        list.add(new History(4,"nghia thao","11/11/2018","123"));

        if(list==null){
            txtHistory.setText("History is empty");
        }else{
            CustomAdapterHistory adapter=new CustomAdapterHistory(HistoryActivity.this,R.layout.row_listviewhistory,list);
            listViewHistory.setAdapter(adapter);
        }

    }
}
