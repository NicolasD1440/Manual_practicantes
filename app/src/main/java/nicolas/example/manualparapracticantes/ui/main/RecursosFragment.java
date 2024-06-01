package nicolas.example.manualparapracticantes.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;

import nicolas.example.manualparapracticantes.Comunicacion;
import nicolas.example.manualparapracticantes.ListAdapter;
import nicolas.example.manualparapracticantes.R;
import nicolas.example.manualparapracticantes.extras.ListElement;

public class RecursosFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_recursos, container, false);
        Button btn1 = v.findViewById(R.id.Rnivel1);
        Button btn2 = v.findViewById(R.id.Rnivel2);
        Button btn3 = v.findViewById(R.id.Rnivel3);
        Button btn4 = v.findViewById(R.id.Rnivel4);
        Button btn5 = v.findViewById(R.id.Rnivel5);
        Button btn6 = v.findViewById(R.id.Rnivel6);
        Button btn7 = v.findViewById(R.id.Rnivel7);
        Button btn8 = v.findViewById(R.id.Rnivel8);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos1();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos2();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos3();
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos4();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos5();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos6();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos7();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).recursos8();
            }
        });


        return v;

    }


}