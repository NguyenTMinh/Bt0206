package com.example.bt0206;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NhanVien implements Parcelable {
    private String maNV;
    private String tenNV;
    private boolean gender;

    public NhanVien(String maNV, String tenNV, boolean gender) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gender = gender;
    }

    protected NhanVien(Parcel in) {
        maNV = in.readString();
        tenNV = in.readString();
        gender = in.readByte() != 0;
    }

    public static final Creator<NhanVien> CREATOR = new Creator<NhanVien>() {
        @Override
        public NhanVien createFromParcel(Parcel in) {
            return new NhanVien(in);
        }

        @Override
        public NhanVien[] newArray(int size) {
            return new NhanVien[size];
        }
    };

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public boolean isGender() {
        return gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(maNV);
        parcel.writeString(tenNV);
        parcel.writeByte((byte) (gender ? 1 : 0));
    }
}
