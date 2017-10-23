package com.manish.android.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by emkayx on 17-10-2017.
 */

public class Recipe implements Parcelable {
 int recipeId;
    String name;
    ArrayList<Ingredient> ingredientArrayList;
    ArrayList<Step> recipeMakingSteps;
    int servings;
//    Bitmap recipeImage;

    public Recipe(int id, String name, ArrayList<Ingredient> ingredientArrayList, ArrayList<Step> recipeMakingSteps, int servings) {
        this.recipeId = id;
        this.name = name;
        this.ingredientArrayList = ingredientArrayList;
        this.recipeMakingSteps = recipeMakingSteps;
        this.servings = servings;
       // this.recipeImage = recipeImage;
    }


    protected Recipe(Parcel in) {
        recipeId = in.readInt();
        name = in.readString();
        ingredientArrayList = in.createTypedArrayList(Ingredient.CREATOR);
        recipeMakingSteps = in.createTypedArrayList(Step.CREATOR);
        servings = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(recipeId);
        dest.writeString(name);
        dest.writeTypedList(ingredientArrayList);
        dest.writeTypedList(recipeMakingSteps);
        dest.writeInt(servings);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredientArrayList() {
        return ingredientArrayList;
    }

    public ArrayList<Step> getRecipeMakingSteps() {
        return recipeMakingSteps;
    }

    public int getServings() {
        return servings;
    }


}
