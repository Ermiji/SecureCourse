package me.ermias.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public String listOfCourses(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        return "listofcourse";
    }
    @RequestMapping("/addcourse")
    public String addCourse(Model model){
        model.addAttribute("course", new Course());
        return "addcourse";
    }

    @PostMapping("/processcourse")
    public String processCourse(@RequestParam("action") String action, Course course){
        if(action.equals("cancel")){
            return"redirect:/";
        } else {
            courseRepository.save(course);
            return "redirect:/";
        }
    }
}
