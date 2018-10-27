package khoa.example.com.project_swd_managerfootfield;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerActivity extends AppCompatActivity {

    Button btnLogout,btnInforUser,btnFindPitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        btnLogout=findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences share = getSharedPreferences("com.khoa.filetoken", MODE_PRIVATE);
                SharedPreferences.Editor editor = share.edit();
                editor.remove("token");
                editor.commit();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnInforUser=findViewById(R.id.btnInforUser);
        btnInforUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),InformationUserActivity.class);
                startActivity(intent);
            }
        });

        btnFindPitch=findViewById(R.id.btnFindPitch);
        btnFindPitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),FindPitchActivity.class);
                startActivity(intent);
            }
        });
    }
}
