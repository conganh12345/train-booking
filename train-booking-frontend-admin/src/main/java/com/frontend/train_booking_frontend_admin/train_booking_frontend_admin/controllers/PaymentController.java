package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.Payment;
import com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.services.PaymentService;


@Controller
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@GetMapping("/index")
	public String index(Model model) {
		List<Payment> payments = paymentService.getAllPayments();

		model.addAttribute("page", "payment").addAttribute("payments", payments);

		return "payment/index";
	}

	@GetMapping("/create")
	public String createPayment(Model model) {
		Payment payment = new Payment(); 
		payment.setAmount(0); 
		model.addAttribute("payment", payment); 
		return "payment/create"; 
	}

	@PostMapping("/create")
	public String create(@ModelAttribute() Payment payment, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (paymentService.addPayment(payment)) {
			redirectAttributes.addFlashAttribute("success", "Thêm mới thanh toán thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Thêm mới thanh toán thất bại!");
		}
		return "redirect:/payment/index";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Payment payment = paymentService.getPaymentById(id);

		model.addAttribute("page", "payment").addAttribute("payment", payment);

		return "payment/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Integer id, @ModelAttribute Payment payment, BindingResult result,
			RedirectAttributes redirectAttributes) {
		payment.setId(id);
		if (paymentService.updatePayment(payment)) {
			redirectAttributes.addFlashAttribute("success", "Cập nhật thanh toán thành công!");
		} else {
			redirectAttributes.addFlashAttribute("error", "Cập nhật thanh toán thất bại!");
		}
		return "redirect:/payment/index";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deletepayment(@PathVariable Integer id) {
		if (paymentService.deletePayment(id)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể xóa thanh toán.");
		}
	}
}
