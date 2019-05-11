package ru.kpfu.itis.repositories.cooperativeTours;

import ru.kpfu.itis.models.CooperativeTour;
import ru.kpfu.itis.repositories.CrudRepository;

import java.util.List;

public interface CooperativeToursRepository extends CrudRepository<CooperativeTour> {
    void addInvitation(Long orderId, Long clientId);
    List<CooperativeTour> showUnConsentedTours(Long id);
    void updateConsent(Long id);
    void deleteConsent(Long id);
}
