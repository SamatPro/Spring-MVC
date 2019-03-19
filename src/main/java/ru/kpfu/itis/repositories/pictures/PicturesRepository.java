package ru.kpfu.itis.repositories.pictures;

import ru.kpfu.itis.models.Picture;
import ru.kpfu.itis.repositories.CrudRepository;

import java.util.List;

public interface PicturesRepository extends CrudRepository<Picture> {
    List<Picture> findAllPicturesByCountryId(Long id);
    List<Picture> findAllPicturesByCountryName(String name);

}
