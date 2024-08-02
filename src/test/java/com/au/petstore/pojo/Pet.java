
package com.au.petstore.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter @Setter
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pet {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("status")
    private String status;

    public Pet(){}
    public Pet(Integer id, Category category, String name, List<String> photoUrls, List<Tag> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public static PetBuilder builder() {
        return new PetBuilder();
    }


    public static class PetBuilder {
        private Integer id;
        private Category category;
        private String name;
        private List<String> photoUrls;
        private List<Tag> tags;
        private String status;

        PetBuilder() {
        }

        public PetBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public PetBuilder category(Category category) {
            this.category = category;
            return this;
        }

        public PetBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PetBuilder photoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public PetBuilder tags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public PetBuilder status(String status) {
            this.status = status;
            return this;
        }

        public Pet build() {
            return new Pet(id, category, name, photoUrls, tags, status);
        }

        public String toString() {
            return "Pet.PetBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", photoUrls=" + this.photoUrls + ", tags=" + this.tags + ", status=" + this.status + ")";
        }


    }
}
