package com.example.demo.controller;
import com.example.demo.repository.TFRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TFCustomerController {
    private TFRepository tfRepository = new TFRepository();

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String listRandomMusicInfo(Model model){
        model.addAttribute("artists", tfRepository.getRandomArtists());
        model.addAttribute("tracks", tfRepository.getRandomTrack());
        model.addAttribute("genres", tfRepository.getRandomGenre());
        model.addAttribute("albums", tfRepository.getRandomAlbum());
        return "index";
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String searchForTrack(@RequestParam("search") String search, Model model){
        model.addAttribute("searchResult", tfRepository.getTrackInfo(search));
        model.addAttribute("searchTerm",search);
        return "search";
    }

}
