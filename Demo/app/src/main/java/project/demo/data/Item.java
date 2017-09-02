package project.demo.data;

import java.io.Serializable;

/**
 * Created by hassan on 8/31/2017.
 */

public class Item  implements Serializable{
    private String description;
    private String title;
    private String imageUrl;


    public Item(String title, String details, String imageUrl) {
        this.description = details;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
