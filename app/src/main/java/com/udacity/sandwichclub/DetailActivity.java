package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.also_known_tv)
    TextView alsoKnownTv;
    @BindView(R.id.ingredients_tv)
    TextView ingredientsTv;
    @BindView(R.id.origin_tv)
    TextView originTv;
    @BindView(R.id.description_tv)
    TextView descriptionTv;
    @BindView(R.id.image_iv)
    ImageView ingredientsIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        if (sandwich != null) {
            alsoKnownTv.setText(formatListToString(sandwich.getAlsoKnownAs()));
            ingredientsTv.setText(formatListToString(sandwich.getIngredients()));
            originTv.setText(sandwich.getPlaceOfOrigin());
            descriptionTv.setText(sandwich.getDescription());
        } else {
            closeOnError();
        }
    }

    /**
     * Convert the List<String> to an String
     *
     * @param list given String List
     * @return converted String
     */
    private String formatListToString(List<String> list) {

        StringBuilder stringBuilder = new StringBuilder();

        for (String string : list) {
            stringBuilder.append(string);
        }

        return stringBuilder.toString();
    }
}
