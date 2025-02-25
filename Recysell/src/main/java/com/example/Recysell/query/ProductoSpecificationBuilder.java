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

        Specification<Producto> result = null;

        SearchCriteria first = params.get(0);

        if (first.key().equalsIgnoreCase("categoria"))
            result = Producto.byCategoria(first);
        else if (first.key().equalsIgnoreCase("precio"))
            result = Producto.byRangoPrecio(first);
        else if (first.key().equalsIgnoreCase("usuario"))
            result = Producto.byUsuario(first);
        else
            result = super.build(first);

        for (int i = 1; i < params.size(); i++) {

            SearchCriteria criteria = params.get(i);

            if (criteria.key().equalsIgnoreCase("categoria"))
                result = result.and(Producto.byCategoria(criteria));
            else if (criteria.key().equalsIgnoreCase("precio"))
                result = result.and(Producto.byRangoPrecio(criteria));
            else if (criteria.key().equalsIgnoreCase("usuario"))
                result = result.and(Producto.byUsuario(criteria));
            else
                result = result.and(super.build(criteria));
        }

        return result;
    }
}
