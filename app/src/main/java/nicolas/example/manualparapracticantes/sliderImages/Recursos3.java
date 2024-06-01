package nicolas.example.manualparapracticantes.sliderImages;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import nicolas.example.manualparapracticantes.MainActivity;
import nicolas.example.manualparapracticantes.R;
import nicolas.example.manualparapracticantes.extras.SliderAdapter;

public class Recursos3 extends AppCompatActivity {
    SliderView sliderView;
    int[] images = {
            R.drawable.img22,
            R.drawable.img23,
            R.drawable.img24,
            R.drawable.img25,
            R.drawable.img26,
           };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recursos1);
//boton de regresar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        //sliderView.startAutoCycle();



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
}