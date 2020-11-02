package com.example.myfrags;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfrags2.FragsData;

public class Fragment2 extends Fragment {

    //1.
    private FragsData fragsData;

    //2.
    private TextView text;

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
        text = (TextView) view.findViewById(R.id.current);
        Button button = (Button) view.findViewById(R.id.button_increase);

        //2.
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        //3.
        Observer<Integer> numberObserver = new Observer<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Integer newInteger) {

                text.setText(newInteger.toString());
            }
        };

        //4.
        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        //5.
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          Integer i = fragsData.counter.getValue();
                                          fragsData.counter.setValue(++i);
                                      }
                                  }
        );

        return view;
    }
}