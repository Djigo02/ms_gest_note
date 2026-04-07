package com.jgohub.ms_bulletin.services;

import com.jgohub.ms_bulletin.dto.Bulletin;
import com.jgohub.ms_bulletin.entities.BulletinEntity;

public interface IBulletinServices {
    Bulletin createBulletin(String matricule);
    Bulletin getBulletinById(Long id);
    Bulletin getBulletinByMatricule(String matricule);
}
