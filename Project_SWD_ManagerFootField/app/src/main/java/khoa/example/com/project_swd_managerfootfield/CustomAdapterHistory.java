package khoa.example.com.project_swd_managerfootfield;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import khoa.example.com.project_swd_managerfootfield.VM.HistoryVM;


public class CustomAdapterHistory extends ArrayAdapter<HistoryVM> {

    private Context context;
    private int resource;
    private List<HistoryVM> arrHistoryVM;

    public CustomAdapterHistory(Context context, int resource, List<HistoryVM> arrHistoryVM) {
        super(context, resource, arrHistoryVM);
        this.context = context;
        this.resource = resource;
        this.arrHistoryVM = arrHistoryVM;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder2 viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listviewhistory, parent, false);
            viewHolder = new ViewHolder2();
//            viewHolder.txtStt = convertView.findViewById(R.id.txtSttRowHistory);
            viewHolder.txtLocationName = convertView.findViewById(R.id.txtLocationNameRowList);
            viewHolder.txtDatePicked = convertView.findViewById(R.id.txtDatePickedRowList);
            viewHolder.txtPrice = convertView.findViewById(R.id.txtPriceRowList);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder2) convertView.getTag();
        }
        HistoryVM historyVM = arrHistoryVM.get(position);


//        viewHolder.txtStt.setText(historyVM.getStt()+"");
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        viewHolder.txtLocationName.setText(historyVM.getLocationName());
        viewHolder.txtDatePicked.setText(simpleDateFormat.format(new Date(historyVM.getDatePicked())) + " VND");
        viewHolder.txtPrice.setText(historyVM.getTotalPrice());
        return convertView;
    }

}

class ViewHolder2 {

    TextView txtStt,txtLocationName, txtDatePicked, txtPrice;
}

