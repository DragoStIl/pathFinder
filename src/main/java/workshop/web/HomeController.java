package workshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import workshop.model.Route;
import workshop.service.RouteService;

import java.util.List;

@Controller
public class HomeController {

    private RouteService routeService;

    @Autowired
    public HomeController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Route> routes = routeService.getMostCommented();
        model.addAttribute("mostCommented", routes.get(0));

        return "index";
    }
}
