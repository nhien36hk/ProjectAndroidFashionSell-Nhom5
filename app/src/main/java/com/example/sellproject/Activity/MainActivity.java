    package com.example.sellproject.Activity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.LinearLayout;

    import androidx.activity.EdgeToEdge;
    import androidx.annotation.NonNull;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import com.example.sellproject.Adapter.SliderItems;
    import com.example.sellproject.R;
    import com.example.sellproject.databinding.ActivityMainBinding;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.ValueEventListener;

    import java.util.ArrayList;

    public class MainActivity extends BasicActivity {
        private ActivityMainBinding binding;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            EdgeToEdge.enable(this);
            setContentView(binding.getRoot());
            LinearLayout profileLayout;
            profileLayout = findViewById(R.id.profile);

            initBanner();
            profileLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentProfile = new Intent(MainActivity.this, ProfileActivity.class);
                    startActivity(intentProfile);
                }
            });
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
//            return null;
        }

        private void initBanner() {
            DatabaseReference myRef = database.getReference("Banner");
            binding.progressBarBanner.setVisibility(View.VISIBLE);
            ArrayList<SliderItems> items = new ArrayList<>();
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        for(DataSnapshot issue:snapshot.getChildren()){
                            items.add(issue.getValue(SliderItems.class));
                        }

                        banners(items);
                        binding.progressBarBanner.setVisibility(View.GONE);
                    }
                }



                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        private void banners(ArrayList<SliderItems> items) {

        }
    }