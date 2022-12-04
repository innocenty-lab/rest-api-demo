package com.enigma.repository.spec;

import com.enigma.util.QueryOperator;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
public class SearchCriteria {
    private String key;
    private QueryOperator operator;
    private Object value;

    public SearchCriteria(Builder builder) {
        this.key = builder.key;
        this.operator = builder.queryOperator;
        this.value = builder.value;
    }

    @ToString
    public static class Builder {
        private String key;
        private QueryOperator queryOperator;
        private Object value;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setQueryOperator(QueryOperator queryOperator) {
            this.queryOperator = queryOperator;
            return this;
        }

        public Builder setValue(Object value) {
            this.value = value;
            return this;
        }

        public SearchCriteria build() {
            return new SearchCriteria(this);
        }
    }
}
