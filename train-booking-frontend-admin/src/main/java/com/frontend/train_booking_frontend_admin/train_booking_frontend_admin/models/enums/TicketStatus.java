package com.frontend.train_booking_frontend_admin.train_booking_frontend_admin.models.enums;

public enum TicketStatus {
	ENABLE("Hoạt Động"), 
	DISABLED("Không Hoạt Động");

	private final String displayName;

	TicketStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
