
package com.au.petstore.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {

    public Category(){}

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

    Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    public static class CategoryBuilder {
        private Integer id;
        private String name;

        CategoryBuilder() {
        }

        public CategoryBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(id, name);
        }

        public String toString() {
            return "Category.CategoryBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
