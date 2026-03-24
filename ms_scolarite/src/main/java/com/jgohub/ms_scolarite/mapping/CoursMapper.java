package com.jgohub.ms_scolarite.mapping;

import com.jgohub.ms_scolarite.dto.Cours;
import com.jgohub.ms_scolarite.entities.CoursEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CoursMapper {
    Cours toCours(CoursEntity coursEntity);
    CoursEntity fromCours(Cours cours);
}
