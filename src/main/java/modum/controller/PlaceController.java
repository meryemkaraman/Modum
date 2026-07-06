package modum.controller;

import modum.entity.Place;
import modum.service.CommentService;
import modum.service.PlaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import modum.entity.Comment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PlaceController {

    private final PlaceService placeService;
    private final CommentService commentService;

    public PlaceController(PlaceService placeService,
                           CommentService commentService) {

        this.placeService = placeService;
        this.commentService = commentService;
    }

@GetMapping("/")
public String home() {

    return "redirect:/moods";
}
    @GetMapping("/place/{id}")
    public String placeDetail(@PathVariable Long id,
                              Model model) {

        Place place = placeService.getPlaceById(id);

        model.addAttribute("place", place);

        model.addAttribute(
                "comments",
                commentService.getCommentsByPlace(id));

        return "place-detail";
    }

    @GetMapping("/mood/{id}")
    public String placesByMood(@PathVariable Long id,
                               Model model) {

        model.addAttribute(
                "places",
                placeService.getPlacesByMood(id));

        return "index";
    }
    @PostMapping("/place/{id}/comment")
public String addComment(@PathVariable Long id,
                         @ModelAttribute Comment comment) {

    Place place = placeService.getPlaceById(id);

    comment.setPlace(place);

    commentService.saveComment(comment);

    return "redirect:/place/" + id;
}
@GetMapping("/search")
public String search(@RequestParam String query, Model model) {
    model.addAttribute("places", placeService.searchPlaces(query));
    return "index";
}

@GetMapping("/district/{name}")
public String placesByDistrict(@PathVariable String name, Model model) {
    model.addAttribute("places", placeService.getPlacesByDistrict(name));
    return "index";
}
}