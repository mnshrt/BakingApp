package com.manish.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class RecipeDetailFragment extends Fragment implements RecipeDetailAdapter.StepListItemClickListener {


private CardView ingredientsCardView;
   private TextView ingredientstextView;
   // private ArrayList<Step> mStepList;
    Recipe recipeClicked;


    public void setRecipeClicked(Recipe recipe) {
        recipeClicked = recipe;
    }


    public RecipeDetailFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        //load the saved state if there is one
   /*     if(savedInstanceState!=null){

        }*/

       View rootView = inflater.inflate(R.layout.recipe_detail_fragment,container,false);

        // get the reference to the textview

        ingredientsCardView = rootView.findViewById(R.id.ingredients_cv);
        ingredientsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // create a parcelable bundle to pass the recipe object
                Intent intent=  new Intent(getContext(),IngredientsActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable("recipeParcel",recipeClicked);
                intent.putExtra("recipeSelected",bundle1);
                startActivity(intent);
            }
        });




        // 1. get a reference to recyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.recipe_details_rv);

        // 2. set layoutManger
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //change the focus to the Instructions cardview

        recyclerView.setFocusable(false);



        // 3. create an adapter
        RecipeDetailAdapter mAdapter = new RecipeDetailAdapter(recipeClicked.getRecipeMakingSteps(),this,getContext());
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);


        return rootView;
    }

  
    @Override
    public void onItemClick( int pos) {
        Toast.makeText(getContext(),"the clicked item id: "+ pos, Toast.LENGTH_SHORT).show();
    }
}
