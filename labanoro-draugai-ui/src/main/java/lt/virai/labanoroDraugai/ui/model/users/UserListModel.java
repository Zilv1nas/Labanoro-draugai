package lt.virai.labanoroDraugai.ui.model.users;

import lt.virai.labanoroDraugai.domain.entities.User;

import java.time.LocalDate;

/**
 * Created by Žilvinas on 2016-05-28.
 */
public class UserListModel {
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private LocalDate registrationDate;
    private String role;
    private boolean recommendedByCurrentUser;

    public UserListModel() {
    }

    public UserListModel(User user) {
        id = user.getId();
        email = user.getEmail();
        name = user.getName();
        surname = user.getSurname();
        registrationDate = user.getRegistrationDate();
        role = user.getRole().name();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isRecommendedByCurrentUser() {
        return recommendedByCurrentUser;
    }

    public void setRecommendedByCurrentUser(boolean recommendedByCurrentUser) {
        this.recommendedByCurrentUser = recommendedByCurrentUser;
    }
}
