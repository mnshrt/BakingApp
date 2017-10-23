package com.manish.android.bakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by emkayx on 20-10-2017.
 */

class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>  {
    private List<Recipe> recipeList;
    final private RecipeListItemClickListener mOnClickListener;



    //interface to respond to the clicks
    public interface RecipeListItemClickListener{
       void onRecipeListItemClick(int clickedItemIndex);
   }


    public RecipeListAdapter(List<Recipe> recipeList,RecipeListItemClickListener listener) {
        this.recipeList = recipeList;
        mOnClickListener = listener;
    }
    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem  = R.layout.recipe_single_item;
        boolean shouldAttachToParentImmediately = false;
        View view = LayoutInflater.from(context).inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);


        RecipeListViewHolder viewHolder = new RecipeListViewHolder(view);

        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {

        Recipe recipe = recipeList.get(position);
        String recipeName = recipe.getName();
        holder.recipeNameTextView.setText(recipeName);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    public class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected TextView recipeNameTextView;

       public RecipeListViewHolder(View itemView) {
           super(itemView);
           recipeNameTextView = itemView.findViewById(R.id.title);
           itemView.setOnClickListener(this);

       }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onRecipeListItemClick(clickedPosition);
        }
    }


}
