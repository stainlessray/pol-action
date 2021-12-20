package dev.raycool.polaction.user.api.ui.model.response;


/**
 * @see dev.raycool.polaction.user.api.ui.model.response.UserRest class defines the http response body for user creation api calls.
 * @see dev.raycool.polaction.user.api.entity.UserEntity class defines the relational table in the database named 'pol_action', the table name is 'users'.
 * @see dev.raycool.polaction.user.api.ui.model.request.UserDetailsRequestModel class defines the structure of the body of a call to the users api. The api call body should be json text.
 * @see dev.raycool.polaction.user.api.shared.dto.UserDto class
 *
 * **/
public class UserRest {

    private long id;
    private String userName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationTokenStatus;

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
}
