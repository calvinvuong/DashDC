
package app.controller;

import org.springframework.ui.Model; //For Model

import org.springframework.stereotype.Controller; //For @Controller
import org.springframework.web.bind.annotation.GetMapping; //For @GetMapping
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import app.Application;
import app.FormCapture;

@Controller
public class DashboardController {
      // Controll page address
      @GetMapping("/")
      public String dashboard(Model model, @ModelAttribute FormCapture form) {

            // Set default page
            if (Application.currentPage == null) {
                  Application.currentPage = Application.dashboard.getPages().get(0);
            }
            
            model.addAttribute("currentPage", Application.currentPage);
            model.addAttribute("pageList", Application.dashboard.getPages());
            model.addAttribute("tileList", Application.currentPage.getTiles());
            return "page";
      }

      @RequestMapping("/changeDashboard")
      public RedirectView changeDashboard(@ModelAttribute FormCapture form) {
            Application.currentPage = Application.dashboard.getDashboardPage(form.getId());
            return new RedirectView("/");
      }

}
