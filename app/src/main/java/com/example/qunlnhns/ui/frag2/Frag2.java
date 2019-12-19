package com.example.qunlnhns.ui.frag2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.qunlnhns.db.DbDAManager;
import com.example.qunlnhns.model.DuAn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Frag2 extends Fragment {

    private ArrayList<DuAn> duAnArrayList = new ArrayList<>();
    private EditText search_du_an;
    private RecyclerView duAnRv;
    private FloatingActionButton fab;
    private DuAnRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment2, container, false);

        initView(view);

        duAnArrayList = DbDAManager.getInstance(getContext()).getAllDuAn();
        adapter = new DuAnRecyclerAdapter(duAnArrayList);
        duAnRv.setAdapter(adapter);

        /*search */
        search_du_an.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // search here
                duAnArrayList = DbDAManager.getInstance(getContext()).getSearchDuAn(s.toString());
                adapter.updateArrayListDuAn(duAnArrayList);

            }
        });


        /*add some */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null){
                    mainActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AddDAFrag())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }

    private void initView(View view) {
        search_du_an = view.findViewById(R.id.search_du_an);
        duAnRv = view.findViewById(R.id.du_an_rv);
        fab =view.findViewById(R.id.floatingActionButton_du_an);
    }
}
