package com.example.Recysell.query;

import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.util.SearchCriteria;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductoSpecificationBuilder extends GenericSpecificationBuilder<Producto> {

    public ProductoSpecificationBuilder(List<SearchCriteria> params) {
        super(params);
    }

    @Override
    public Specification<Producto> build() {
        if (params == null || params.isEmpty()) {
            return Specification.where(null);
        }

        Specification<Producto> result = Specification.where(null);

        for (SearchCriteria criteria : params) {
            Specification<Producto> spec = switch (criteria.key().toLowerCase()) {
                case "categoria" -> Producto.byCategoria(criteria);
                case "precio" -> Producto.byRangoPrecio(criteria);
                case "usuario" -> Producto.byUsuario(criteria);
                default -> super.build(criteria);
            };

            if (spec != null) {
                result = result.and(spec);
            }
        }

        return result;
    }

}
