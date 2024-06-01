package nicolas.example.manualparapracticantes.modulos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

import nicolas.example.manualparapracticantes.R;

public class intro extends AppCompatActivity {
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        fragmentManager = getSupportFragmentManager();
        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataForOnBoarding());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, paperOnboardingFragment);
        fragmentTransaction.commit();
    }

    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {
        PaperOnboardingPage src1 = new PaperOnboardingPage("Hotels", "all hotels and hoteles and all you want",
                Color.parseColor("#ffb174"),R.drawable.iniciar, R.drawable.copa);

        ArrayList<PaperOnboardingPage> elemnts  = new ArrayList<>();
            elemnts.add(src1);

            return elemnts;
    }
}