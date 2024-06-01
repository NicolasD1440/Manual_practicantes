package nicolas.example.manualparapracticantes.extras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
import java.util.Map;

import nicolas.example.manualparapracticantes.Comunicacion;
import nicolas.example.manualparapracticantes.R;

public class ejemplo extends AppCompatActivity {

    private FirebaseAuth mAuth;
    ImageView imagenUser;
    TextView id, puntos;
    TextView nomb;
    TextView corr;
    Button incio_S;
    Button reg;
    Button cerrar;
    ConstraintLayout layaut;
    Persona persona = new Persona();
    public String name3;
    public String name4;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo);
        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String name = prefs.getString("Metodo", "ninguno");
        String name2 = prefs.getString("Metodo2", "ninguno");
        name3 = prefs.getString("Visibilidad1", "ninguno");
        name4 = prefs.getString("Visibilidad2", "ninguno");
        String metodo = prefs.getString("Inicio", "ninguno");




        // Inflate the layout for this fragment
       /* Toast.makeText(getContext(), mipass, Toast.LENGTH_SHORT).show();
        Log.e("error25",name);*/


        id = findViewById(R.id.userId);
        corr = findViewById(R.id.userEmail);
        nomb = findViewById(R.id.userNombre);

        reg = findViewById(R.id.btnRegistro);
        cerrar = findViewById(R.id.btnCerrar);
        incio_S = findViewById(R.id.btnCerrar);
        puntos = findViewById(R.id.puntosQuiz);
        imagenUser = findViewById(R.id.userImagen);
        //Intancias firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //Glide.with(getContext()).load(R.drawable.iniciar).apply(RequestOptions.circleCropTransform()).into(imagenUser);
        if (name.equals("Google")) {
            //Configurar las gso para google signIn con el fin de luego desloguear de google
            gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();
            mGoogleSignInClient = GoogleSignIn.getClient(getApplicationContext(), gso);

            if (user != null) {
                // Name, email address, and profile photo Url
                nomb.setText(user.getDisplayName());
                corr.setText(user.getEmail());
                id.setText(user.getUid());

                firebaseDatabase.child("Persona").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String puntos1 =  snapshot.child("puntos").getValue().toString();
                            String  puntos2 =  snapshot.child("puntos2").getValue().toString();
                            String  puntos3 =  snapshot.child("puntos3").getValue().toString();
                            String  puntos4 =  snapshot.child("puntos4").getValue().toString();
                            String  puntos5 =  snapshot.child("puntos5").getValue().toString();
                            String  puntos6 =  snapshot.child("puntos6").getValue().toString();
                            String  puntos7 =  snapshot.child("puntos7").getValue().toString();
                            String puntos8 =  snapshot.child("puntos8").getValue().toString();

                            int total = (Integer.parseInt(puntos1)+Integer.parseInt(puntos2)+Integer.parseInt(puntos3)+Integer.parseInt(puntos4)
                                    +Integer.parseInt(puntos5)+Integer.parseInt(puntos6)+Integer.parseInt(puntos7)+Integer.parseInt(puntos8));

                            puntos.setText(String.valueOf(total));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                firebaseDatabase.child("Persona").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(getApplicationContext(), "ESTE CORREO YA ESTA RESGITRADO", Toast.LENGTH_SHORT).show();
                        }else{
                            String usuario =user.getDisplayName();
                            String Emailusuario=user.getEmail();
                            String idusuario=user.getUid();
                            Map<String, Object> datosUsuario = new HashMap<>();
                            datosUsuario.put("contraseña","123456");
                            datosUsuario.put("correo",Emailusuario);
                            datosUsuario.put("id",idusuario);
                            datosUsuario.put("nombre",usuario);
                            datosUsuario.put("puntos",0);
                            datosUsuario.put("puntos2",0);
                            datosUsuario.put("puntos3",0);
                            datosUsuario.put("puntos4",0);
                            datosUsuario.put("puntos5",0);
                            datosUsuario.put("puntos6",0);
                            datosUsuario.put("puntos7",0);
                            datosUsuario.put("puntos8",0);



                            firebaseDatabase.child("Persona").child(idusuario).setValue(datosUsuario);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                Glide.with(this).load(user.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(imagenUser);

                // Check if user's email is verified
                boolean emailVerified = user.isEmailVerified();

            }
        }
        if (metodo.equals("Google")) {
            if (user != null) {
                // Name, email address, and profile photo Url
                nomb.setText(user.getDisplayName());
                corr.setText(user.getEmail());
                id.setText(user.getUid());
                Glide.with(this).load(user.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(imagenUser);
                String id_user = mAuth.getCurrentUser().getUid();
                firebaseDatabase.child("Persona").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String puntos1 =  snapshot.child("puntos").getValue().toString();
                            String  puntos2 =  snapshot.child("puntos2").getValue().toString();
                            String  puntos3 =  snapshot.child("puntos3").getValue().toString();
                            String  puntos4 =  snapshot.child("puntos4").getValue().toString();
                            String  puntos5 =  snapshot.child("puntos5").getValue().toString();
                            String  puntos6 =  snapshot.child("puntos6").getValue().toString();
                            String  puntos7 =  snapshot.child("puntos7").getValue().toString();
                            String puntos8 =  snapshot.child("puntos8").getValue().toString();

                            int total = (Integer.parseInt(puntos1)+Integer.parseInt(puntos2)+Integer.parseInt(puntos3)+Integer.parseInt(puntos4)
                                    +Integer.parseInt(puntos5)+Integer.parseInt(puntos6)+Integer.parseInt(puntos7)+Integer.parseInt(puntos8));

                            puntos.setText(String.valueOf(total));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }


        if (name2.equals("Correo")) {
            getInformacion();
        }






        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear().apply();


                nomb.setText(R.string.nombre_del_usuario);
                corr.setText(R.string.correo_del_usuario);
                id.setText(R.string.id_usuario);
                puntos.setText("0");
                Glide.with(getApplicationContext()).load(R.drawable.iniciar).apply(RequestOptions.circleCropTransform()).into(imagenUser);

                //Cerrar sesion de firebase
                mAuth.signOut();
                //Cerrar sesión con google tambien: Google sign out
                if (name.equals("Google")) {
                    mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //Abrir MainActivity con SigIn button
                            if(task.isSuccessful()){
                               // Activity estaACtivity = getActivity();
                                //((Comunicacion)estaACtivity).deslogearse();
                            }else{
                                Toast.makeText(getApplicationContext(), "No se pudo cerrar sesión con google",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




            }
        });
        incio_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
    public void getInformacion(){
        String id_user = mAuth.getCurrentUser().getUid();
        firebaseDatabase.child("Persona").child(id_user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("nombre").getValue().toString();
                    String email = snapshot.child("correo").getValue().toString();
                    String ID = snapshot.child("id").getValue().toString();
                    corr.setText(email);
                    nomb.setText(name);
                    id.setText(ID);


                    String puntos1 =  snapshot.child("puntos").getValue().toString();
                    String  puntos2 =  snapshot.child("puntos2").getValue().toString();
                    String  puntos3 =  snapshot.child("puntos3").getValue().toString();
                    String  puntos4 =  snapshot.child("puntos4").getValue().toString();
                    String  puntos5 =  snapshot.child("puntos5").getValue().toString();
                    String  puntos6 =  snapshot.child("puntos6").getValue().toString();
                    String  puntos7 =  snapshot.child("puntos7").getValue().toString();
                    String puntos8 =  snapshot.child("puntos8").getValue().toString();

                    int total = (Integer.parseInt(puntos1)+Integer.parseInt(puntos2)+Integer.parseInt(puntos3)+Integer.parseInt(puntos4)
                            +Integer.parseInt(puntos5)+Integer.parseInt(puntos6)+Integer.parseInt(puntos7)+Integer.parseInt(puntos8));

                    puntos.setText(String.valueOf(total));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}