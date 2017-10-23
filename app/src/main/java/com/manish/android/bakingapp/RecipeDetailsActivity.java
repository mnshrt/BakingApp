package com.manish.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RecipeDetailsActivity extends AppCompatActivity  {

    TextView testTextView;
    Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_details_acivity);


        Intent intent = getIntent();
        if(intent.hasExtra("recipeClicked")){
            recipe = intent.getBundleExtra("recipeClicked").getParcelable("recipe");
        }



   /*     testTextView = (TextView) findViewById(R.id.testTextView1);

        testTextView.setText(recipe.getName());*/
        // creating the recipe detail fragment
        RecipeDetailFragment recipeDetailFragment = new RecipeDetailFragment();
        recipeDetailFragment.setRecipeClicked(recipe);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.step_container,recipeDetailFragment)
                .commit();

    }




}
