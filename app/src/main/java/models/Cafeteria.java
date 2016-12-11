package models;

/**
 * Created by Nico on 9/22/2016.
 */

public class Cafeteria {
    private String name;
    private String email;
    private String phone;
    private String image;
    private String queue;



    public Cafeteria() {
    }

    public Cafeteria(String name, String email, String phone, String image) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.queue = "min";

    }
    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public String getQueue(){
        return queue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
