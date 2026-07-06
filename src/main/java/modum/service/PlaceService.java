package modum.service;

import modum.entity.Place;
import modum.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;

    private static final Map<String, String> IMAGE_MAP = Map.ofEntries(
        // ☕ Kafeler
        Map.entry("Minoa",                        "/images/places/minoa2.webp"),
        Map.entry("Walters Coffee",               "/images/places/WaltersCoffeeRoastery.avif"),
        Map.entry("Story Coffee",                 "/images/places/s2.jpg"),
        Map.entry("Petra Roasting",               "/images/places/Petraroasting.png"),
        Map.entry("Petra Roasting Co",            "/images/places/s.avif"),
        Map.entry("Story Roasters",               "/images/places/s1.avif"),
        Map.entry("Montag Coffee",                "/images/places/l2.png"),
        Map.entry("Kronotrop",                    "/images/places/Kronotrop.png"),
        Map.entry("Cherrybean Coffees",           "/images/places/l6.avif"),
        Map.entry("Federal Coffee Company",       "/images/places/g.png"),
        Map.entry("Coffee Manifesto",             "/images/places/l3.png"),
        Map.entry("Brekkie",                      "/images/places/The Botanist Coffee.jpg"),
        Map.entry("Cup of Joy",                   "/images/places/h.jpg"),
        Map.entry("Caffe Nero Beşiktaş Çarşı",   "/images/places/nero.png"),
        Map.entry("MOC Coffee Roastery",          "/images/places/roastery.png"),
        Map.entry("The House Cafe Ortaköy",       "/images/places/thehouse.png"),
        Map.entry("Backyard",                     "/images/places/Hidden Garden Cafe.avif"),
        Map.entry("Black Owl Coffee",             "/images/places/h3.avif"),
        Map.entry("Kaffa Coffee",                 "/images/places/h5.avif"),
        Map.entry("United Coffee",                "/images/places/h1.jpg"),
        Map.entry("Joker No 19",                  "/images/places/Dorock.jpg"),
        Map.entry("Fiskos Kahve",                 "/images/places/h5.jpg"),
        Map.entry("Balkon Cafe",                  "/images/places/m5.jpg"),
        Map.entry("FilBooks",                     "/images/places/BooksAndCoffee.jpg"),
        Map.entry("Dem Karaköy",                  "/images/places/m.jpg"),
        Map.entry("Federal Galata",               "/images/places/m.png"),
        Map.entry("Coffee Department",            "/images/places/l5.avif"),
        Map.entry("Petra Karaköy",                "/images/places/l1.avif"),
        Map.entry("Mums Cafe",                    "/images/places/t1.jpg"),
        Map.entry("Walters Coffee Galata",        "/images/places/m4.jpg"),
        Map.entry("Velvet Cafe",                  "/images/places/r2.jpg"),
        Map.entry("Nevmekan Sahil",               "/images/places/m2.jpg"),
        Map.entry("Nevmekan Kuzguncuk",           "/images/places/nevmekan.png"),
        Map.entry("Moc Kuzguncuk",                "/images/places/moc.png"),
        Map.entry("Pita Kuzguncuk",               "/images/places/Minoa Village.avif"),
        Map.entry("Nail Kitabevi Cafe",           "/images/places/s3.avif"),
        Map.entry("Kuzguncuk Bostanı Cafe",       "/images/places/agacev.jpg"),
        Map.entry("Katibim Cafe",                 "/images/places/r1.jpg"),
        Map.entry("Çınaraltı Çay Bahçesi",        "/images/places/agacev.jpg"),

        // 🎲 Kutu Oyunu Kafeleri
        Map.entry("Goblin Cafe",                  "/images/places/o2.png"),
        Map.entry("Game Galeri",                  "/images/places/o3.jpg"),
        Map.entry("Gizli Bahçe Game Zone",        "/images/places/o.jpg"),
        Map.entry("Periwinkle XL",                "/images/places/o1.jpg"),

        // 📸 Fotoğraf Çekmelik
        Map.entry("F'Rosès",                      "/images/places/k3.jpg"),
        Map.entry("Patisserie de Pera",           "/images/places/k6.jpg"),
        Map.entry("Suflör Cafe",                  "/images/places/k8.jpg"),
        Map.entry("Cherry Blossom Cafe",          "/images/places/Cherry Blossom Cafe.jpg"),

        // 💕 Romantik
        Map.entry("Banyan Istanbul",              "/images/places/r1.jpg"),
        Map.entry("Loi Bosphorus",                "/images/places/r3.png"),
        Map.entry("Mürver Karaköy",               "/images/places/m2.avif"),

        // 🎵 Canlı Müzik
        Map.entry("Frankhan",                     "/images/places/Frankhan.png"),
        Map.entry("Karga Bar",                    "/images/places/Karga.jpg"),
        Map.entry("Asa-Khai Jazz",                "/images/places/r.jpg"),

        // 🎨 Sanat & Atölye
        Map.entry("Atölye Dally",                 "/images/places/w.avif"),
        Map.entry("Studio Masterpiece",           "/images/places/w3.avif"),
        Map.entry("Sosyal Sanathane",             "/images/places/w1.avif")
    );

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    private void assignImage(Place place) {
        String img = IMAGE_MAP.get(place.getName());
        if (img != null) {
            place.setImageUrl(img);
        } else {
            place.setImageUrl("/images/places/s2.jpg");
        }
    }

    public List<Place> searchPlaces(String query) {
        List<Place> places = placeRepository.findByNameContainingIgnoreCaseOrDistrictContainingIgnoreCase(query, query);
        places.forEach(this::assignImage);
        return places;
    }

    public List<Place> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        places.forEach(this::assignImage);
        return places;
    }

    public void savePlace(Place place) {
        placeRepository.save(place);
    }

    public Place getPlaceById(Long id) {
        Place place = placeRepository.findById(id).orElse(null);
        if (place != null) assignImage(place);
        return place;
    }

    public List<Place> getPlacesByMood(Long moodId) {
        List<Place> places = placeRepository.findByMoodsId(moodId);
        places.forEach(this::assignImage);
        return places;
    }

    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    public List<Place> getPlacesByDistrict(String district) {
        List<Place> places = placeRepository.findByDistrictContainingIgnoreCase(district);
        places.forEach(this::assignImage);
        return places;
    }
}