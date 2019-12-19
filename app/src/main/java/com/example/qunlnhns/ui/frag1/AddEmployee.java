package com.example.qunlnhns.ui.frag1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.qunlnhns.R;
import com.example.qunlnhns.activities.mainAcitivity.MainActivity;
import com.example.qunlnhns.db.DbEmployeeManager;
import com.example.qunlnhns.db.DbPbManager;
import com.example.qunlnhns.model.NhanVien;

import java.util.ArrayList;

public class AddEmployee extends Fragment {

    private TextView save, luong;
    private ImageView cancel;
    private EditText hoten, maNV, chucVu, phongBan, duAn, gioiTinh, ngaySinh, diaChi, sdt, email, heSoLuong;
    private Spinner spinnerCV, spinnerDA, spinnerPB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_employee, container, false);

        if(getContext() == null){
            return view;
        }

        initView(view);

        // spinner

        ArrayList<String> listCV = DbPbManager.getInstance(getContext()).getStringsCV();
        ArrayList<String> listDA = DbPbManager.getInstance(getContext()).getStringsDA();
        ArrayList<String> listPB = DbPbManager.getInstance(getContext()).getStringsPB();

        ArrayAdapter<String> adapterCV = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listCV);
        adapterCV.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCV.setAdapter(adapterCV);
        spinnerCV.setVisibility(View.VISIBLE);

        ArrayAdapter<String> adapterPB = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listPB);
        adapterPB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPB.setAdapter(adapterPB);
        spinnerPB.setVisibility(View.VISIBLE);

        ArrayAdapter<String> adapterDA = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listDA);
        adapterDA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDA.setAdapter(adapterDA);
        spinnerDA.setVisibility(View.VISIBLE);

        luong.setText("Lương: ");


        heSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // set text for "luong"

                if(s.toString().isEmpty()) {
                    return;
                }

                if( Integer.valueOf(s.toString()) > 6 || Integer.valueOf(s.toString()) < 1){
                    luong.setText("Hệ số lương không hợp lệ");
                    return;
                }

                luong.setText("Lương: "+ Integer.valueOf(s.toString()) * 1400000);
            }
        });

        // button

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save change to db

                if(DbEmployeeManager.getInstance(getContext()).isDupEmployee(maNV.getText().toString())){
                    Toast.makeText(getContext(), "Trùng mã nhân viên, mời nhập lại", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(hoten.getText().toString().trim().isEmpty()){
                    Toast.makeText(getContext(), "Cần nhập tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(1 > Integer.valueOf(heSoLuong.getText().toString()) || 6 < Integer.valueOf(heSoLuong.getText().toString())){
                    Toast.makeText(getContext(), "Hệ số lương sai, mời nhập lại", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(gioiTinh.getText().toString().trim().isEmpty() ||
                        ngaySinh.getText().toString().trim().isEmpty() ||
                        diaChi.getText().toString().trim().isEmpty() ||
                        sdt.getText().toString().trim().isEmpty() ||
                        email.getText().toString().trim().isEmpty() ||
                        heSoLuong.getText().toString().trim().isEmpty()
                ){
                    Toast.makeText(getContext(), "Cần nhập đủ các trường", Toast.LENGTH_SHORT).show();
                    return;
                }

                NhanVien newNhanVien = new NhanVien(maNV.getText().toString()
                        , hoten.getText().toString()
                        , gioiTinh.getText().toString()
                        , ngaySinh.getText().toString()
                        , diaChi.getText().toString()
                        , Integer.valueOf(sdt.getText().toString())
                        , email.getText().toString()
                        , (String) spinnerPB.getSelectedItem()
                        , (String) spinnerCV.getSelectedItem()
                        , (String) spinnerDA.getSelectedItem()
                        , Integer.valueOf( heSoLuong.getText().toString() ));

                DbEmployeeManager.getInstance(getContext()).addEmployee(newNhanVien);

                // back to frag 1
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null){
                    mainActivity.getSupportFragmentManager().popBackStack();
                }

                Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back to frag 1
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null){
                    mainActivity.getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    private void initView(View view) {
        hoten = view.findViewById(R.id.ho_ten);
        maNV = view.findViewById(R.id.ma_nv);
        gioiTinh = view.findViewById(R.id.gioi_tinh);
        ngaySinh = view.findViewById(R.id.ngay_sinh);
        diaChi = view.findViewById(R.id.dia_chi);
        sdt = view.findViewById(R.id.sdt);
        email = view.findViewById(R.id.email);
        heSoLuong = view.findViewById(R.id.he_so_luong);

        spinnerCV = view.findViewById(R.id.spinner_cv);
        spinnerDA = view.findViewById(R.id.spinner_da);
        spinnerPB = view.findViewById(R.id.spinner_pb);

        luong = view.findViewById(R.id.luong);

        save = view.findViewById(R.id.save_employ);
        cancel = view.findViewById(R.id.close_employee);
    }
}