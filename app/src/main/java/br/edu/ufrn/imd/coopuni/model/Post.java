package br.edu.ufrn.imd.coopuni.model;

import java.io.Serializable;
import java.util.Date;



public class Post implements Serializable {

  private long id;

  private boolean type;

  private String description;

  private int likes;

  private int downvotes;

  private Date createdAt;

  private Date updatedAt;

  private String area;

  private String category;

  private Geolocation geolocation;

  private String image;

  private String username;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public boolean isType() {
    return type;
  }

  public void setType(boolean type) {
    this.type = type;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public int getDownvotes() {
    return downvotes;
  }

  public void setDownvotes(int downvotes) {
    this.downvotes = downvotes;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Geolocation getGeolocation() {
    return geolocation;
  }

  public void setGeolocation(Geolocation geolocation) {
    this.geolocation = geolocation;
  }
}