package com.alacriti.saml.sp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SpController {
  Logger logger = LoggerFactory.getLogger(SpController.class);

  @GetMapping("/logout")
  public String logout(Model model, HttpServletResponse response) {
    logger.info("GET logout");
    Cookie cookie = new Cookie(Constants.CookieName, null);
    cookie.setMaxAge(0); // expires now
    response.addCookie(cookie);
    model.addAttribute("userInfo", new UserInfo());
    return "login";
  }

  @GetMapping("/home")
  public String homeView(Model model) {
    logger.info("GET home");
    // model.addAttribute("userInfo", new UserInfo());
    return "home";
  }

  @GetMapping("/samlAssertion")
  public RedirectView samlAssertion(Model model, HttpServletResponse response) {
    logger.info("GET /samlAssertion");

    boolean isValidRequest = true;

    if (isValidRequest) {
      logger.info("Successful samlAssertion, redirecting to /home");
      Cookie cookie = new Cookie(Constants.CookieName, "CK123456789");
      cookie.setMaxAge(Constants.CookieExpirySeconds); // expires in 5 minutes days
      response.addCookie(cookie);
      return new RedirectView("/home");
    } else {
      logger.error("Error in samlAssertion");
      model.addAttribute("errorMsg", "Invalid login credentials!!!");
      return new RedirectView("SAMLRequestUrlInIdP");
    }
  }

  @GetMapping("/test")
  public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
      Model model) {
    model.addAttribute("name", name);
    return "test";
  }

}
