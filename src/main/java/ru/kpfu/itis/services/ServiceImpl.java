package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.Picture;
import ru.kpfu.itis.models.Review;
import ru.kpfu.itis.repositories.pictures.PicturesRepository;
import ru.kpfu.itis.repositories.reviews.ReviewsRepository;

import java.util.List;

@Component("service")
public class ServiceImpl implements Service{
  @Autowired
  @Qualifier("picturesRepositoryJdbcTemplateImpl")
  private PicturesRepository picturesRepository;

  @Autowired
  @Qualifier("reviewsRepositoryJdbcTemplateImpl")
  private ReviewsRepository reviewsRepository;

  @Override
  public List<Picture> getRandomPictures() {
    return picturesRepository.findSomeRandomPictures();
  }

  @Override
  public List<Review> getAllReviews() {
    return reviewsRepository.findAll();
  }


}
