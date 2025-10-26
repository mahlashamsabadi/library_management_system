package com.library_managment.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefaultResponseDto<T> {
    private T data;
    private HttpStatus status;
    private String message;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PageableResponseDto<T> {
        private List<T> data;
        private HttpStatus status;
        private String message;
        private int page;
        private int size;
        private long total;
    }

}
