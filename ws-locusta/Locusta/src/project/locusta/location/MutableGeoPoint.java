package project.locusta.location;
import com.google.android.maps.GeoPoint;

public class MutableGeoPoint extends GeoPoint {
    private int latitudeE6, longitudeE6;

    public MutableGeoPoint(int latitudeE6, int longitudeE6) {
        super(0, 0); // the values in the superclass are moot
        this.latitudeE6 = latitudeE6;
        this.longitudeE6 = longitudeE6;
    }

    @Override
    public int getLatitudeE6() {
        return latitudeE6;
    }

    @Override
    public int getLongitudeE6() {
        return longitudeE6;
    }

    public void setLatitudeE6(int value) {
        this.latitudeE6 = value;
    }

    public void setLongitudeE6(int value) {
        this.longitudeE6 = value;
    }

    @Override
    public int hashCode() {
        return 29*latitudeE6 + longitudeE6;
    }

    @Override
    public String toString() {
        return latitudeE6 + "," + longitudeE6;
    }
}