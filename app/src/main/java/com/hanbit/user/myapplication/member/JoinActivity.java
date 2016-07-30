package com.hanbit.user.myapplication.member;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hanbit.user.myapplication.R;

public class JoinActivity extends Activity implements View.OnClickListener {
    EditText etID, etPW, etEmail, etNAME, etPhone, etPhoto, etAddr;
    TextView tvResult;
    Button cancel, btjoin;
    Button login;
    MemberBean bean;
    MemberSerivce memberSerivce = new MemberServiceImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        init();
    }

    @Override
    public void onClick(View v) {
        String id = etID.getText().toString();
        String pw = etPW.getText().toString();
        String email = etEmail.getText().toString();
        String name = etNAME.getText().toString();
        String phone = etPhone.getText().toString();
        String photo = etPhoto.getText().toString();
        String addr = etAddr.getText().toString();
        bean = new MemberBean(id, pw, name, email, phone, photo, addr);
        switch (v.getId()) {
            case R.id.btjoin:

                 memberSerivce.join(bean);
                break;
            case R.id.cancel:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button17:
                intent = new Intent(this, Loginactivity.class);
                startActivity(intent);
            default:
                Toast.makeText(this, "???", Toast.LENGTH_LONG).show();
        }
    }

    public void init() {
        etID = (EditText) findViewById(R.id.etID);
        etPW = (EditText) findViewById(R.id.etPW);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNAME = (EditText) findViewById(R.id.etName);
        tvResult = (TextView) findViewById(R.id.tvResult);
        etPhone = (EditText)findViewById(R.id.etPhone);
        etPhoto =(EditText)findViewById(R.id.etPhoto);
        etAddr = (EditText)findViewById(R.id.etAddr);
        cancel = (Button) findViewById(R.id.cancel);
        btjoin = (Button) findViewById(R.id.btjoin);
        cancel.setOnClickListener(this);
        btjoin.setOnClickListener(this);
        login = (Button) findViewById(R.id.button17);
        login.setOnClickListener(this);
    }
}
