package com.telerik.extension_repository.models.bindingModels.extensions;

public class ExtensionModel {

    private String name;

    private String description;

    private Integer number_of_downloads;

    public ExtensionModel() {
    }

    public ExtensionModel(String name, String description, Integer number_of_downloads) {
        this.name = name;
        this.description = description;
        this.number_of_downloads = number_of_downloads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber_of_downloads() {
        return number_of_downloads;
    }

    public void setNumber_of_downloads(Integer number_of_downloads) {
        this.number_of_downloads = number_of_downloads;
    }
}
