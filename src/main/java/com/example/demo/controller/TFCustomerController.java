package com.example.demo.controller;
import com.example.demo.models.TrackSearch;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TFrepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TFCustomerController {
    TFrepository tFrepository= new TFrepository();

   /* @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }*/

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String listRandomArtists(Model model){
        model.addAttribute("customers", tFrepository.getRandomArtists());
        model.addAttribute("tracks", tFrepository.getRandomTrack());
        model.addAttribute("genres", tFrepository.getRandomGenre());
        return "index";
    }
    @RequestMapping(value = "/tracks",method = RequestMethod.GET)
    public String listRandomTracks(Model model){
        model.addAttribute("tracks", tFrepository.getRandomTrack());
        return "index";
    }
    @RequestMapping(value = "/genres",method = RequestMethod.GET)
    public String listRandomGenres(Model model){
        model.addAttribute("genres", tFrepository.getRandomGenre());
        return "index";
    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String searchForTrack(@RequestParam("search") String search, Model model){
        model.addAttribute("searchResult",tFrepository.getTrackInfo(search));
        model.addAttribute("searchTerm",search);
        return "search";
    }

}
