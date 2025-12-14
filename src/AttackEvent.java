import java.time.LocalDateTime;

public class AttackEvent {
    
    //Les données de la classe AttackEvent
    private String ipAddress;       
    private LocalDateTime timestamp; 
    private double latitude;
    private double longitude;
    private String country;

    //Setters et Getters
    public void setIpAddress(String ip) {
        this.ipAddress = ip;
    }

    public void setTimestamp(LocalDateTime time) {
        this.timestamp = time;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public LocalDateTime getTimeStamp() {
        return this.timestamp;
    }

    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double lat) {
        this.latitude = lat;
    }
    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double lon) { 
        this.longitude = lon;
    }
    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}