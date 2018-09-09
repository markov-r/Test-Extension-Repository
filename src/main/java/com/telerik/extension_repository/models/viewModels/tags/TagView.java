package com.telerik.extension_repository.models.viewModels.tags;

public class TagView {

    private Integer id;

    private String name;

    public TagView() {
    }

    public TagView(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
