package jp.ac.meijou.android.s221205057;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijou.android.s221205057.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s221205057.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {

    private ActivityMain3Binding binding;
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()){
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.result.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.result.setText("Result: Canceled");
                        break;
                    default:
                        binding.result.setText("Result: Unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Explicit.setOnClickListener(view ->{
            var intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });

        binding.Implicit.setOnClickListener(view ->{
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });

        binding.transmission.setOnClickListener(view -> {
            var intent = new Intent(this,SubActivity.class);
            intent.putExtra("text", binding.editTextText2.getText().toString());
            startActivity(intent);
        });

        binding.Action.setOnClickListener(view ->{
            var intent = new Intent(this, SubActivity.class);
            getActivityResult.launch(intent);
        });
    }
}