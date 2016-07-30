package com.hanbit.user.myapplication.member;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-07-09.
 */

public interface MemberSerivce {
    /*Create : DB에 정보를 생성,입력*/
    public void join(MemberBean bean);
    public void add(MemberBean bean);
    /*Read : DB정보를 조회*/
    public boolean login(MemberBean bean);
    public int count();
    public ArrayList<MemberBean> list();
    public ArrayList<MemberBean> findByName(String name);
    public MemberBean findById(String id);
    /*update : DB정보를 수정*/
    public void update(MemberBean bean);
    /*delete : DB정보를 삭제*/
    public void delete(String id);
}
/*
public interface MemberSerivce {

    public String join(MemberBean memberBean);
    public void add(MemberBean memberBean);

    public boolean login(MemberBean memberBean);

    public  boolean update(MemberBean memberBean);

    public boolean delete(MemberBean memberBean);
    public int count();
    public List<MemberBean> list();
    public MemberBean findByID(String id);
    public List<MemberBean> findByName(String name);

}
*/