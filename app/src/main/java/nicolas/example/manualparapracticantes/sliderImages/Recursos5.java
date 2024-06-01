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

public class Recursos5 extends AppCompatActivity {
    SliderView sliderView;
    int[] images = {
            R.drawable.img32,
            R.drawable.img33,
            R.drawable.img34,
            R.drawable.img35,
            R.drawable.img36,
            R.drawable.img37,
            R.drawable.img38,
            R.drawable.img39,
            R.drawable.img40,
            R.drawable.img41,
            R.drawable.img42,
            R.drawable.img43,
            R.drawable.img44
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