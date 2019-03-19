package ru.kpfu.itis.repositories.cooperativeTours;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.CooperativeTours;
import ru.kpfu.itis.repositories.CrudRepository;
import sun.util.resources.cldr.vai.LocaleNames_vai;

import java.util.List;

public interface CooperativeToursRepository extends CrudRepository<CooperativeTours> {
    void addInvitation(Long orderId, Long clientId);
    List<CooperativeTours> showUnConsentedTours(Long id);
    void updateConsent(Long id);
    void deleteConsent(Long id);
}
