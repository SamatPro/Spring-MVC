package ru.kpfu.itis.services;

import ru.kpfu.itis.models.Picture;
import ru.kpfu.itis.models.Review;

import java.util.List;

public interface Service {
  List<Picture> getRandomPictures();
  List<Review> getAllReviews();
}
