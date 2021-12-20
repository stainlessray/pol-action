package dev.raycool.polaction.user.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @see dev.raycool.polaction.user.api.entity.UserEntity class defines the relational table in the database named 'pol_action', the table name is 'users'.
 * @see dev.raycool.polaction.user.api.ui.model.response.UserRest class defines the http response body for user creation api calls.
 * @see dev.raycool.polaction.user.api.ui.model.request.UserDetailsRequestModel class defines the structure of the body of a call to the users api. The api call body should be json text.
 * @see dev.raycool.polaction.user.api.shared.dto.UserDto class
 *
 * **/

@Entity(name = "users")
public class UserEntity implements Serializable {

    public static final long serialVersionUID = 8675627962622957962L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String userName;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false)
    private String encryptedPassword;

    @Column
    private String emailVerificationToken;

    @Column(nullable = false)
    private Boolean emailVerificationTokenStatus = false;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Boolean getEmailVerificationTokenStatus() {
        return emailVerificationTokenStatus;
    }

    public void setEmailVerificationTokenStatus(Boolean emailVerificationTokenStatus) {
        this.emailVerificationTokenStatus = emailVerificationTokenStatus;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "REDACTED" + '\'' +
                ", encryptedPassword='" + encryptedPassword + '\'' +
                ", emailVerificationToken='" + emailVerificationToken + '\'' +
                ", emailVerificationTokenStatus=" + emailVerificationTokenStatus +
                '}';
    }
}
