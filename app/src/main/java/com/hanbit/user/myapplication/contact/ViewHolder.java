package com.hanbit.user.myapplication.contact;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 1027 on 2016-07-16.
 */
public class ViewHolder {
    TextView name,email,phoneNo;
    ImageView photo;

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getEmail() {
        return email;
    }

    public void setEmail(TextView email) {
        this.email = email;
    }

    public TextView getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(TextView phoneNo) {
        this.phoneNo = phoneNo;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }
}
