package com.example.Recysell.categoria.service;

import com.example.Recysell.categoria.dto.EditarCategoriaCmd;
import com.example.Recysell.categoria.dto.GetCategoriaDto;
import com.example.Recysell.categoria.model.Categoria;
import com.example.Recysell.categoria.repo.CategoriaRepository;
import com.example.Recysell.error.CategoriaNotFoundException;
import com.example.Recysell.files.model.FileMetadata;
import com.example.Recysell.files.service.StorageService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final EntityManager entityManager;
    private final StorageService storageService;



    //Listar Categorias.
    public Page<GetCategoriaDto> findAll(Pageable pageable, boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedCategoriaFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetCategoriaDto> result = categoriaRepository.findAllCategorias(pageable);
        session.disableFilter("deletedCategoriaFilter");

        if(result.isEmpty()){
            throw new CategoriaNotFoundException();
        }
        return result;
    }

    //Obtener categoria por id.
    public Categoria findById(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isPresent() && !categoria.get().isDeleted()){
            return categoria.get();
        }else{
            throw new CategoriaNotFoundException(id);
        }
    }

    //Añadir categoria.
    public Categoria save(EditarCategoriaCmd nuevo, MultipartFile file){
        FileMetadata fileMetadata = storageService.store(file);

        Categoria categoria = Categoria.builder()
                .nombre(nuevo.nombre())
                .imagen(fileMetadata.getFilename())
                .build();

        return categoriaRepository.save(categoria);

    }

    //Editar categoria.
    public Categoria edit(Long id, EditarCategoriaCmd edit, MultipartFile file){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent() && !categoria.get().isDeleted()){
            return categoria.
                    map(c -> {
                        c.setNombre(edit.nombre());
                        c.setImagen(storageService.store(file).getFilename());
                        return categoriaRepository.save(c);
                    }).get();
        }else{
            throw new CategoriaNotFoundException(id);
        }
    }

    //Eliminar categoria.
    public void deleteById(Long id){
        categoriaRepository.deleteById(id);
    }
}
