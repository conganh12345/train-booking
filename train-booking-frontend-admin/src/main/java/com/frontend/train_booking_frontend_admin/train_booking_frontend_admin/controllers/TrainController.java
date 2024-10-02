package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Train;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.TrainService;

@Controller
@RequestMapping("/train")
public class TrainController {
	@Autowired
	private TrainService trainService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Train> trains = trainService.getAllTrains();

		model.addAttribute("page", "train").addAttribute("trains", trains);

		return "train/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("page", "train");

		return "train/create";
	}
	
	@PostMapping("/create")
    public String create(@ModelAttribute() Train train, BindingResult result, Model model) {
		 if (result.hasErrors()) {
		        model.addAttribute("page", "train");  
		        return "train/create";
		    }

        trainService.addTrain(train); 

        return "redirect:/train/index"; 
    }
}
