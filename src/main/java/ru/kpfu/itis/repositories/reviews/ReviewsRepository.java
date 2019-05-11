package ru.kpfu.itis.repositories.reviews;

import ru.kpfu.itis.models.Review;
import ru.kpfu.itis.repositories.CrudRepository;

public interface ReviewsRepository extends CrudRepository<Review> {
  void addReview(Long clientId, String text);
}
