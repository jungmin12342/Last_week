package com.hanbit.user.myapplication.member;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Member;

public class AddActivity extends Activity implements View.OnClickListener{
    EditText etName, etPhone;
    Button btAdd;
    MemberSerivce serivce = new MemberServiceImpl(this.getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = (EditText)findViewById(R.id.etname);
    }
    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        MemberBean guest = new MemberBean();
        guest.setName(name);
        guest.setPhone(phone);


    }
}
