package com.xgaslan.data.mappers;

import com.xgaslan.data.entities.Department;
import com.xgaslan.data.entities.DepartmentText;
import com.xgaslan.data.models.DepartmentModel;

public class DepartmentMapper {

    public static DepartmentModel.DepartmentViewModel toDepartmentViewModel(Department entity) {
        if (entity == null) {
            return null;
        }

        return new DepartmentModel.DepartmentViewModel(
                entity.getId(),
                entity.getTexts().stream()
                        .map(DepartmentMapper::toDepartmentTextViewModel)
                        .toList());

    }

    public static DepartmentModel.DepartmentTextViewModel toDepartmentTextViewModel(DepartmentText entity) {
        if (entity == null) {
            return null;
        }

        return new DepartmentModel.DepartmentTextViewModel(
                entity.getId(),
                entity.getLanguageId(),
                entity.getText());
    }
}
