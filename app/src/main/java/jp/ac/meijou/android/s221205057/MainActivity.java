package jp.ac.meijou.android.s221205057;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import jp.ac.meijou.android.s221205057.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore = PrefDataStore.getInstance(this);

        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));

        binding.saveButton.setOnClickListener(view ->{
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
        });


    }
}
