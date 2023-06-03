package com.example.bt0206;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThemNhanVienActivity extends AppCompatActivity {
    public static final String NHAN_VIEN = "NHAN_VIEN";
    private EditText mMaNVEditText;
    private EditText mTenNVEditText;
    private RadioGroup mGioiTinhRadioGroup;
    private Button mXoaTrangButton;
    private Button mLuuButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhanvien);

        mMaNVEditText = findViewById(R.id.et_manv);
        mTenNVEditText = findViewById(R.id.et_tennv);
        mGioiTinhRadioGroup = findViewById(R.id.rg_gioitinh);
        mXoaTrangButton = findViewById(R.id.bt_xoatrangnv);
        mLuuButton = findViewById(R.id.bt_luunv);

        mXoaTrangButton.setOnClickListener(view -> {
            mMaNVEditText.setText("");
            mTenNVEditText.setText("");
        });

        mLuuButton.setOnClickListener(view -> {
            String ma = mMaNVEditText.getText().toString().trim();
            String ten = mTenNVEditText.getText().toString().trim();
            boolean gioiTinh = (mGioiTinhRadioGroup.getCheckedRadioButtonId()==R.id.rb_nam);
            if (TextUtils.isEmpty(ma) || TextUtils.isEmpty(ten)) {
                return;
            }
            NhanVien nhanVien = new NhanVien(ma, ten, gioiTinh);
            Intent intent = new Intent();
            intent.putExtra(NHAN_VIEN, nhanVien);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
