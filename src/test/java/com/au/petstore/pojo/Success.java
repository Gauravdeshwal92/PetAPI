package com.au.petstore.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "code",
            "type",
            "message"
    })

    public class Success {

        @JsonProperty("code")
        private Integer code;
        @JsonProperty("type")
        private String type;
        @JsonProperty("message")
        private String message;

        @JsonProperty("code")
        public Integer getCode() {
            return code;
        }

        @JsonProperty("code")
        public void setCode(Integer code) {
            this.code = code;
        }

        @JsonProperty("type")
        public String getType() {
            return type;
        }

        @JsonProperty("type")
        public void setType(String type) {
            this.type = type;
        }

        @JsonProperty("message")
        public String getMessage() {
            return message;
        }

        @JsonProperty("message")
        public void setMessage(String message) {
            this.message = message;
        }

    }


