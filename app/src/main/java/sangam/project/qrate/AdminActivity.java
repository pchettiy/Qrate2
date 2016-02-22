package sangam.project.qrate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    public void browseasuser(View v){
        Intent intent=new Intent(getApplicationContext(),UserActivity.class);
        startActivity(intent);
    }
    public void adminpanel(View v){
        Intent intent=new Intent(getApplicationContext(),AdminPanel.class);
        startActivity(intent);
    }
}
