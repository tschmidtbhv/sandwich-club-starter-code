package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String NAMEKEY = "name";
    private final static String MAINNAMEKEY = "mainName";
    private final static String ALSOKNOWNASKEY = "alsoKnownAs";
    private final static String PLACEOFORIGINKEY = "placeOfOrigin";
    private final static String DESCRIPTIONKEY = "description";
    private final static String IMAGE = "image";
    private final static String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject(NAMEKEY);

            String mainName = name.getString(MAINNAMEKEY);

            JSONArray alsoKnownAsJSON = name.getJSONArray(ALSOKNOWNASKEY);

            List<String> alsoKnownAs = loopJSONArray(alsoKnownAsJSON);

            String placeOfOrigin = sandwich.getString(PLACEOFORIGINKEY);
            String description = sandwich.getString(DESCRIPTIONKEY);
            String image = sandwich.getString(IMAGE);

            JSONArray ingredientsJSON = sandwich.getJSONArray(INGREDIENTS);
            List<String> ingredients = loopJSONArray(ingredientsJSON);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Create a List from given JSONArray
     *
     * @param jsonArray used for knownAs / ingredients etc.
     * @return List<String>
     * @throws JSONException
     */
    private static List<String> loopJSONArray(JSONArray jsonArray) throws JSONException {

        List<String> items = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String currentIngredient = (String) jsonArray.get(i);
            items.add(currentIngredient);
        }

        return items;
    }
}
