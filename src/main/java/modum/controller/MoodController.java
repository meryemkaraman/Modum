package modum.controller;

import modum.entity.Place;
import modum.repository.MoodRepository;
import modum.repository.PlaceRepository;
import modum.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MoodController {

    private final MoodRepository moodRepository;
    private final PlaceService placeService;

    public MoodController(MoodRepository moodRepository, PlaceService placeService) {
        this.moodRepository = moodRepository;
        this.placeService = placeService;
    }

    @GetMapping("/moods")
    public String moodPage(Model model) {
        model.addAttribute("moods", moodRepository.findAll());

        // Öne çıkan mekanlar: belirli isimlerle seç
        List<String> featuredNames = List.of("Minoa", "Nevmekan Sahil", "Cherry Blossom Cafe", "Petra Roasting");
        List<Place> allPlaces = placeService.getAllPlaces();

        List<Place> featured = new ArrayList<>();
        for (String name : featuredNames) {
            allPlaces.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .ifPresent(featured::add);
        }

        // Yeterli mekan bulunamazsa ilk 4'ü al
        if (featured.size() < 4) {
            for (Place p : allPlaces) {
                if (featured.stream().noneMatch(f -> f.getId().equals(p.getId()))) {
                    featured.add(p);
                }
                if (featured.size() == 4) break;
            }
        }

        model.addAttribute("featured", featured);
        return "mood-selection";
    }
}