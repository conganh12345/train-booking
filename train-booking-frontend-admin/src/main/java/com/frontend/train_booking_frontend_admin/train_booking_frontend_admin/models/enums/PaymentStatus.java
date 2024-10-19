package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum PaymentStatus {
	PENDING("Đang chờ"),         
    COMPLETED("Hoàn thành"),       
    FAILED("Thất bại"),          
    CANCELLED("Đã hủy");    
    
    private final String displayName;

    PaymentStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
