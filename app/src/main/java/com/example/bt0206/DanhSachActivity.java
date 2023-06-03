package com.example.bt0206;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DanhSachActivity extends AppCompatActivity {
    private ListView listView;
    private List<NhanVien> nhanVienList;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach);
        listView = findViewById(R.id.lv_nhanvien);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            nhanVienList = getIntent().getParcelableArrayListExtra("NVS", NhanVien.class);
            List<String> temp = new ArrayList<>();
            for (NhanVien nv: nhanVienList) {
                temp.add(nv.getMaNV() + ". " + nv.getTenNV() + " " + ((nv.isGender())?"Nam":"Nữ"));
            }
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, temp);
            listView.setAdapter(mAdapter);
        }
    }
}
