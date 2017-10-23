package com.manish.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class IngredientsActivity extends AppCompatActivity {
private Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients_activity);

        // get the recipe object from the bundle
        Intent intent = getIntent();
        if(intent.hasExtra("recipeSelected")){
            recipe = intent.getBundleExtra("recipeSelected").getParcelable("recipeParcel");
        }
        IngredientsFragment ingredientsFragment = new IngredientsFragment();
        ingredientsFragment.setRecipe(recipe);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.ingredients_container,ingredientsFragment)
        .commit();
    }
}
