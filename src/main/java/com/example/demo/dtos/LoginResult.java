package com.example.demo.dtos;

public class LoginResult {
    private boolean success;
    private Long userId;
    private String role;
    private Long companyId;

    public LoginResult(boolean success, Long userId, String role, Long comapyId) {
        this.success = success;
        this.userId = userId;
        this.role = role;
        this.companyId = comapyId;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
}
