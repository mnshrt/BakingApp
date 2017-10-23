package com.manish.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecipeListAdapter.RecipeListItemClickListener {
private static final int RECIPE_LIST_ITEMS =100;
    private RecipeListAdapter recipeListAdapter;

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    List<Recipe> recipeList;
    RecyclerView recipeListRecyclerView;
    String videoUrl= "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd9a6_2-mix-sugar-crackers-creampie/2-mix-sugar-crackers-creampie.mp4";
public static final String LOG_TAG = String.class.getSimpleName();
Toast mToast;
    TextView testTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
// get the list of recipes from jsonUtility
        recipeList= JsonUtility.jsonContentExtraction(this);

/*        testTextView = (TextView) findViewById(R.id.testTextView);
        for(Recipe r : recipeList){
        Log.i(LOG_TAG,r.getName()+"\n");
            testTextView.append(r.getName()+" "+r.getRecipeId() +" "+r.getServings()+"\n");

        }*/
        recipeListRecyclerView = (RecyclerView) findViewById(R.id.recipe_list_rv);
        recipeListRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipeListRecyclerView.setLayoutManager(linearLayoutManager);

        recipeListAdapter = new RecipeListAdapter(recipeList,this);
        recipeListRecyclerView.setAdapter(recipeListAdapter);
    }

    @Override
    public void onRecipeListItemClick(int clickedItemIndex) {


       /* ArrayList<Step> steps = recipeList.get(clickedItemIndex).getRecipeMakingSteps();
        String toastMessage = "Item #" + steps.get(0).getShortDescription() +" clicked.";
        mToast= Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        mToast.show();*/

        Intent intent=  new Intent(MainActivity.this,RecipeDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("recipe",recipeList.get(clickedItemIndex));
        intent.putExtra("recipeClicked",bundle);
        startActivity(intent);

    }





   /* public void recipeInstructionPlayer(String videoUrl){
        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exo_player_view);
        try{
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);


            // convert the url string to url string
            Uri videoUri = Uri.parse(videoUrl);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_player" );
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            MediaSource videoSource =new ExtractorMediaSource(videoUri,dataSourceFactory,extractorsFactory,null,null);

            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(videoSource);
            exoPlayer.setPlayWhenReady(true);

        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
}
