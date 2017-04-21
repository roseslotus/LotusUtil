package com.lotus.share;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvPlayDefultSound;
    private TextView tvDevice_sn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPlayDefultSound=(TextView) findViewById(R.id.playDefultSound);
        tvDevice_sn=(TextView)findViewById(R.id.device_sn);


        tvPlayDefultSound.setOnClickListener(this);
        tvDevice_sn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            if(v.getId()== R.id.playDefultSound){
                VoliceUtils.playVibrator(this,10);
            }else if(v.getId()==R.id.device_sn){
                DeviceUtils.getDeviceSn();
            }
    }
}
