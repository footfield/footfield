package khoa.example.com.project_swd_managerfootfield;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class CustomAdapterHistory extends ArrayAdapter<History> {

    private Context context;
    private int resource;
    private List<History> arrHistory;

    public CustomAdapterHistory(Context context, int resource, List<History> arrHistory) {
        super(context, resource, arrHistory);
        this.context = context;
        this.resource = resource;
        this.arrHistory = arrHistory;
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
        History history = arrHistory.get(position);


//        viewHolder.txtStt.setText(history.getStt()+"");
        viewHolder.txtLocationName.setText(history.getLocationName());
        viewHolder.txtDatePicked.setText(history.getDatePicked());
        viewHolder.txtPrice.setText(history.getTotalPrice());
        return convertView;
    }

}

class ViewHolder2 {

    TextView txtStt,txtLocationName, txtDatePicked, txtPrice;
}

