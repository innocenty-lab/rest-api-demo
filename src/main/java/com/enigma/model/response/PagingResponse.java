package com.enigma.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

@ToString
public class PagingResponse<T> extends CommonResponse {
    @Setter @Getter
    private List<T> data;

    @Setter @Getter
    private Long count;

    @Setter @Getter
    private int totalPage;

    @Setter @Getter
    private int page;

    @Setter @Getter
    private int size;

    public PagingResponse(String message, Page<T> page) {
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = page.getContent();
        this.count = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
    }
}
