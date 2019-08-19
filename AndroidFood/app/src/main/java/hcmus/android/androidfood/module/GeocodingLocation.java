package hcmus.android.androidfood.module;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeocodingLocation {
    private static final String TAG = "GeocodingLocation";
    ArrayList<Double> latlong = new ArrayList<Double>();

    public ArrayList<Double> getAddressFromLocation(final String locationAddress,
                                final Context context) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List
                            addressList = geocoder.getFromLocationName(locationAddress, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = (Address) addressList.get(0);
                        latlong = new ArrayList<Double>();
                        latlong.add(address.getLatitude());
                        latlong.add(address.getLongitude());

                    }
                } catch (IOException e) {
                    Log.e(TAG, "Unable to connect to Geocoder", e);
                } finally {

                }
            }
        };
        thread.start();
        return latlong;
    }
}
