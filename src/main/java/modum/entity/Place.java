package modum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String district;

    private Double rating;

    private Boolean wifi;

    private Boolean socket;

    private String imageUrl;
    private String category;
    public String getCategory() {
    return category;
}

private String priceRange;

public String getPriceRange() { return priceRange; }
public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

public void setCategory(String category) {
    this.category = category;
}
    @ManyToMany
@JoinTable(
    name = "place_mood",
    joinColumns = @JoinColumn(name = "place_id"),
    inverseJoinColumns = @JoinColumn(name = "mood_id")
)
private List<Mood> moods;

    @OneToMany(mappedBy = "place")
private List<Comment> comments;
public List<Comment> getComments() {
    return comments;
}

public void setComments(List<Comment> comments) {
    this.comments = comments;
}
    public Place() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
    this.id = id;
}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getSocket() {
        return socket;
    }

    public void setSocket(Boolean socket) {
        this.socket = socket;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

public List<Mood> getMoods() {
    return moods;
}

public void setMoods(List<Mood> moods) {
    this.moods = moods;
}
private String address;
private String openingTime;
private String closingTime;
private Double latitude;
private Double longitude;

public String getOpeningTime() { return openingTime; }
public void setOpeningTime(String openingTime) { this.openingTime = openingTime; }

public String getClosingTime() { return closingTime; }
public void setClosingTime(String closingTime) { this.closingTime = closingTime; }

public Double getLatitude() { return latitude; }
public void setLatitude(Double latitude) { this.latitude = latitude; }

public Double getLongitude() { return longitude; }
public void setLongitude(Double longitude) { this.longitude = longitude; }

public String getAddress() {
    return address;
}

public void setAddress(String address) {
    this.address = address;
}
}