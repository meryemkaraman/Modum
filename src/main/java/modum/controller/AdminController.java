package modum.controller;

import modum.entity.Place;
import modum.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final PlaceService placeService;

    public AdminController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/admin/add")
    public String addPlacePage() {
        return "add-place";
    }

    @PostMapping("/admin/save")
    public String savePlace(@ModelAttribute Place place) {

        placeService.savePlace(place);

        return "redirect:/";
    }

    @GetMapping("/admin/edit")
    public String editPlaces(Model model) {

        model.addAttribute("places",
                placeService.getAllPlaces());

        return "edit-places";
    }

    @GetMapping("/admin/edit/{id}")
    public String editPlace(@PathVariable Long id,
                            Model model) {

        Place place = placeService.getPlaceById(id);

        model.addAttribute("place", place);

        return "edit-place";
    }
    @PostMapping("/admin/update")
public String updatePlace(@ModelAttribute Place place) {

    placeService.savePlace(place);

    return "redirect:/";
}
 @GetMapping("/admin/delete")
public String deletePlaces(Model model) {

    model.addAttribute("places",
            placeService.getAllPlaces());

    return "delete-places";
}
@GetMapping("/admin/delete/{id}")
public String deletePlace(@PathVariable Long id) {

    placeService.deletePlace(id);

    return "redirect:/admin/delete";
}
}