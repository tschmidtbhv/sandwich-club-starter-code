package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject("name");

            String mainName = name.getString("mainName");

            JSONArray alsoKnownAsJSON = name.getJSONArray("alsoKnownAs");

            List<String> alsoKnownAs = loopJSONArray(alsoKnownAsJSON);

            String placeOfOrigin = sandwich.getString("placeOfOrigin");
            String description = sandwich.getString("description");
            String image = sandwich.getString("image");

            JSONArray ingredientsJSON = sandwich.getJSONArray("ingredients");
            List<String> ingredients = loopJSONArray(ingredientsJSON);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<String> loopJSONArray(JSONArray jsonArray) throws JSONException {

        List<String> items = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String currentIngredient = (String) jsonArray.get(i);
            items.add(currentIngredient);
        }

        return items;
    }
}
