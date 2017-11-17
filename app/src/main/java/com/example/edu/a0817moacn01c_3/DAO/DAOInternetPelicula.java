package com.example.edu.a0817moacn01c_3.DAO;

import android.os.AsyncTask;
import android.util.Log;

import com.example.edu.a0817moacn01c_3.Controller.ControllerContenido;
import com.example.edu.a0817moacn01c_3.Model.ContenedorDeContenido;
import com.example.edu.a0817moacn01c_3.Model.ContenedorDePelicula;
import com.example.edu.a0817moacn01c_3.Model.Contenido;
import com.example.edu.a0817moacn01c_3.Model.Pelicula;
import com.example.edu.a0817moacn01c_3.utils.HTTPConnectionManager;
import com.example.edu.a0817moacn01c_3.utils.ResultListener;
import com.example.edu.a0817moacn01c_3.utils.TMDBHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ma on 14/11/17.
 */

public class DAOInternetPelicula {
    private Minion unMinion;

    public void getPeliculasPopulares(ResultListener<List<Pelicula>> listener){

        String url = TMDBHelper.getPopularMovies(TMDBHelper.language_SPANISH,1);
        unMinion = new Minion(url);
        unMinion.setEscuchadorPeliculasControlador(listener);
        unMinion.execute();
    }

    public class Minion extends AsyncTask<String,Void,List<Pelicula>>{
        private ResultListener<List<Pelicula>>escuchadorPeliculasControlador;
        private String url;
        HttpURLConnection urlConnection;

        public Minion(String url){
            this.url=url;
        }

        public void setEscuchadorPeliculasControlador(ResultListener<List<Pelicula>> escuchadorPeliculasControlador) {
            this.escuchadorPeliculasControlador = escuchadorPeliculasControlador;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {

            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;
            try{
                input = connectionManager.getRequestString(url);
                Log.v("[G3] fetching URL", url);
                Log.v("[G3] input", input);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();

            ContenedorDePelicula contenedorDePelicula = gson.fromJson(input,ContenedorDePelicula.class);

            return contenedorDePelicula.getContenidos();

        }
        @Override
        protected void onPostExecute(List<Pelicula> listaPeliculas) {

            this.escuchadorPeliculasControlador.finish(listaPeliculas);
        }
    }
}