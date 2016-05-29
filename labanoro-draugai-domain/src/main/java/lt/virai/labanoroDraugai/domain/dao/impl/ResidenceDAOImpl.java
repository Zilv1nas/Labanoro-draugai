package lt.virai.labanoroDraugai.domain.dao.impl;

import lt.virai.labanoroDraugai.domain.dao.AbstractDAO;
import lt.virai.labanoroDraugai.domain.dao.ResidenceDAO;
import lt.virai.labanoroDraugai.domain.entities.Reservation;
import lt.virai.labanoroDraugai.domain.entities.Residence;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceListModel;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchRequest;
import lt.virai.labanoroDraugai.domain.model.search.ResidenceSearchResponse;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Mantas on 4/19/2016.
 */
@Stateless
public class ResidenceDAOImpl extends AbstractDAO<Residence> implements ResidenceDAO {
    @Override
    public ResidenceSearchResponse searchResidences(ResidenceSearchRequest request) {
        Objects.requireNonNull(request);

        final int to = request.getPage() * request.getPageSize();
        final int from = to - request.getPageSize();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Residence> cq = builder.createQuery(Residence.class);
        Root<Residence> root = cq.from(Residence.class);
        cq.where(buildSearchPredicates(root, cq, builder, request));

        CriteriaQuery<Long> cqTotal = builder.createQuery(Long.class);
        Root<Residence> rootTotal = cqTotal.from(Residence.class);
        cqTotal.select(builder.count(rootTotal));
        cqTotal.where(buildSearchPredicates(rootTotal, cqTotal, builder, request));

        long total = entityManager.createQuery(cqTotal).getSingleResult();
        List<ResidenceListModel> residences = entityManager.createQuery(cq)
                .setFirstResult(from)
                .setMaxResults(to)
                .getResultList().stream().map(this::mapToListModel).collect(Collectors.toList());

        return new ResidenceSearchResponse(residences, total);
    }

    private ResidenceListModel mapToListModel(Residence residence) {
        ResidenceListModel model = new ResidenceListModel();
        model.setId(residence.getId());
        model.setName(residence.getName());
        model.setAddress(residence.getAddress());
        model.setCapacity(residence.getCapacity());
        model.setImage(residence.getImage());
        model.setDescription(residence.getDescription());
        model.setWeeklyPrice(residence.getWeeklyPrice());
        model.setAvailableFrom(residence.getAvailableFrom());
        model.setAvailableUntil(residence.getAvailableUntil());
        model.setDateOfRegistration(residence.getDateOfRegistration());

        return model;
    }

    private Predicate buildSearchPredicates(Root<Residence> root, CriteriaQuery cq, CriteriaBuilder builder, ResidenceSearchRequest request) {
        List<Predicate> predicates = new ArrayList<>();
        if (request.getTitle() != null) {
            predicates.add(builder.like(root.get("name"), "%" + request.getTitle() + "%"));
        }

        if (request.getAddress() != null) {
            predicates.add(builder.like(root.get("address"), "%" + request.getAddress() + "%"));
        }

        if (request.getPriceTo() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("weeklyPrice"), request.getPriceTo()));
        }

        if (request.getPriceFrom() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("weeklyPrice"), request.getPriceFrom()));
        }

        if (request.getUnoccupiedFrom() != null || request.getUnoccupiedTo() != null) {
            Subquery<Integer> sq = cq.subquery(Integer.class);
            Join<Residence, Reservation> join = sq.from(Residence.class).join("reservations");

            Predicate fromPredicate = null;
            Predicate toPredicate = null;
            if (request.getUnoccupiedFrom() != null) {
                fromPredicate = builder.greaterThanOrEqualTo(join.get("dateTo"), request.getUnoccupiedFrom());
            }

            if (request.getUnoccupiedTo() != null) {
                toPredicate = builder.lessThanOrEqualTo(join.get("dateFrom"), request.getUnoccupiedTo());
            }

            if (fromPredicate != null && toPredicate != null) {
                sq.where(builder.and(fromPredicate, toPredicate));
            } else if (fromPredicate != null) {
                sq.where(fromPredicate);
            } else if (toPredicate != null) {
                sq.where(toPredicate);
            }

            sq.distinct(true);
            sq.select(join.get("residence").get("id"));

            predicates.add(root.get("id").in(sq).not());

        }

        return predicates.stream().reduce(builder::and).orElse(builder.and());
    }
}
