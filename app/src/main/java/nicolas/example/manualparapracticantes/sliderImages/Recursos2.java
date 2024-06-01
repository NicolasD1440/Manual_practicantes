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

public class Recursos2 extends AppCompatActivity {
    SliderView sliderView;
    int[] images = {
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15,
            R.drawable.img16,
            R.drawable.img17,
            R.drawable.img18,
            R.drawable.img19,
            R.drawable.img20,
            R.drawable.img21};

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