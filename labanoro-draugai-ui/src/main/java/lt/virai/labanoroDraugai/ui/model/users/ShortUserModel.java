package lt.virai.labanoroDraugai.ui.model.users;

import lt.virai.labanoroDraugai.domain.entities.User;

/**
 * Created by Mantas on 4/30/2016.
 */
public class ShortUserModel {
    private Integer id;

    private String email;

    private String fullName;

    public ShortUserModel(User user) {
        id = user.getId();
        email = user.getEmail();
        fullName = user.getName() + ' ' + user.getSurname();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
