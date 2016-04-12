package serviceproject.simpleservicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start)
    void onStartClick(){
        Intent intent = new Intent(MainActivity.this, SimpleService.class);
        startService(intent);
    }

    @OnClick(R.id.btn_stop)
    void onStopClick(){
        Intent intent = new Intent(MainActivity.this, SimpleService.class);
        stopService(intent);
    }
}
