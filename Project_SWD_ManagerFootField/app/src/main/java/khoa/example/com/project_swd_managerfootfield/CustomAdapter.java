package khoa.example.com.project_swd_managerfootfield;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import khoa.example.com.project_swd_managerfootfield.VM.LocationInfoVM;

public class CustomAdapter extends ArrayAdapter<LocationInfoVM> {
    private Context context;
    private int resource;
    private List<LocationInfoVM> arrLocationInfor;

    public CustomAdapter(Context context, int resource, List<LocationInfoVM> arrLocationInfor) {
        super(context, resource, arrLocationInfor);
        this.context = context;
        this.resource = resource;
        this.arrLocationInfor = arrLocationInfor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_listview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imgAvata = convertView.findViewById(R.id.imgAvatarRowLists);
            viewHolder.txtName = convertView.findViewById(R.id.txtNameRowList);
            viewHolder.txtAddress = convertView.findViewById(R.id.txtAddressRowList);
            viewHolder.txtPhone = convertView.findViewById(R.id.txtPhoneRowList);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LocationInfoVM locationInfoVM = arrLocationInfor.get(position);

        String img = locationInfoVM.getImage();

       Picasso.get().load(img).into(viewHolder.imgAvata);

        viewHolder.txtName.setText(locationInfoVM.getLocationName());
        viewHolder.txtAddress.setText(locationInfoVM.getAddress());
        viewHolder.txtPhone.setText(locationInfoVM.getPhone());
        return convertView;
    }

}

class ViewHolder {

    ImageView imgAvata;
    TextView txtName, txtAddress, txtPhone;
}
