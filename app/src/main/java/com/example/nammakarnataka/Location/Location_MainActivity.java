package com.example.nammakarnataka.Location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nammakarnataka.R;
import com.example.nammakarnataka.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class Location_MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_location_main);
        int[] imageList = {R.drawable.images, R.drawable.kailshtemple, R.drawable.adikumbeshwara, R.drawable.dwaraka, R.drawable.cave, R.drawable.sun, R.drawable.halasuru,R.drawable.airavateshwara,R.drawable.brahadeshwara,R.drawable.lingaraj};
        int[] ingredientList = {R.string.pastaIngredients, R.string.maggiIngredients,R.string.cakeIngredients,R.string.pancakeIngredients,R.string.pizzaIngredients, R.string.burgerIngredients, R.string.friesIngredients,R.string.Airavatesvara,R.string.Brahadeshwawa,R.string.Lingaraj};

        int[] descList = {R.string.pastaDesc, R.string.maggieDesc, R.string.cakeDesc,R.string.pancakeDesc,R.string.pizzaDesc, R.string.burgerDesc, R.string.friesDesc,R.string.Airavatesvara_dec,R.string.Brahadeshwawa_dec,R.string.Lingaraj_dec};
        String[] nameList = {"meenakshi temple", "kailasa temple", "adi kumbeshwara temple", "Dwarkadhish Temple", "Cave Temple","Sun temple", "Halasuru someshwara Temple","Airavatesvara Temple","Brahadeshwawa temple","Lingaraj Temple"};
        String[] timeList = {"2500+ years old", "1200+ Years old", "1300+ years old","2500+ years old", "1300+ years old", "800+ years old", "800+ years old","800+ years old","1000+ years old","1000+ years old"};
        for (int i = 0; i < imageList.length; i++){
            listData = new ListData(nameList[i], timeList[i], ingredientList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(Location_MainActivity.this, dataArrayList);
        listView=findViewById(R.id.listview_location);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Location_MainActivity.this, DetailedActivity.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("time", timeList[i]);
                intent.putExtra("ingredients", ingredientList[i]);
                intent.putExtra("desc", descList[i]);
                intent.putExtra("image", imageList[i]);
                startActivity(intent);

            }
        });

        findViewById(R.id.back_button_location).setOnClickListener((v)->{
            finish();
        });

    }
}