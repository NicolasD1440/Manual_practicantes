package nicolas.example.manualparapracticantes;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.MissingFormatArgumentException;

import nicolas.example.manualparapracticantes.extras.Inicio;
import nicolas.example.manualparapracticantes.extras.Registro;
import nicolas.example.manualparapracticantes.extras.ejemplo;
import nicolas.example.manualparapracticantes.modulos.intro;
import nicolas.example.manualparapracticantes.modulos.modulo1;
import nicolas.example.manualparapracticantes.modulos.modulo2;
import nicolas.example.manualparapracticantes.modulos.modulo3;
import nicolas.example.manualparapracticantes.modulos.modulo4;
import nicolas.example.manualparapracticantes.modulos.modulo5;
import nicolas.example.manualparapracticantes.modulos.modulo6;
import nicolas.example.manualparapracticantes.modulos.modulo7;
import nicolas.example.manualparapracticantes.modulos.modulo8;
import nicolas.example.manualparapracticantes.sliderImages.Recursos1;
import nicolas.example.manualparapracticantes.sliderImages.Recursos2;
import nicolas.example.manualparapracticantes.sliderImages.Recursos3;
import nicolas.example.manualparapracticantes.sliderImages.Recursos4;
import nicolas.example.manualparapracticantes.sliderImages.Recursos5;
import nicolas.example.manualparapracticantes.sliderImages.Recursos6;
import nicolas.example.manualparapracticantes.sliderImages.Recursos7;
import nicolas.example.manualparapracticantes.sliderImages.Recursos8;
import nicolas.example.manualparapracticantes.ui.main.SectionsPagerAdapter;
import nicolas.example.manualparapracticantes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Comunicacion {

    private ActivityMainBinding binding;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });





    }
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.salir_app)
                .setPositiveButton(R.string.si, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
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


    @Override
    public void deslogearse() {
        Intent mainActivity = new Intent(getApplicationContext(), Registro.class);
        startActivity(mainActivity);
        MainActivity.this.finish();
    }

    @Override
    public void iniciar() {
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
    }

    @Override
    public void registro() {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }

    @Override
    public void menu1() {
        Intent intent = new Intent(this, modulo1.class);
        startActivity(intent);


    }

    @Override
    public void menu2() {
        Intent intent = new Intent(this, modulo2.class);
        startActivity(intent);

    }

    @Override
    public void menu3() {
        Intent intent = new Intent(this, modulo3.class);
        startActivity(intent);

    }

    @Override
    public void menu4() {
        Intent intent = new Intent(this, modulo4.class);
        startActivity(intent);

    }

    @Override
    public void menu5() {
        Intent intent = new Intent(this, modulo5.class);
        startActivity(intent);

    }

    @Override
    public void menu6() {
        Intent intent = new Intent(this, modulo6.class);
        startActivity(intent);

    }

    @Override
    public void menu7() {
        Intent intent = new Intent(this, modulo7.class);
        startActivity(intent);

    }

    @Override
    public void menu8() {
       Intent intent = new Intent(this, modulo8.class);
        startActivity(intent);

    }

    @Override
    public void menu9() {
        Intent intent = new Intent(this, intro.class);
        startActivity(intent);

    }

    @Override
    public void recursos1() {
        Intent intent = new Intent(this, Recursos1.class);
        startActivity(intent);
    }

    @Override
    public void recursos2() {
        Intent intent = new Intent(this, Recursos2.class);
        startActivity(intent);
    }

    @Override
    public void recursos3() {
        Intent intent = new Intent(this, Recursos3.class);
        startActivity(intent);
    }

    @Override
    public void recursos4() {
        Intent intent = new Intent(this, Recursos4.class);
        startActivity(intent);
    }

    @Override
    public void recursos5() {
        Intent intent = new Intent(this, Recursos5.class);
        startActivity(intent);
    }

    @Override
    public void recursos6() {
        Intent intent = new Intent(this, Recursos6.class);
        startActivity(intent);
    }

    @Override
    public void recursos7() {
        Intent intent = new Intent(this, Recursos7.class);
        startActivity(intent);
    }

    @Override
    public void recursos8() {
        Intent intent = new Intent(this, Recursos8.class);
        startActivity(intent);
    }
}