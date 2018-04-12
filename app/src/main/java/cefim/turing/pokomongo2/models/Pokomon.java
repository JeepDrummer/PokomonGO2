package cefim.turing.pokomon.models;

import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by peterbardu on 3/24/17.
 */

public class Pokomon implements Serializable {

    //{"_id":"58b6f4bc75fa97000469e21f",
    // "level":2,
    // "attaque":8,
    // "defense":0,
    // "vitesse":9,
    // "xp":0,
    // "coordinates":[47.39233510793859,0.7000970190311437],
    // "name":"Chenipan",
    // "picture":"http://www.pokemontrash.com/pokedex/images/minixy/010.png",
    // "number":10,
    // "captured":false}

    public String mId;
    // TODO : les autres...
    public int mPicture;
    // TODO : les autres...
    
    public Pokomon(JSONObject jsonObject) throws JSONException {

        mId = jsonObject.getString("_id");
        // TODO : les autres...
        mPicture = Integer.valueOf(jsonObject.getString("picture").substring(50, 53));
        // TODO : les autres...
    }
}
