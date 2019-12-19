package com.example.qunlnhns.ui.frag1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qunlnhns.R;
import com.example.qunlnhns.activities.mainAcitivity.MainActivity;
import com.example.qunlnhns.db.DbEmployeeManager;
import com.example.qunlnhns.model.NhanVien;

import java.util.ArrayList;


public class EmployeeRecyclerAdapter extends RecyclerView.Adapter<EmployeeRecyclerAdapter.ViewHolder>{
    private static final String TAG = "dung_test";

    private ArrayList<NhanVien> nhanVienList;
    private Context context;

    EmployeeRecyclerAdapter(ArrayList<NhanVien> nhanVienList) {
        this.nhanVienList = nhanVienList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nhan_vien_rv, parent, false);
        if(context == null) context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NhanVien nhanVien = nhanVienList.get(position);

        holder.tvHoTen.setText(nhanVien.getHoTen());
        holder.tvChucVu.setText(nhanVien.getChucVu());
        holder.tvDuAn.setText(nhanVien.getDuAn());
        holder.tvPhongBan.setText(nhanVien.getPhongBan());
    }

    @Override
    public int getItemCount() {
        return nhanVienList.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvHoTen, tvChucVu, tvDuAn, tvPhongBan;
        ImageView delete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHoTen = itemView.findViewById(R.id.employee_name);
            tvChucVu = itemView.findViewById(R.id.employee_chuc_vu);
            tvDuAn = itemView.findViewById(R.id.employee_du_an);
            tvPhongBan = itemView.findViewById(R.id.employee_phong_ban);

            delete = itemView.findViewById(R.id.delete_employee);

            itemView.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item) {
                Log.d(TAG, "onClick: item." + getAdapterPosition());

                // add frag detail

                Bundle bundle = new Bundle();

                ShowDetailEmployee showDetailEmployee = new ShowDetailEmployee();
                NhanVien nhanVien = nhanVienList.get(getAdapterPosition());
                bundle.putSerializable("passedNhanVien", nhanVien);
                showDetailEmployee.setArguments(bundle);

                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, showDetailEmployee)
                            .addToBackStack(null)
                            .commit();
                }
            }

            if(v.getId() == R.id.delete_employee){
                // delete this position in Rv, delete employee in db
                Log.d(TAG, "onClick: click item delete.");
                NhanVien nhanVien = nhanVienList.get(getAdapterPosition());

                DbEmployeeManager.getInstance(context).deleteEmployee(nhanVien.getMaNV());

                nhanVienList.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            }
        }
    }

    void updateList(ArrayList<NhanVien> nhanVienList){
        this.nhanVienList = nhanVienList;
        notifyDataSetChanged();
    }
}
