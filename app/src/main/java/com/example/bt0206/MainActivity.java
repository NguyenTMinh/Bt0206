package com.example.bt0206;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActionController{
    private EditText mMaPhongBanEdittext;
    private EditText mTenPhongBanEdittext;
    private Button mLuuPhongBanButton;
    private RecyclerView mPhongBanRecyclerView;
    private List<PhongBan> mPhongBans;
    private PhongBanAdapter mPhongBanAdapter;
    private int mCurrentEditItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

        mPhongBans = new ArrayList<>();
        mPhongBanAdapter = new PhongBanAdapter(mPhongBans, this, this);

        mPhongBanRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPhongBanRecyclerView.setAdapter(mPhongBanAdapter);

        mLuuPhongBanButton.setOnClickListener(view -> {
            String s1 = mMaPhongBanEdittext.getText().toString();
            String s2 = mTenPhongBanEdittext.getText().toString();
            if (!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)) {
                PhongBan phongBan = new PhongBan();
                phongBan.setMaPhongBan(s1);
                phongBan.setTenPhongBan(s2);
                mPhongBans.add(phongBan);
                mPhongBanAdapter.notifyDataSetChanged();
            }
        });
    }

    private void findView() {
        mMaPhongBanEdittext = findViewById(R.id.et_mpb);
        mTenPhongBanEdittext = findViewById(R.id.et_tpb);
        mLuuPhongBanButton = findViewById(R.id.bt_luuphongban);
        mPhongBanRecyclerView = findViewById(R.id.rv_phongban);
    }

    @Override
    public void themNhanVien(int pos) {
        mCurrentEditItem = pos;
        Intent intent = new Intent(this, ThemNhanVienActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void xemDanhSachNhanVien(int pos) {
        mCurrentEditItem = pos;
        Intent intent = new Intent(this, DanhSachActivity.class);
        intent.putParcelableArrayListExtra("NVS", (ArrayList<? extends Parcelable>) mPhongBans.get(mCurrentEditItem).getDanhSachNhanVien());
        startActivity(intent);
    }

    @Override
    public void lapTruongPhong(int pos) {
        mCurrentEditItem = pos;
        Intent intent = new Intent(this, LapTruongPhongActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void xoa(int pos) {
        mPhongBans.remove(pos);
        mPhongBanAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0: {
                    String tp = data.getStringExtra(LapTruongPhongActivity.TRUONG_PHONG_DATA);
                    String pps = data.getStringExtra(LapTruongPhongActivity.PHO_PHONG_DATA);
                    String[] pp = pps.split(",");

                    mPhongBans.get(mCurrentEditItem).setTenTruongPhong(tp);
                    if (!TextUtils.isEmpty(pps)) {
                        for (String p: pp) {
                            mPhongBans.get(mCurrentEditItem).addPhoPhong(p);
                        }
                    }
                    mPhongBanAdapter.notifyDataSetChanged();
                    break;
                }
                case 1: {
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                        NhanVien nhanVien = data.getParcelableExtra(ThemNhanVienActivity.NHAN_VIEN, NhanVien.class);
                        mPhongBans.get(mCurrentEditItem).addNhanVien(nhanVien);
                        mPhongBanAdapter.notifyDataSetChanged();
                    }
                    break;
                }
            }
        }
    }
}