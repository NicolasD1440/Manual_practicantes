package nicolas.example.manualparapracticantes.extras;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nicolas.example.manualparapracticantes.MainActivity;
import nicolas.example.manualparapracticantes.R;

public class Registro extends AppCompatActivity {
    static final int RC_SIGN_IN = 1;
    Persona persona = new Persona();
    EditText correo,contraseña,nombre;
    Button btn, btn1;
    ImageView google;
    public String con;
    //Intancias firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //boton de regresar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b511ec")));


        correo = findViewById(R.id.Correo);
        contraseña = findViewById(R.id.Contraseña);
        nombre = findViewById(R.id.nombre);
        btn = findViewById(R.id.inciar);
        google = findViewById(R.id.google);





        // Configurar Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_ids))
                .requestEmail()
                .build();
        // Crear un GoogleSignInClient con las opciones especificadas por gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Metodo","Google");
                editor.commit();
                signIn();
            }
        });
        // Inicializar Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        //Acciones boton de registro
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Visibilidad1","Registrado");
                editor.commit();

                verificar();

            }
        });


        inicializarFireBase();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        //Resultado devuelto al iniciar el Intent de GoogleSignInApi.getSignInIntent (...);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if(task.isSuccessful()){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = task.getResult(ApiException.class);
                    firebaseAuthWithGoogle(account.getIdToken());
                } catch (ApiException e) {
                    // Google Sign In fallido, actualizar GUI
                    Log.w(TAG, "Google sign in failed", e);
                }
            }else{
                Log.d(TAG, "Error, login no exitoso:" + task.getException().toString());
                Toast.makeText(this, "Ocurrio un error. "+task.getException().toString(),
                        Toast.LENGTH_LONG).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            //FirebaseUser user = mAuth.getCurrentUser();
//Iniciar DASHBOARD u otra actividad luego del SigIn Exitoso
                            Intent dashboardActivity = new Intent(Registro.this, MainActivity.class);
                            startActivity(dashboardActivity);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }

    private void signIn() {
        Intent signInIntent =googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onStart() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){ //si no es null el usuario ya esta logueado
            //mover al usuario al dashboard
            Toast.makeText(Registro.this, "Ya estas registrado", Toast.LENGTH_SHORT).show();
            Intent dashboardActivity = new Intent(Registro.this, MainActivity.class);
            startActivity(dashboardActivity);
        }
        super.onStart();
    }

    private void inicializarFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void verificar(){
        String NombreV = nombre.getText().toString();
        String correoV = correo.getText().toString();
        String contraseñaV = contraseña.getText().toString();

        if (!NombreV.isEmpty() && !correoV.isEmpty() && !contraseñaV.isEmpty()) {
            if (contraseñaV.length() >=6) {
                RegistroUsuario();

            }else{
                Toast.makeText(this, "La contraseña debe tener mas de 6 carcateres", Toast.LENGTH_SHORT).show();
            }
        }else{
            correo.setError("Required");
            nombre.setError("Required");
            contraseña.setError("Required");
        }
    }

    private void RegistroUsuario() {
        enviar();
        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String email = prefs.getString("Correo", "admin@gmail.com");
        String pass= prefs.getString("Contraseña", "12345");
        Log.i("prefrerencias", email);
        Log.i("prefrerencias", pass);

       firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()) {

                   persona.setID(firebaseAuth.getCurrentUser().getUid());
                   persona.setCorreo(correo.getText().toString());
                   persona.setContraseña(contraseña.getText().toString());
                   persona.setNombre(nombre.getText().toString());
                   persona.setPuntos(0);
                   persona.setPuntos2(0);
                   persona.setPuntos3(0);
                   persona.setPuntos4(0);
                   persona.setPuntos5(0);
                   persona.setPuntos6(0);
                   persona.setPuntos7(0);
                   persona.setPuntos8(0);

                   databaseReference.child("Persona").child(persona.getID()).setValue(persona).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task2) {
                           if (task2.isSuccessful()) {

                               startActivity(new Intent(Registro.this, MainActivity.class));
                               finish();
                           }else{
                               Toast.makeText(getApplicationContext(), "Nose pudo registrar los datos", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }else{
                   Toast.makeText(getApplicationContext(), "Este correo electronico ya ha sido usado por favor pruebe otro ", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }

    public  void enviar(){
        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Correo", correo.getText().toString());
        editor.putString("Contraseña", contraseña.getText().toString());
        editor.commit();


    }



}