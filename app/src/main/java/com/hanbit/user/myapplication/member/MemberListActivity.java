package com.hanbit.user.myapplication.member;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemberListActivity extends Activity {

    ListView lv_memberlist;
    MemberSerivce service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        service = new MemberServiceImpl(this.getApplicationContext());
        ArrayList<MemberBean> list = service.list();
        Log.d("서비스에서 넘어온 데이터 갯수 : ",String.valueOf(list.size()));
        lv_memberlist.setAdapter(new MemberAdapter(this,list));
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                Object o = lv_memberlist.getItemAtPosition(i);
                MemberBean member = (MemberBean) o;
                Toast.makeText(MemberListActivity.this,"선택한 이름"+member.getName(),
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MemberListActivity.this,
                        DetailActivity.class);

                intent.putExtra("id",member.getId());
                startActivity(intent);

            }
        });
        lv_memberlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MemberListActivity.this)
                        .setTitle("삭제 OK ?")
                        .setMessage("정말로 삭제하시겠습니까?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 삭제코드

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 취소 코드
                            }
                        })
                        .show();
                return true;
            }
        });

    }
}
