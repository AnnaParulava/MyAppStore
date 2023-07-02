package com.example.myappstore.model;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappstore.JsonAPI;
import com.example.myappstore.R;
import com.example.myappstore.adapter.TopItemRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryItemData {
    public static List<CategoryItem> unionCategoryItemList = new ArrayList<>();
    public static List<CategoryItem> categoryItemList = new ArrayList<>();
    public static List<CategoryItem> categoryItemListJourneys = new ArrayList<>();
    public static List<CategoryItem> categoryItemListLearningEnglish = new ArrayList<>();
    public static List<CategoryItem> categoryItemListEntertainments = new ArrayList<>();
    public static final String BASE_URL = "http://192.168.208.150:5000/api/appstore/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonAPI json = retrofit.create(JsonAPI.class);

    public List<CategoryItem> categoryItemList(int id) {
        Call<List<CategoryItem>> call = json.getCategory(id);

        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                categoryItemList = (ArrayList<CategoryItem>) response.body();
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
        return categoryItemList;
    }

    public List<CategoryItem> categoryItemListJourneys(int id) {
        Call<List<CategoryItem>> call = json.getCategory(id);

        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                categoryItemListJourneys = (ArrayList<CategoryItem>) response.body();
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
        return categoryItemListJourneys;
    }

    public List<CategoryItem> categoryItemListLearningEnglish(int id) {
        Call<List<CategoryItem>> call = json.getCategory(id);

        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                categoryItemListLearningEnglish = (ArrayList<CategoryItem>) response.body();
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
        return categoryItemListLearningEnglish;
    }

    public List<CategoryItem> categoryItemListEntertainments(int id) {
        Call<List<CategoryItem>> call = json.getCategory(id);

        call.enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                if (!response.isSuccessful()) {
                    System.out.println("Error: " + response.code());
                    return;
                }
                categoryItemListEntertainments = (ArrayList<CategoryItem>) response.body();
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
        return categoryItemListEntertainments;
    }


}



/*
    //обьединить список
    public List<CategoryItem> buildUnionListData(){
        unionCategoryItemList = Stream.of(categoryItemListFoodAndDrinks, categoryItemListJourneys,
            categoryItemListLearningEnglish,categoryItemListEntertainments)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
    return unionCategoryItemList;}

    public List<CategoryItem> buildListDataFoodAndDrinks() {
        categoryItemListFoodAndDrinks.clear();
        categoryItemListFoodAndDrinks.add(new CategoryItem(1, R.drawable.kuhnya_na_raione, "Кухня на районе: доставка еды", 4.5, ""));
        categoryItemListFoodAndDrinks.add(new CategoryItem(2, R.drawable.perecrestoc, "Перекресток - доставка продуктов на дом", 4.3,""));
        categoryItemListFoodAndDrinks.add(new CategoryItem(3, R.drawable.grab_superapp, "Grab Superapp", 4.2,""));
        categoryItemListFoodAndDrinks.add(new CategoryItem(4, R.drawable.dicsi, "Дикси - Клуб Друзей", 4.6,""));
        categoryItemListFoodAndDrinks.add(new CategoryItem(5, R.drawable.utconos, "Утконос – доставка продуктов в день заказа", 4.8,""));
        categoryItemListFoodAndDrinks.add(new CategoryItem(6, R.drawable.igooods, "igooods: заказ и доставка продуктов из ЛЕНТА, АШАН", 3.9,""));
        categoryItemListFoodAndDrinks.add(new CategoryItem(7, R.drawable.beyond, "Хороший Выбор Онлайн", 4.9,""));
        return categoryItemListFoodAndDrinks;
    }

    public List<CategoryItem> buildListDataJourneys() {
        categoryItemListJourneys.clear();
        categoryItemListJourneys.add(new CategoryItem(1, R.drawable.tripadvisor, "Tripadvisor: все для поездок", 4.9,""));
        categoryItemListJourneys.add(new CategoryItem(2, R.drawable.booking, "Booking.com: Hotels and more", 4.6,""));
        categoryItemListJourneys.add(new CategoryItem(3, R.drawable.flio, "FLIO - твой личный ассистент в путешествии", 4.3,""));
        categoryItemListJourneys.add(new CategoryItem(4, R.drawable.expedia, "Expedia: Hotels, Flights & Car", 4.7,""));
        categoryItemListJourneys.add(new CategoryItem(5, R.drawable.airbnb, "Airbnb", 3.4,""));
        categoryItemListJourneys.add(new CategoryItem(6, R.drawable.couchsurfing, "Couchsurfing Travel App", 4.7,""));
        categoryItemListJourneys.add(new CategoryItem(7, R.drawable.finnair, "Finnair", 3.4,""));
        return categoryItemListJourneys;

    }

    public List<CategoryItem> buildListDataLearningEnglish() {
        categoryItemListLearningEnglish.clear();
        categoryItemListLearningEnglish.add(new CategoryItem(1, R.drawable.busuu, "Busuu - Изучай английский", 4.9,""));
        categoryItemListLearningEnglish.add(new CategoryItem(2, R.drawable.reword, "Английский язык. Учить слова", 4.5,""));
        categoryItemListLearningEnglish.add(new CategoryItem(3, R.drawable.two_books, "Книги на английском с переводом | 2Books", 4.4,""));
        categoryItemListLearningEnglish.add(new CategoryItem(4, R.drawable.elsa, "ELSA Speak: Коррекция Акцента", 3.9,""));
        categoryItemListLearningEnglish.add(new CategoryItem(5, R.drawable.xeropan, "Xeropan: изучайте языки", 4.1,""));
        categoryItemListLearningEnglish.add(new CategoryItem(6, R.drawable.duolingo, "Duolingo: Учи языки бесплатно", 3.9,""));
        categoryItemListLearningEnglish.add(new CategoryItem(7, R.drawable.puzzle_english, "Puzzle English", 4.1,""));
        return categoryItemListLearningEnglish;

    }

    public List<CategoryItem> buildListDataEntertainments() {
        categoryItemListEntertainments.clear();
        categoryItemListEntertainments.add(new CategoryItem(1, R.drawable.deezer, "Deezer: музыка и подкасты", 3.4,""));
        categoryItemListEntertainments.add(new CategoryItem(2, R.drawable.groovepad, "Groovepad – музыка и биты", 4,""));
        categoryItemListEntertainments.add(new CategoryItem(3, R.drawable.hbo_max, "HBO Max: Stream TV & Movies", 4.4,""));
        categoryItemListEntertainments.add(new CategoryItem(4, R.drawable.redbul, "Red Bull TV: Фильмы, сериалы, трансляции", 4.9,""));
        categoryItemListEntertainments.add(new CategoryItem(5, R.drawable.start_films, "START Фильмы, сериалы", 4.6,""));
        categoryItemListEntertainments.add(new CategoryItem(6, R.drawable.two_books, "Twitch: прямые трансляции игр", 4.9,""));
        categoryItemListEntertainments.add(new CategoryItem(7, R.drawable.pervi, "Кино1ТВ: сериалы и фильмы HD", 4.6,""));
        return categoryItemListEntertainments;
    }
 */
