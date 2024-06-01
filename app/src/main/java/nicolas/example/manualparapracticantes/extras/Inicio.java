package nicolas.example.manualparapracticantes.extras;

import static android.content.ContentValues.TAG;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import nicolas.example.manualparapracticantes.MainActivity;
import nicolas.example.manualparapracticantes.R;
import nicolas.example.manualparapracticantes.ui.main.InfomacionFragment;

public class Inicio extends AppCompatActivity {
    static final int RC_SIGN_IN = 1;
     public static int con;
    Persona persona = new Persona();
    EditText correo,contraseña;
    Button btn;
    ImageView google;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    InfomacionFragment fragInfo = new InfomacionFragment();
    private String email;
    private String password;

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

 //boton de regresar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#b511ec")));



        correo = findViewById(R.id.Correo);
        contraseña = findViewById(R.id.Contraseña);
        btn = findViewById(R.id.inciar);
        google = findViewById(R.id.google);

        //boton de iniciar
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             email = correo.getText().toString();
             password = contraseña.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {

                    loginUser();
                }else{
                    Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });


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
                editor.putString("Inicio","Google");
                editor.commit();

                signIn();
            }
        });
        // Inicializar Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        inicializarFireBase();
    }

    private void loginUser() {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("Metodo2","Correo");
                    editor.putString("Visibilidad2","Iniciado");
                    editor.commit();

                    startActivity(new Intent(Inicio.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Intente iniciar sesion con google o verifique sus datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    private String getString(String s) {
        return s;
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
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
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
                            String id_user = firebaseAuth.getCurrentUser().getUid();
                            databaseReference.child("Persona").child(id_user).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {

                                        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("NombreGoogle",snapshot.child("nombre").getValue().toString());
                                        editor.putString("CorreoGoogle",snapshot.child("correo").getValue().toString());
                                        editor.putString("IdGoogle",snapshot.child("id").getValue().toString());
                                        editor.commit();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

//Iniciar DASHBOARD u otra actividad luego del SigIn Exitoso
                            Intent dashboardActivity = new Intent(Inicio.this, MainActivity.class);
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
        SharedPreferences prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        String name = prefs.getString("Metodo", "ninguno");
        if (name.equals("Google")) {
            if(user!=null){ //si no es null el usuario ya esta logueado
                //mover al usuario al dashboard
                Toast.makeText(Inicio.this, "Ya estas has iniciado sesion", Toast.LENGTH_SHORT).show();
                Intent dashboardActivity = new Intent(Inicio.this, MainActivity.class);
                startActivity(dashboardActivity);
            }
        }

        super.onStart();
    }

    private void inicializarFireBase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }




}