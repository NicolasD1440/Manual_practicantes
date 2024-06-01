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

public class Recursos6 extends AppCompatActivity {
    SliderView sliderView;
    int[] images = {
            R.drawable.img45,
            R.drawable.img46,
            R.drawable.img47,
            R.drawable.img48,
            R.drawable.img49,
            R.drawable.img50,
            R.drawable.img51,
            R.drawable.img52,
            R.drawable.img53,
            R.drawable.img54,
            R.drawable.img55,
            R.drawable.img56,
            R.drawable.img57,
            R.drawable.img58,
            R.drawable.img59,
            R.drawable.img60,
            R.drawable.img61,
            R.drawable.img62,
            R.drawable.img63,
            R.drawable.img64,
            R.drawable.img65,
            R.drawable.img66
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