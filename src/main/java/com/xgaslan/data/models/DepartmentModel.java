package com.xgaslan.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

public class DepartmentModel {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentViewModel {
        private UUID id;

        private List<DepartmentTextViewModel> texts;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentTextViewModel {

        private long id;

        private String languageId;

        private String text;
    }
}
