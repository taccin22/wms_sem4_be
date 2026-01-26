package com.example.demo.dtos;

public class LoginResult {
    private boolean success;
    private Long userId;
    private String role;

    public LoginResult(boolean success, Long userId, String role) {
        this.success = success;
        this.userId = userId;
        this.role = role;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    
}
