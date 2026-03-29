package com.jgohub.ms_bulletin.mapping;

import com.jgohub.ms_bulletin.dto.Bulletin;
import com.jgohub.ms_bulletin.entities.BulletinEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BulletinMapper {
    Bulletin toBulletin(BulletinEntity bulletinEntity);
    BulletinEntity fromBulletin(Bulletin bulletin);
}
