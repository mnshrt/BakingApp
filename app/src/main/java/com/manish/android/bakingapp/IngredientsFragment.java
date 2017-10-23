package com.manish.android.bakingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class IngredientsFragment extends Fragment {
private Recipe recipe;
    TextView ingredientsTextView;

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredients_fragment,container,false);

        ingredientsTextView= rootView.findViewById(R.id.ingredients_tv);
        List<Ingredient> ingredientList = recipe.getIngredientArrayList();

        for(Ingredient ingredient:ingredientList){
           ingredientsTextView.append(ingredient.getIngredientName()+"("+ingredient.getQuantity()+" "+ingredient.getMeasure()+")" +"\n");

        }


        return rootView;
    }

}
