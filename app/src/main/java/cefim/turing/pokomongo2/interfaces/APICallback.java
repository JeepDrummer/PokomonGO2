package cefim.turing.pokomon.interfaces;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by peterbardu on 3/2/17.
 */

public interface APICallback {

    void successCallback(final Response response) throws IOException;

    void failCallback(final Response response);
}
