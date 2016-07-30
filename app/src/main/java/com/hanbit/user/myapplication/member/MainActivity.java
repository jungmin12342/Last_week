package com.hanbit.user.myapplication.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hanbit.user.myapplication.R;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btJoin, btAdd, btLogin, btCount, btList, btFind, btUpdate, btDelete, btMap;
    MemberSerivce service ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        service  = new MemberServiceImpl(this.getApplicationContext());
        btJoin = (Button) findViewById(R.id.btJoin);
        btAdd = (Button) findViewById(R.id.btAdd);
        btLogin = (Button) findViewById(R.id.btLogin);
        btCount = (Button) findViewById(R.id.btCount);
        btList = (Button) findViewById(R.id.btList);
        btFind = (Button) findViewById(R.id.btFind);
        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btMap =(Button)findViewById(R.id.btMap);
        btMap.setOnClickListener(this);
        btJoin.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        btCount.setOnClickListener(this);
        btList.setOnClickListener(this);
        btFind.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btJoin:
                this.startActivity(new Intent(this, JoinActivity.class));
                break;
            case R.id.btAdd:
                break;
            case R.id.btLogin:
                this.startActivity(new Intent(this, Loginactivity.class));
                break;
            case R.id.btCount:
                break;
            case R.id.btList:
                break;
            case R.id.btFind:
                break;
            case R.id.btUpdate:
                break;
            case R.id.btDelete:
                break;
            case R.id.btMap:
                this.startActivity(new Intent(this, MapsActivity.class));
                break;
        }
        //Toast.makeText(this,v.getId()+"가 눌림", Toast.LENGTH_SHORT).show();
    }
}
