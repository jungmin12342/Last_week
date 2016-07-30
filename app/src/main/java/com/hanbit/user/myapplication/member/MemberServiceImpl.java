package com.hanbit.user.myapplication.member;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-07-09.
 */
public class MemberServiceImpl implements MemberSerivce {
    MemberDAO dao;
    GuestDAO guestDAO;
    public MemberServiceImpl(Context context) {

        dao = new MemberDAO(context);
    }

    @Override
    public void join(MemberBean bean) {
        String id = bean.getId();
        String pw = bean.getPw();
        String name = bean.getName();
        String email = bean.getEmail();
        Log.d("넘어온 ID : ",id);
        Log.d("넘어온 PW : ",pw);
        Log.d("넘어온 이름 : ",name);
        Log.d("넘어온 이메일 : ",email);
        dao.insert(bean);

    }

    @Override
    public void add(MemberBean bean) {
        dao.insert(bean);
    }

    @Override
    public boolean login(MemberBean bean) {
        boolean loginOK = false;
        String id = bean.getId();
        String pw = bean.getPw();
        Log.d("서비스로 넘어온 ID : ",id);
        Log.d("서비스로 넘어온 PW : ",pw);
        MemberBean member = dao.login(bean);
        if(member.getId().equals("fail")){
            loginOK = false;
        }else{
            loginOK = true;
        }
        Log.d("DAO에서 반환한 ID",member.getId());
        return loginOK;
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public ArrayList<MemberBean> list() {
        return dao.list();
    }

    @Override
    public ArrayList<MemberBean> findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public MemberBean findById(String id) {
        if(dao.findById(id)==null){
            Log.d("DAO 에서 넘어온 값 : ","null");
        }else{
            Log.d("DAO 에서 넘어온 값 : ","not null");
        }
        return dao.findById(id);
    }

    @Override
    public void update(MemberBean bean) {
        dao.update(bean);
    }

    @Override
    public void delete(String id) {
        dao.delete(id);
    }
}
/*
public class MemberServiceImpl implements MemberSerivce {
    MemberDAO dao ;
    public MemberServiceImpl(Context context){
         dao = new MemberDAO(context);
    }
    public  MemberServiceImpl(){}
    @Override
    public List<MemberBean> findByName(String name) {
        return null;
    }

    @Override
    public void add(MemberBean memberBean) {
        dao.insert(memberBean);
    }

    @Override
    public int count() {
        return dao.count();
    }

    @Override
    public String join(MemberBean memberBean) {
       /* String id = memberBean.getId();
        String pw = memberBean.getPw();
        String name = memberBean.getName();
        String email = memberBean.getEmail();
        Log.d("sent ID","id: " + id + "pw: " +pw + "name is ;" +name + "email :" +email);*/
/*
        dao.insert(memberBean);
        return memberBean.getId();
    }

    @Override
    public boolean login(MemberBean memberBean) {
        boolean loginOK =false;
        String id = memberBean.getId();
        String pw =memberBean.getPw();
        MemberBean member = dao.login(memberBean);
        //meberbean은 폰에서 입력 member은 db랑 들어가기

        if(member.getId().equals("fail")){
            loginOK = false;
        }else{
            loginOK = true;
        }
        Log.d("서비스에서 불리안", String.valueOf(loginOK));
        Log.d("서비스에서 체크한 ID", member.getId());
        return loginOK;
    }

    @Override
    public boolean update(MemberBean memberBean) {
        dao.update(memberBean);
        return false;
    }

    @Override
    public boolean delete(MemberBean memberBean) {
        dao.delete(memberBean.getId());
        return true;
    }

    @Override
    public List<MemberBean> list() {

        return dao.show();
    }

    @Override
    public MemberBean findByID(String id) {

        return dao.findByID(id);
    }
}*/
