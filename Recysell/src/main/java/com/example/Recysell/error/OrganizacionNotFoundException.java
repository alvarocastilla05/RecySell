package com.example.Recysell.error;

public class OrganizacionNotFoundException extends NotFoundException{

    public OrganizacionNotFoundException(){
        super("Organización no encontrada.");
    }

    public OrganizacionNotFoundException(Long id){
        super("Organización con id: " + id + " no encontrada.");
    }
}
