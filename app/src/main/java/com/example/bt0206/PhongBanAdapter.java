package com.example.bt0206;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhongBanAdapter extends RecyclerView.Adapter<PhongBanAdapter.PhongBanViewHolder> {
    private List<PhongBan> mListPhongBan;
    private Context mContext;
    private ActionController mActionController;

    public PhongBanAdapter(List<PhongBan> list, Context context, ActionController controller) {
        mListPhongBan = list;
        mContext = context;
        mActionController = controller;
    }

    @NonNull
    @Override
    public PhongBanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_phong_ban, parent, false);
        return new PhongBanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongBanViewHolder holder, int position) {
        PhongBan phongBan = mListPhongBan.get(position);
        String tenPhongBan = phongBan.getMaPhongBan() + " - " + phongBan.getTenPhongBan()
                + ((phongBan.getSoLuongNhanVien()>0)? "(Có " + phongBan.getSoLuongNhanVien() + " NV)":"(Chưa có NV)");
        String textTruongPhong = (TextUtils.isEmpty(phongBan.getTenTruongPhong()))?
                "Trưởng phòng: [Chưa có]": "Trưởng phòng: ["+phongBan.getTenTruongPhong()+"]";
        String textPhoPhong = (TextUtils.isEmpty(phongBan.getTenTruongPhong()))?
                "Phó phòng: [Chưa có]": "Phó phòng: ";
        String dsPhoPhong = "";
        for (int i = 0; i < phongBan.getDanhSachPhoPhong().size(); i++) {
            dsPhoPhong += (i+1) + ". " + phongBan.getDanhSachPhoPhong().get(i) + "\n";
        }

        holder.phongBanTextView.setText(tenPhongBan);
        holder.phoPhongListView.setText(dsPhoPhong);
        holder.phoPhongTextView.setText(textPhoPhong);
        holder.truongPhongTextView.setText(textTruongPhong);
    }

    @Override
    public int getItemCount() {
        return mListPhongBan.size();
    }

    class PhongBanViewHolder extends RecyclerView.ViewHolder {
        TextView phongBanTextView;
        TextView truongPhongTextView;
        TextView phoPhongTextView;
        TextView phoPhongListView;

        public PhongBanViewHolder(@NonNull View itemView) {
            super(itemView);
            phongBanTextView = itemView.findViewById(R.id.tv_tenphongban);
            truongPhongTextView = itemView.findViewById(R.id.tv_tentruongphong);
            phoPhongTextView = itemView.findViewById(R.id.tv_tenphophong);
            phoPhongListView = itemView.findViewById(R.id.tv_dsphophong);

            itemView.setOnLongClickListener(view -> {
                Dialog dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog_menu_control);
                TextView t1 = dialog.findViewById(R.id.tv_themnhanvien);
                TextView t2 = dialog.findViewById(R.id.tv_danhsachhanvien);
                TextView t3 = dialog.findViewById(R.id.tv_laptp);
                TextView t4 = dialog.findViewById(R.id.tv_xoa);

                t1.setOnClickListener(view1 -> {
                    mActionController.themNhanVien(getAdapterPosition());
                    dialog.cancel();
                });
                t2.setOnClickListener(view1 -> {
                    mActionController.xemDanhSachNhanVien(getAdapterPosition());
                    dialog.cancel();
                });
                t3.setOnClickListener(view1 -> {
                    mActionController.lapTruongPhong(getAdapterPosition());
                    dialog.cancel();
                });
                t4.setOnClickListener(view1 -> {
                    mActionController.xoa(getAdapterPosition());
                    dialog.cancel();
                });

                dialog.show();
                return true;
            });
        }
    }
}
