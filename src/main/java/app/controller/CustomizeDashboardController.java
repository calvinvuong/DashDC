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
public class CustomizeDashboardController {
    // Handles the request for customize
    @GetMapping("/customize/")
    public String customize(Model model) {

        // Pass attributes to thymeleaf
        model.addAttribute("pageList", Application.dashboard.getPages());

        return "customizeDashboard";
    }

    // Handles the request to create a new page
    // Points web browser to /customize
    @RequestMapping("/customize/newpage")
    public RedirectView newPage(@ModelAttribute FormCapture form) {

        Application.dashboard.addPage(form.getId(), form.getName());
        return new RedirectView("/customize/");
    }

    // Handles the request to select the page to be edited
    // Points web browser to /customize/page
    @RequestMapping("/customize/selectpage")
    public RedirectView selectPage(@ModelAttribute FormCapture form) {

        Application.currentPage = Application.dashboard.getDashboardPage(form.getId());

        // Brings the user to the customize page
        return new RedirectView("/customize/page/");
    }

    // Handles requests to customize a page
    // Serves up customizePage template 
    @RequestMapping("/customize/page/")
    public String customizePage(Model model, @ModelAttribute FormCapture form) {

        model.addAttribute("currentPage", Application.currentPage);
        model.addAttribute("pageList", Application.dashboard.getPages());
        model.addAttribute("tileList", Application.currentPage.getTiles());

        return "customizePage";
    }

    // Handles creating a new tile on the current page
    // Points web browser to /customize/page
    @RequestMapping("/customize/page/newtile")
    public RedirectView newTile(@ModelAttribute FormCapture form) {

        Application.currentPage.addTile(form.getId(), form.getName());

        return new RedirectView("/customize/page/");
    }

    // Handles selecting the tile to be edited
    // Points web browser to /customize/page/tile
    @RequestMapping("/customize/page/selecttile")
    public RedirectView selectTile(@ModelAttribute FormCapture form) {

        Application.currentTile = Application.currentPage.getTile(form.getId());

        return new RedirectView("/customize/page/tile/");
    }

    // Handles the customize tile page
    // Serves up the customizeTile template
    @GetMapping("/customize/page/tile/")
    public String customizeTile(Model model) {

        model.addAttribute("assetList", Application.currentTile.getAssets());
        model.addAttribute("assetTypes", Application.ASSET_TYPES);

        return "customizeTile";
    }


    // Handles deleting a tile inside the page
    // Serves up the customizePage template
    @RequestMapping("/customize/page/deletetile")
    public RedirectView deleteTile(@ModelAttribute FormCapture form) {
	Application.currentPage.deleteTile(form.getId()); // delete the tile with this id
	// go back to customize page
	return new RedirectView("/customize/page/");

    }
    
    // Handles deleting an asset inside the tile
    // Serves up the customizePage template
    @RequestMapping("/customize/page/tile/deleteasset")
    public RedirectView deleteAsset(@ModelAttribute FormCapture form) {
	Application.currentTile.deleteAsset(form.getId()); // delete the asset with this id
	// go back to customize page
	return new RedirectView("/customize/page/");
    }
    
    // Handles creating a new asset inside the tile
    // Serves up the newAsset template
    @RequestMapping("/customize/page/tile/newasset")
    public String newAsset(Model model, @ModelAttribute FormCapture form) {

        model.addAttribute("type", form.getType());

        return "newAsset";
    }

    // Handles making the new image
    // Points web browser to /customize/page/tile
    @RequestMapping("/customize/page/tile/newimage")
    public RedirectView newImage(@ModelAttribute FormCapture form) {

        int[] size = new int[] { form.getXsize(), form.getYsize() };
        int[] position = new int[] { form.getXpos(), form.getYpos() };

        Application.currentTile.addAssetImage(form.getId(), form.getName(), form.getLink(), size, position);

        return new RedirectView("/customize/page/tile/");
    }

    // Handles making the new list
    // Points web browser to /customize/page/tile
    @RequestMapping("/customize/page/tile/newlist")
    public RedirectView newList(@ModelAttribute FormCapture form) {

        Application.currentTile.addAssetList(form.getId(), form.getName(), form.getType());

        return new RedirectView("/customize/page/tile/");
    }

    // Handles making the new note
    // Points web browser to /customize
    @RequestMapping("/customize/page/tile/newnote")
    public RedirectView newNote(@ModelAttribute FormCapture form) {
        Application.currentTile.addAssetNote(form.getId(), form.getName());
        
        return new RedirectView("/customize/page/tile/");
    }

    // Handles making the new link
    // Points web browser to /customize/page/tile/
    @RequestMapping("/customize/page/tile/newlink")
    public RedirectView newLink(@ModelAttribute FormCapture form) {
        Application.currentTile.addAssetLink(form.getId(), form.getName(), form.getLink());
        
        return new RedirectView("/customize/page/tile/");
    }

}
