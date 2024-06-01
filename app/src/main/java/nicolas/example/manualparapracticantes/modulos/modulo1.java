package nicolas.example.manualparapracticantes.modulos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import nicolas.example.manualparapracticantes.MainActivity;
import nicolas.example.manualparapracticantes.R;

public class modulo1 extends AppCompatActivity  {
    //variables y arrays
    private  int id_answers[]={R.id.answer1,R.id.answer2,R.id.answer3};
    private int correcto;
    private int con_pregunta; //pregunta actual
    private boolean[] res_correctas;
    private String[] Preguntas;
    private int con=1;
    int index;
    //objetos de pantalla
    private ProgressBar progres;
    private TextView textPregunta, cora;
    private RadioGroup group;
    private Button btn,op;
    int Corazones = 0;
    ImageView cancelBtn;


    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo1);

        //Referencias
        textPregunta = (TextView) findViewById(R.id.txt);
        group =findViewById(R.id.respuestas);
        btn = findViewById(R.id.check);
        cora = findViewById(R.id.corazones);

        cancelBtn = findViewById(R.id.cancelar);
  cancelBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          retroseso();
      }
  });


        progres = findViewById(R.id.progressBar);
        Preguntas = getResources().getStringArray(R.array.todas_preguntas);//LLenar array de preguntas
        res_correctas = new boolean[Preguntas.length];


        con_pregunta = 0;//pregunta actual se empieza en la posicion 0
        progres.setMax(Preguntas.length);
        MostrarPregunta();




        //instancias firebase
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Persona");



        //Acccion del boton

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id= group.getCheckedRadioButtonId();//traer el id del Radio button
                //los id`s tiene numeros muy grandes
                index = -1;// si es -1 significa que no hemos seleccionado nada
                for (int i = 0; i < id_answers.length; i++) {// tomamos la posicion de la opcion
                    if (id_answers[i] == id){ //si el id se encuantra en nuetro array de opciones guardamos la posicion de la opcion
                        index = i;//guardar la posicion de la opcion seleccionada

                    }

                }
                if (index == -1) {
                    Toast.makeText(getApplicationContext(), "Por favor selecciona una opcion de respuesta", Toast.LENGTH_SHORT).show();

                }else{
                    progres.setProgress(con);
                    con++;

                    if (index == correcto) { //como ya definimos cual opcion tiene el * lo que hacemos es comprobar si la posicion de la opcion seleccionada
                        // es igual a la posicion de la opcion correcta si es correcto lanzamos un mensaje de correcto
                        mp = MediaPlayer.create(modulo1.this, R.raw.check);
                        mp.start();

                        Corazones ++;
                        cora.setText("" + Corazones);
                    }else {
                        mp = MediaPlayer.create(modulo1.this, R.raw.error);
                        mp.start();
                    }


                    res_correctas[con_pregunta] = (index == correcto);

                    if (con_pregunta < Preguntas.length -1) {
                        con_pregunta++;
                        MostrarPregunta();
                    }else {
                        int correctas = 0, Incorrectas=0;
                        for (boolean b: res_correctas ) {
                            if (b) correctas++;
                            else Incorrectas++;
                        }
                        try {
                            puntos("puntos",correctas);
                        }catch (NullPointerException exception){
                            Toast.makeText(getApplicationContext(), "Inicia sesion o registrate", Toast.LENGTH_SHORT).show();
                        }



                        String resultado = String.format("Correctas: %d  ---- Incorrectas: %d", correctas, Incorrectas);
                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }

    private void MostrarPregunta() {

        String q = Preguntas[con_pregunta];
        String[] partes = q.split(";");
        group.clearCheck();

        textPregunta.setText(partes[0]);

        for (int i = 0; i < id_answers.length; i++) {
            RadioButton rb = findViewById(id_answers[i]);
            String res = partes[i+1]; //guardamos una opcion en cada itaracion del ciclo, para posteriormente comprobar
            // si tiene un *
            //ejemplo --> iteracion 1: res = opcion1; iteracion 2: res = *opcion2; iteracion 3: res = opcion3
            if (res.charAt(0) == '*') {// charAt nos ayuda a identificar cual opcion tiene el * para saber si es correcta
                correcto = i; //guadamos la posicion de la respuesta correcta
                res = res.substring(1);
            }
            rb.setText(res);// ponemos el texto de la opcion
        }

        if (con_pregunta == Preguntas.length-1) {
            btn.setText(R.string.Terminar);
        }
    }
    public  void puntos(String key, int correcto)throws NullPointerException{

        HashMap<String,Object> hashMap  = new HashMap<>();
        hashMap.put(key, correcto);


            databaseReference.child(user.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });





    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.salir_quiz)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(modulo1.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    public void retroseso(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.salir_quiz)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(modulo1.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }


}