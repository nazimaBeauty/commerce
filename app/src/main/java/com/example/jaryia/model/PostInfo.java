package com.example.jaryia.model;

import java.util.List;

public class PostInfo {
    private String id, author, description, whatsapp_number, add_date;
    private int itemchoice;
    private List<SocialMedia> numbers;
    private List<Images> ims;

    public PostInfo(String id, String author, String description, String whatsapp_number, String add_date, List<SocialMedia> numbers, List<Images> ims, int itemchoice) {
        this.itemchoice = itemchoice;
        this.id = id;
        this.author = author;
        this.description = description;
        this.whatsapp_number = whatsapp_number;
        this.add_date = add_date;
        this.numbers = numbers;
        this.ims = ims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWhatsapp_number() {
        return whatsapp_number;
    }

    public void setWhatsapp_number(String whatsapp_number) {
        this.whatsapp_number = whatsapp_number;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

    public List<SocialMedia> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<SocialMedia> numbers) {
        this.numbers = numbers;
    }

    public List<Images> getIms() {
        return ims;
    }

    public void setIms(List<Images> ims) {
        this.ims = ims;
    }

    public int getItemchoice() {
        return itemchoice;
    }

    public void setItemchoice(int itemchoice) {
        this.itemchoice = itemchoice;
    }
}
