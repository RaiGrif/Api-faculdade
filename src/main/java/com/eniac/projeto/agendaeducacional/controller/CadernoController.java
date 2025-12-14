package com.eniac.projeto.agendaeducacional.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eniac.projeto.agendaeducacional.entity.Caderno;
import com.eniac.projeto.agendaeducacional.entity.StatusCaderno;
import com.eniac.projeto.agendaeducacional.service.CadernoService;
import com.eniac.projeto.agendaeducacional.DTO.CadernoDTO;
import com.eniac.projeto.agendaeducacional.DTO.CadernoDetalheDto;
import com.eniac.projeto.agendaeducacional.DTO.CategoriaRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/cadernos")
public class CadernoController {
    private CadernoService cadernoService;

    public CadernoController(CadernoService cadernoService_) {
        this.cadernoService = cadernoService_;
    }

    @GetMapping("/usuario/{idUsuario}")
    List<CadernoDTO> listarCadernos(@RequestParam(name="statusCaderno",required = false) String statusCaderno, @RequestParam(required = false, name = "sortBy") List<String> sortBy, @RequestParam(defaultValue = "asc", name = "direction") String direction, @RequestParam(required = false, name="nomeCategoria") String nomeCategoria, @PathVariable("idUsuario") Long id) {
        
        String sortDirection = direction.equalsIgnoreCase("desc") ? "desc" : "asc";
        
        StatusCaderno statusEnum = null;
    if (statusCaderno != null) {
        try {
            statusEnum = StatusCaderno.valueOf(statusCaderno.toUpperCase());
        } catch (IllegalArgumentException e) {
            // opcional: lançar exceção personalizada ou ignorar
        }
    }   
        return cadernoService.list( id ,statusEnum, sortBy, sortDirection, nomeCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadernoDetalheDto> buscarPorId (@PathVariable("id") Long id) {
        return cadernoService.buscarPorId(id).map(caderno -> ResponseEntity.ok(new CadernoDetalheDto(caderno)))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Caderno create (@RequestBody Caderno caderno) {
       Caderno salvo = cadernoService.create(caderno, caderno.getUsuario().getId());
        return salvo;
    }

    @PutMapping("/{id}")
    public String update (@PathVariable("id") Long id_caderno, @RequestBody Caderno caderno)   {
        return cadernoService.update(caderno, id_caderno);
    }

    @PutMapping("/{idCaderno}/categorias")
    public ResponseEntity<Caderno> updateCategorias(@PathVariable("idCaderno") Long idCaderno, @RequestBody List<CategoriaRequest> categoriasRequest) {
        return cadernoService.atualizarCategorias(idCaderno, categoriasRequest);
    }

    @DeleteMapping("/{id}")
    String delete (@PathVariable("id") Long id) {
        return cadernoService.deleteById(id);
    }
    
}
