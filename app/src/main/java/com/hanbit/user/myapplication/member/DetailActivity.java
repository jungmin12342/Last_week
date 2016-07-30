package com.hanbit.user.myapplication.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hanbit.user.myapplication.Map.*;

public class DetailActivity extends Activity implements View.OnClickListener {
    MemberSerivce service;
    MemberBean member;
    TextView tvID,tvPW,tvName,tvEmail,tvPhone,tvPhoto,tvAddr;
    Button btPhone,btSms,btUpdate,btList, btmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        service = new MemberServiceImpl(this.getApplicationContext());
        Intent intent = this.getIntent();
        String id = intent.getExtras().getString("id");
        member = service.findById(id);
        tvID = (TextView) findViewById(R.id.tvID);
        tvPW = (TextView) findViewById(R.id.tvPW);
        tvName = (TextView) findViewById(R.id.tvName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvPhoto = (TextView) findViewById(R.id.tvPhoto);
        tvAddr = (TextView) findViewById(R.id.tvAddr);
        btmap = (Button)findViewById(R.id.btMap);

        tvID.setText(member.getId());
        tvPW.setText(member.getPw());
        tvName.setText(member.getName());
        tvEmail.setText(member.getEmail());
        tvPhone.setText(member.getPhone());
        tvPhoto.setText(member.getId()+".jpg");
        tvAddr.setText(member.getAddr());

        btPhone = (Button) findViewById(R.id.btPhone);
        btSms = (Button) findViewById(R.id.btSms);
        btUpdate = (Button) findViewById(R.id.btUpdate);
        btList = (Button) findViewById(R.id.btList);
        btPhone.setOnClickListener(this);
        btSms.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btList.setOnClickListener(this);
        btmap.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPhone :

                break;
            case R.id.btSms :

                break;
            case R.id.btUpdate : break;
            case R.id.btList :
                startActivity(new Intent(this,MemberListActivity.class));
                break;
            case R.id.btMap:
                Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
                member.setAddr("37.5665350,126.9779690");
                intent.putExtra("pos",member.getAddr());
                startActivity(intent);
                break;
        }

    }
}