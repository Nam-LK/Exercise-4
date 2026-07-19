package com.javaweb.builder;

public class CustomerSearchBuilder {
    private String customerName;
    private String phoneNumber;
    private String email;
    private Long staffId;

    public CustomerSearchBuilder(Builder builder) {
        this.customerName = builder.customerName;
        this.phoneNumber = builder.phoneNumber;
        this.email = builder.email;
        this.staffId = builder.staffId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Long getStaffId() {
        return staffId;
    }

    public static class Builder {
        private String customerName;
        private String phoneNumber;
        private String email;
        private Long staffId;

        public Builder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }
        public CustomerSearchBuilder build() {
            return new CustomerSearchBuilder(this);
        }
    }
}
