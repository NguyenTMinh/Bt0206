package com.example.bt0206;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LapTruongPhongActivity extends AppCompatActivity {
    public static final String TRUONG_PHONG_DATA = "TRUONG_PHONG_DATA";
    public static final String PHO_PHONG_DATA = "PHO_PHONG_DATA";

    private Button mXacNhanButton;
    private EditText mTruongPhongEditText;
    private EditText mPhoPhongEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptruongphong);

        mXacNhanButton = findViewById(R.id.bt_luu_tp);
        mTruongPhongEditText = findViewById(R.id.et_tp);
        mPhoPhongEditText = findViewById(R.id.et_pp);

        mXacNhanButton.setOnClickListener(view -> {
            String tp = mTruongPhongEditText.getText().toString().trim();
            String pp = mPhoPhongEditText.getText().toString().trim();
            Intent intent = new Intent();
            intent.putExtra(TRUONG_PHONG_DATA, tp);
            intent.putExtra(PHO_PHONG_DATA, pp);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
