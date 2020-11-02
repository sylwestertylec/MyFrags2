package com.example.myfrags;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.myfrags2.FragsData;

public class Fragment4 extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);
//1.
        final FragsData fragsData;
        Observer<Integer> numberObserver;

        //2.
        final EditText edit;
        TextWatcher textWatcher;
        final boolean[] turnOffWatcher = new boolean[1];
        //1.
        edit = view.findViewById(R.id.editTextNumber);

        //2.
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        //3.
        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer newInteger) {
                turnOffWatcher[0] = true;
                edit.setText(newInteger.toString());
            }
        };

        //4.
        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        //5.
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if(!turnOffWatcher[0]){

                    Integer i;
                    try{
                        i = Integer.parseInt( s.toString() );
                    } catch (NumberFormatException e){
                        i = fragsData.counter.getValue();
                    }
                    fragsData.counter.setValue(i);

                } else {
                    turnOffWatcher[0] = !turnOffWatcher[0];
                }
            }
        };

        //6.
        edit.addTextChangedListener(textWatcher);

        return view;
    }
}