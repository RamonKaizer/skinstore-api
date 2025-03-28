package com.ramonkaizer.skinstore.specification;

import com.ramonkaizer.skinstore.domain.Skin;
import com.ramonkaizer.skinstore.dto.request.SkinConsultaRequest;
import com.ramonkaizer.skinstore.enums.*;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SkinSpecification {

    public static Specification<Skin> toSpec(SkinConsultaRequest filtros) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("status"), StatusSkin.DISPONIVEL));

            if (filtros.getNome() != null && !filtros.getNome().trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filtros.getNome().toLowerCase() + "%"));
            }

            if (filtros.getPrecoMin() != null && filtros.getPrecoMax() != null) {
                predicates.add(criteriaBuilder.between(root.get("preco"), filtros.getPrecoMin(), filtros.getPrecoMax()));
            } else if (filtros.getPrecoMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("preco"), filtros.getPrecoMin()));
            } else if (filtros.getPrecoMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("preco"), filtros.getPrecoMax()));
            }

            if (filtros.getRaridade() != null) {
                predicates.add(criteriaBuilder.equal(root.get("raridade"), filtros.getRaridade()));
            }

            if (filtros.getFloatMin() != null && filtros.getFloatMax() != null) {
                predicates.add(criteriaBuilder.between(root.get("floatValue"), filtros.getFloatMin(), filtros.getFloatMax()));
            } else if (filtros.getFloatMin() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("floatValue"), filtros.getFloatMin()));
            } else if (filtros.getFloatMax() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("floatValue"), filtros.getFloatMax()));
            }

            if (filtros.getCategoria() != null) {
                predicates.add(criteriaBuilder.equal(root.get("categoria"), filtros.getCategoria()));
            }

            if (filtros.getSortBy() != null) {
                String sortField;
                if (filtros.getSortBy() == SortBy.PRECO) {
                    sortField = "preco";
                } else {
                    sortField = "floatValue";
                }
                if (filtros.getSortDirection() == SortDirection.ASC) {
                    query.orderBy(criteriaBuilder.asc(root.get(sortField)));
                } else {
                    query.orderBy(criteriaBuilder.desc(root.get(sortField)));
                }
            }

            query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return query.getRestriction();
        };
    }
}