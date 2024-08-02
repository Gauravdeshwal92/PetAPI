
package com.au.petstore.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter @Setter
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tag {

    public Tag(){}

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;

    Tag(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static TagBuilder builder() {
        return new TagBuilder();
    }

    public static class TagBuilder {
        private Integer id;
        private String name;

        TagBuilder() {
        }

        public TagBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public TagBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Tag build() {
            return new Tag(id, name);
        }

        public String toString() {
            return "Tag.TagBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
