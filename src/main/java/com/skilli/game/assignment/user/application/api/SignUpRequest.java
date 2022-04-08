package com.skilli.game.assignment.user.application.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class SignUpRequest implements Serializable {
    private static final long serialVersionUID=1l;

    @Schema(example = "demouser")
    private String username;
    @Schema(example = "FirstName")
    private String firstName;
    @Schema(example = "LastName")
    private String lastName;
    @Schema(example = "Password")
    private String password;
    @Schema(example = "EmailId")
    private String emailId;

    public SignUpRequest() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SignUpRequest)) return false;
        final SignUpRequest other = (SignUpRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$emailId = this.getEmailId();
        final Object other$emailId = other.getEmailId();
        if (this$emailId == null ? other$emailId != null : !this$emailId.equals(other$emailId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SignUpRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $emailId = this.getEmailId();
        result = result * PRIME + ($emailId == null ? 43 : $emailId.hashCode());
        return result;
    }

    public String toString() {
        return "SignUpRequest(username=" + this.getUsername() + ", firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", password=" + this.getPassword() + ", emailId=" + this.getEmailId() + ")";
    }
}
