package com.example.android.DITO.model;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.example.android.DITO.R;

import java.util.ArrayList;
import java.util.List;

public class AttractionRepository {

    @SuppressWarnings({"FieldCanBeLocal", "unused"}) private Context context;
    private List<AttractionCollection> collections;
    @SuppressLint("StaticFieldLeak") private static AttractionRepository attractionRepository;

    public static AttractionRepository getInstance(Context packageContext) {
        if (attractionRepository == null) {
            attractionRepository = new AttractionRepository(packageContext);
        }
        return attractionRepository;
    }

    public AttractionCollection getCollection(int sectionTitle) {
        for (int i = 0; i < collections.size(); i++) {
            if (sectionTitle == collections.get(i).getHeaderTitle()) {
                return collections.get(i);
            }
        }

        return null;
    }

    private AttractionRepository(Context context) {
        this.context = context.getApplicationContext();
        collections = new ArrayList<>();

        // Build collection
        AttractionCollection activity = buildActivityCollection();
        collections.add(activity);

        AttractionCollection restaurants = buildRestaurantsCollection();
        collections.add(restaurants);

        AttractionCollection breweries = buildBreweriesCollection();
        collections.add(breweries);

        AttractionCollection nightLife = buildNightLifeCollection();
        collections.add(nightLife);
    }

    public List<AttractionCollection> getCollections() {
        return collections;
    }

    @VisibleForTesting
    static AttractionCollection buildActivityCollection() {
        List<Attraction> attractions = new ArrayList<>();

        attractions.add(new Attraction(
                        R.drawable.bandong,
                        R.string.judulbandung,
                        R.string.kotakembang,
                        R.string.deskripsibandung,
                        R.string.mapbandung
                )
        );
        attractions.add(new Attraction(
                        R.drawable.garut,
                        R.string.judulgarut,
                        R.string.swissvanjava,
                        R.string.deskripsigarut,
                        R.string.mapgarut
                )
        );
        attractions.add(new Attraction(
                        R.drawable.yogyakarta,
                        R.string.judulyogyakarta,
                        R.string.kotagudeg,
                        R.string.deskripsiyogyakarta,
                        R.string.mapyogyakarta
                )
        );
        attractions.add(new Attraction(
                        R.drawable.semarang,
                        R.string.judulsemarang,
                        R.string.kotalumpia,
                        R.string.deskripsisemarang,
                        R.string.mapsemarang
                )
        );
        attractions.add(new Attraction(
                        R.drawable.sby,
                        R.string.judulsurabaya,
                        R.string.kotapahlawan,
                        R.string.deskripsisurabaya,
                        R.string.mapsurabaya
                )
        );

        return new AttractionCollection(R.string.kotafavorit, attractions);
    }

    @VisibleForTesting
    static AttractionCollection buildRestaurantsCollection() {
        ArrayList<Attraction> attractions = new ArrayList<>();

        attractions.add(new Attraction(
                        R.drawable.borobudur,
                        R.string.judulborobudur,
                        R.string.jawatengah,
                        R.string.deskripsiborobudur,
                        R.string.mapborobudur
                )
        );
        attractions.add(new Attraction(
                        R.drawable.komodo,
                        R.string.judulkomodo,
                        R.string.ntt,
                        R.string.deskripsikomodo,
                        R.string.mapkomodo
                )
        );
        attractions.add(new Attraction(
                        R.drawable.leuser,
                        R.string.judulleuser,
                        R.string.sumatera,
                        R.string.deskripsileuser,
                        R.string.mapleuser
                )
        );
        attractions.add(new Attraction(
                        R.drawable.ramayana,
                        R.string.judulramayana,
                        R.string.jawatengah,
                        R.string.deskripsiramayana,
                        R.string.mapramayana
                )
        );
        attractions.add(new Attraction(
                        R.drawable.batikpekalongan,
                        R.string.judulbatik,
                        R.string.jawatengah,
                        R.string.deskripsibatik,
                        R.string.mappekalongan
                )
        );
        attractions.add(new Attraction(
                        R.drawable.sekaten,
                        R.string.judulsekaten,
                        R.string.yogyakarta,
                        R.string.deskripsisekaten,
                        R.string.mapsekaten
                )
        );
        attractions.add(new Attraction(
                        R.drawable.kecak,
                        R.string.judulkecak,
                        R.string.bali,
                        R.string.deskripsikecak,
                        R.string.mapkecak
                )
        );

        return new AttractionCollection(R.string.situskebudayaan, attractions);
    }

    @VisibleForTesting
    static AttractionCollection buildBreweriesCollection() {
        ArrayList<Attraction> attractions = new ArrayList<>();

        attractions.add(new Attraction(
                        R.drawable.gili,
                        R.string.judulgili,
                        R.string.ntb,
                        R.string.deskripsigili,
                        R.string.mapgili
                )
        );
        attractions.add(new Attraction(
                        R.drawable.tangkuban,
                        R.string.judultangkuban,
                        R.string.jawabarat,
                        R.string.deskripsitangkuban,
                        R.string.maptangkuban
                )
        );
        attractions.add(new Attraction(
                        R.drawable.jatimpark,
                        R.string.juduljatimpark,
                        R.string.jawatimur,
                        R.string.deskripsijatimpark,
                        R.string.mapjatimpark
                )
        );
        attractions.add(new Attraction(
                        R.drawable.bunaken,
                        R.string.judulbunaken,
                        R.string.sulut,
                        R.string.deskripsibunaken,
                        R.string.mapbunaken
                )
        );

        return new AttractionCollection(R.string.pariwisata, attractions);
    }

    @VisibleForTesting
    static AttractionCollection buildNightLifeCollection() {
        ArrayList<Attraction> attractions = new ArrayList<>();

        attractions.add(new Attraction(
                        R.drawable.fatahillah,
                        R.string.judulfatahillah,
                        R.string.jakarta,
                        R.string.deskripsifatahillah,
                        R.string.mapfatahillah
                )
        );
        attractions.add(new Attraction(
                        R.drawable.keraton,
                        R.string.judulkeraton,
                        R.string.yogyakarta,
                        R.string.deskripsikeraton,
                        R.string.mapkeraton
                )
        );
        attractions.add(new Attraction(
                        R.drawable.ambarawa,
                        R.string.judulambarawa,
                        R.string.jawatengah,
                        R.string.deskripsiambarawa,
                        R.string.mapambarawa
                )
        );
        attractions.add(new Attraction(
                        R.drawable.lawangsewu,
                        R.string.judullawangsewu,
                        R.string.jawatengah,
                        R.string.deskripsilawangsewu,
                        R.string.maplawangsewu
                )
        );
        attractions.add(new Attraction(
                        R.drawable.sangiran,
                        R.string.judulsangiran,
                        R.string.jawatengah,
                        R.string.deskripsisangiran,
                        R.string.mapsangiran
                )
        );

        return new AttractionCollection(R.string.situssejarah, attractions);
    }
}
