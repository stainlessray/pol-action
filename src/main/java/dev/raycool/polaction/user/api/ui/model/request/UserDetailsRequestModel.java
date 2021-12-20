package dev.raycool.polaction.user.api.ui.model.request;

/**
 * @see dev.raycool.polaction.user.api.ui.model.request.UserDetailsRequestModel class defines the structure of the body of a call to the users api. The api call body should be json text.
 * @see dev.raycool.polaction.user.api.ui.model.response.UserRest class defines the http response body for user creation api calls.
 * @see dev.raycool.polaction.user.api.entity.UserEntity class defines the relational table in the database named 'pol_action', the table name is 'users'.
 * @see dev.raycool.polaction.user.api.shared.dto.UserDto class
 *
 * **/
public class UserDetailsRequestModel {

    private String userName;
    private String email;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
