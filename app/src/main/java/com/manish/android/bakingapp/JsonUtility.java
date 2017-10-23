package com.manish.android.bakingapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by emkayx on 17-10-2017.
 */

public class JsonUtility {


    public static String jsonLoader(Context context) {
        String jsonString = null;
        try {
            InputStream is = context.getAssets().open("baking.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;

    }


    public static ArrayList<Recipe> jsonContentExtraction(Context context) {
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        try {
            JSONArray recipesArray = new JSONArray(jsonLoader(context));


            for (int i = 0; i < recipesArray.length(); i++) {
                JSONObject recipeObject = recipesArray.getJSONObject(i);

                int id = recipeObject.getInt("id");
                String recipeName = recipeObject.getString("name");
                int servings = recipeObject.getInt("servings");
                //  Bitmap recipeImage = recipeObject.getString("image");


                //getting the ingredient's details from the ingredients array
                ArrayList<Ingredient> ingredientsList = new ArrayList<>();
                JSONArray recipeIngredients = recipeObject.getJSONArray("ingredients");
                for (int j = 0; j < recipeIngredients.length(); j++) {
                    JSONObject singleIngredient = (JSONObject) recipeIngredients.get(j);
                    int quantity = singleIngredient.getInt("quantity");
                    String measure = singleIngredient.getString("measure");
                    String ingredientName = singleIngredient.getString("ingredient");

                    ingredientsList.add(new Ingredient(quantity, measure, ingredientName));
                }

                //getting the steps array content
                ArrayList<Step> stepsList = new ArrayList<>();
                JSONArray recipeSteps = recipeObject.getJSONArray("steps");
                for (int j = 0; j < recipeSteps.length(); j++) {
                    JSONObject singleStep = (JSONObject) recipeSteps.get(j);
                    int stepId = singleStep.getInt("id");
                    String stepShortDescription = singleStep.getString("shortDescription");
                    String stepDescription = singleStep.getString("description");
                    String videoURL = singleStep.getString("videoURL");
                    String thumbnailURL = singleStep.getString("thumbnailURL");
                    stepsList.add(new Step(stepId, stepShortDescription, stepDescription, videoURL, thumbnailURL));
                }

                recipeArrayList.add(new Recipe(id, recipeName, ingredientsList, stepsList, servings));

            }


        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return recipeArrayList;
    }
}
