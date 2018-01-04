package frameworks.locationmanager.location;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Sandeep on 10/06/2016.
 */
public abstract class BaseLocationActivity extends AppCompatActivity implements LocationManager.LocationAndSettingChangeUpdates {
    protected LocationManager mLocationManager = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationManager = LocationManager.getmLocationManagerInstance(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLocationManager.connectGoogleClient();
        mLocationManager.setLocationandSettingChangeObserver(this);
    }


    public Location getCurrentLocation() {
        return mLocationManager.getmCurrentLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        mLocationManager.disconnectGoogleClient();
        mLocationManager.removeLocationandSettingChangeObserver(this);
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        mLocationManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
