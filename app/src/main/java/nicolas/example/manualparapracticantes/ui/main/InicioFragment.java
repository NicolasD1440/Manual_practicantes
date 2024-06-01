package nicolas.example.manualparapracticantes.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import nicolas.example.manualparapracticantes.Comunicacion;
import nicolas.example.manualparapracticantes.R;

public class InicioFragment extends Fragment {



public InicioFragment(){

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mimenu =  inflater.inflate(R.layout.fragment_inicio, container, false);

        ImageView modulo1 = (ImageView) mimenu.findViewById(R.id.modulo1);
        ImageView modulo2 = (ImageView) mimenu.findViewById(R.id.modulo2);
        ImageView modulo3 = (ImageView) mimenu.findViewById(R.id.modulo3);
        ImageView modulo4 = (ImageView) mimenu.findViewById(R.id.modulo4);
        ImageView modulo5 = (ImageView) mimenu.findViewById(R.id.modulo5);
        ImageView modulo6 = (ImageView) mimenu.findViewById(R.id.modulo6);
        ImageView modulo7 = (ImageView) mimenu.findViewById(R.id.modulo7);
        ImageView modulo8 = (ImageView) mimenu.findViewById(R.id.modulo8);
        ImageView intro = (ImageView) mimenu.findViewById(R.id.Intro);

        modulo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).menu1();

            }
        });

        modulo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).menu2();
            }
        });


       modulo3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Activity estaACtivity = getActivity();
          ((Comunicacion)estaACtivity).menu3();
           }
        });
       modulo4.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Activity estaACtivity = getActivity();
               ((Comunicacion)estaACtivity).menu4();
           }
       });
       modulo5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Activity estaACtivity = getActivity();
               ((Comunicacion)estaACtivity).menu5();
           }
       });
        modulo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).menu6();
            }
        });
        modulo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).menu7();
            }
        });
        modulo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).menu8();
            }
        });

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity estaACtivity = getActivity();
                ((Comunicacion)estaACtivity).menu9();
            }
        });
   return  mimenu;
    }



}