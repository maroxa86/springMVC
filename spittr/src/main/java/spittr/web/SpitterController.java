package spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import spittr.Spitter;
import spittr.data.SpitterRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

  private SpitterRepository spitterRepository;

  @Autowired
  public SpitterController(SpitterRepository spitterRepository) {
    this.spitterRepository = spitterRepository;
  }
  
  @RequestMapping(value="/register", method=GET)
  public String showRegistrationForm(Model model) {
    model.addAttribute(new Spitter());
    return "registerForm";
  }
  
  @RequestMapping(value="/register", method=POST)
  public String processRegistration(
		 @Valid SpitterForm spitterForm, 
		 RedirectAttributes model,
		 Errors errors) throws IllegalStateException, IOException {
    if (errors.hasErrors()) {
      return "registerForm";
    }
    
    MultipartFile profilePicture = spitterForm.getProfilePicture();
    profilePicture.transferTo(new File(profilePicture.getOriginalFilename()));
    Spitter spitter = spitterRepository.save(spitterForm.toSpitter());
    //Forma parte de la URL de redirección
    model.addAttribute("username", spitter.getUsername());
    //No forma parte de la URL de la redirección pero aparece en ella en forma de parametro en la petición GET
    model.addAttribute("spitterId", spitter.getId());
    model.addFlashAttribute("spitter", spitter);
    return "redirect:/spitter/{username}";
  }
  
  @RequestMapping(value="/{username}", method=GET)
  public String showSpitterProfile(@PathVariable String username, Model model) {
	  if(!model.containsAttribute("spitter")){
		  Spitter spitter = spitterRepository.findByUsername(username);
		  model.addAttribute(spitter);
	  }
	  return "profile";
  }
  
}
