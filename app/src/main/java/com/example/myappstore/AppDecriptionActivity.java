package com.example.myappstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myappstore.adapter.MainRecyclerAdapter;
import com.example.myappstore.adapter.ProfileItemRecyclerAdapter;
import com.example.myappstore.adapter.ReviewAdapter;
import com.example.myappstore.adapter.TopItemRecyclerAdapter;
import com.example.myappstore.model.AppResponse;
import com.example.myappstore.model.CategoryItem;
import com.example.myappstore.model.CategoryItemData;
import com.example.myappstore.model.MainCategoryRecyclerData;
import com.example.myappstore.model.Review;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppDecriptionActivity extends AppCompatActivity {
    RatingBar ratingBar;
    private ArrayList<CategoryItem> posts;
    private ArrayList<Review> rwposts;
    ImageView favBtn;
    Bundle arguments;
    int item_id;
    int isFavorite;
    String ImagePath;
    String AppName;
    String Description;
    String Price;
    private EditText edReview;
    private Button btnReview;
    private ImageView item_image;
    private TextView item_title;
    private TextView description;
    private TextView price;
    private ReviewAdapter reviewAdapter;
    private ImageView back;
    ReviewAdapter mAdapter;
    RecyclerView recyclerView;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(CategoryItemData.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonAPI json = retrofit.create(JsonAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_decription);

        arguments = getIntent().getExtras();
        item_id = arguments.getInt("item_id");
        isFavorite = arguments.getInt("Favorites");
        ImagePath = arguments.getString("ImagePath");
        AppName = arguments.getString("AppName");
        Description = arguments.getString("Description");
        Price = arguments.getString("Price");

        recyclerView = (RecyclerView) findViewById(R.id.reviews_recycler);
        favBtn = (ImageView) findViewById(R.id.favBtn);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        edReview = (EditText) findViewById(R.id.edReview);
        btnReview = (Button) findViewById(R.id.btnReview);
        item_image = (ImageView) findViewById(R.id.item_image);
        item_title = (TextView) findViewById(R.id.item_title);
        description = (TextView) findViewById(R.id.description);
        price = (TextView) findViewById(R.id.item_price);
        back = (ImageView) findViewById(R.id.back);

        try (InputStream inputStream = getApplicationContext().getAssets().open(ImagePath)) {
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            item_image.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        item_title.setText(AppName);
        description.setText(Description);
        price.setText(Price);
        favBtn.setClickable(true);

        if (isFavorite == 1) {
            favBtn.setBackgroundResource(R.drawable.ic_color_favorite_24);
        } else if (isFavorite == 0) {
            favBtn.setBackgroundResource(R.drawable.baseline_favorite_border_24);
        }

        favBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_CANCEL: {
                        favBtn.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                        break;
                    }
                    case MotionEvent.ACTION_DOWN: {
                        CategoryItem fbody = new CategoryItem();

                        if (isFavorite == 0) {
                            fbody.Favorites = 1;
                            isFavorite = 1;
                            favBtn.setBackgroundResource(R.drawable.ic_color_favorite_24);
                        } else if (isFavorite == 1) {
                            fbody.Favorites = 0;
                            isFavorite = 0;
                            favBtn.setBackgroundResource(R.drawable.baseline_favorite_border_24);
                        }

                        Call<AppResponse> call = json.changeFavorite(item_id, fbody);

                        call.enqueue(new Callback<AppResponse>() {
                            @Override
                            public void onResponse(Call<AppResponse> call, Response<AppResponse> response) {
                                if (!response.isSuccessful()) {
                                    System.out.println("Favorite error: " + response.code());
                                    return;
                                }
                                recyclerView.getAdapter().notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<AppResponse> call, Throwable t) {
                                System.out.println("Favorite error: " + t.getMessage());
                            }
                        });
                    }

                    break;
                }
                return true;
            }
        });

        CategoryItem body = new CategoryItem();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                body.Rating = Double.parseDouble(String.valueOf(ratingBar.getRating()));

                Call<AppResponse> call = json.changeRating(item_id, body);
                call.enqueue(new Callback<AppResponse>() {
                    @Override
                    public void onResponse(Call<AppResponse> call, Response<AppResponse> response) {
                        if (!response.isSuccessful()) {
                            System.out.println("Error: " + response.code());
                            return;
                        }

                    }

                    @Override
                    public void onFailure(Call<AppResponse> call, Throwable t) {
                        System.out.println("Error: " + t.getMessage());
                    }
                });
            }
        });

        Call<List<Review>> call = json.getReview(item_id);

        call.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                rwposts = (ArrayList<Review>) response.body();
                setReviewRecycler(rwposts);
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
        ;

    }

    private void setReviewRecycler(List<Review> categoryItemDataList) {
        reviewAdapter = new ReviewAdapter(this, categoryItemDataList);
        recyclerView.setAdapter(reviewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.getAdapter().notifyDataSetChanged();
    }


    public void addTextClick(View view) {
        Review body = new Review();
        body.review = edReview.getText().toString();

        Call<AppResponse> call = json.addReview(item_id, body);
        Call<List<Review>> call1 = json.getReview(item_id);

        call.enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(Call<AppResponse> call, Response<AppResponse> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Add error: " + response.code());
                    return;
                }
                setReviewRecycler(rwposts);
                // mAdapter.notifyDataSetChanged();
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<AppResponse> call, Throwable t) {
                System.out.println("Add error: " + t.getMessage());
            }
        });


        call1.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call1, Response<List<Review>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                rwposts = (ArrayList<Review>) response.body();
                setReviewRecycler(rwposts);
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Review>> call1, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
