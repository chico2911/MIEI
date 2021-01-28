import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Registo {
    Timestamp timestamp;
    String city;
    String latitude; String longitude;
    Double temperature;
    int humidity;

    public Registo(String timestamp, String city, String latitude, String longitude, String temperature, String humidity) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(timestamp);
            this.timestamp = new Timestamp(parsedDate.getTime());
        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = Double.parseDouble(temperature);
        this.humidity = Integer.parseInt( humidity);
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    //Representing the range "3/16 to 6/15" is difficult. Representing the range 316 to 615 is easy.
    public int getSeason(){
        int result;
        int month_day = timestamp.getMonth() * 100 + timestamp.getDay();
        if (month_day <= 320) {
            result = 0;
        }
        else if (month_day <= 621) {
            result = 1;
        }
        else if (month_day <= 922) {
            result = 2;
        }
        else if (month_day <= 1221) {
            result = 3;
        }
        else {
            result = 0;
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registo registo = (Registo) o;
        return humidity == registo.humidity &&
                Objects.equals(timestamp, registo.timestamp) &&
                Objects.equals(city, registo.city) &&
                Objects.equals(latitude, registo.latitude) &&
                Objects.equals(longitude, registo.longitude) &&
                Objects.equals(temperature, registo.temperature);
    }


    @Override
    public String toString() {
        return "Registo{" +
                "timestamp=" + timestamp +
                ", city='" + city + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}
