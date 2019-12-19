package com.example.qunlnhns.ui.frag1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qunlnhns.R;
import com.example.qunlnhns.activities.mainAcitivity.MainActivity;
import com.example.qunlnhns.db.DbEmployeeManager;
import com.example.qunlnhns.db.DbPbManager;
import com.example.qunlnhns.model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
public class Frag1 extends Fragment{

    private RecyclerView employeeRecyclerView;
    private ArrayList<NhanVien> nhanViens;
    private EmployeeRecyclerAdapter employeeRecyclerAdapter;
    private EditText searchNhanVien;
    private FloatingActionButton fab;

    private static final String TAG = "dung_test";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        initView(view);

        /* khởi tạo rv ban đầu gồm tất cả các nhân viên */
        nhanViens = DbEmployeeManager.getInstance(getContext()).getAllNhanVien();
        employeeRecyclerAdapter = new EmployeeRecyclerAdapter(nhanViens);
        employeeRecyclerView.setAdapter(employeeRecyclerAdapter);

        /* search */
        searchNhanVien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                nhanViens = DbEmployeeManager.getInstance(getContext()).getNhanVien(s.toString());
                employeeRecyclerAdapter.updateList(nhanViens);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                if(mainActivity != null){
                    mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AddEmployee())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });


        return view;
    }

    private void initView(View view) {
        searchNhanVien = view.findViewById(R.id.search_employee);
        employeeRecyclerView = view.findViewById(R.id.employees_rv);

        fab = view.findViewById(R.id.floatingActionButton);
    }
}
