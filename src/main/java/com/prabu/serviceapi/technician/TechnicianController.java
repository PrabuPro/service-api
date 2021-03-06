package com.prabu.serviceapi.technician;

import com.prabu.serviceapi.pagination.PaginationPage;
import com.prabu.serviceapi.technician.model.TechnicianDTO;
import com.prabu.serviceapi.technician.model.TechnicianListDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TechnicianController.API_V_1_TECHNICIAN)
public class TechnicianController {
    public static final String API_V_1_TECHNICIAN = "/api/v1/technician";

    private final TechnicianService technicianService;

    public TechnicianController(TechnicianService technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("/list")
    public ResponseEntity<TechnicianListDTO> getTechnicianList(){
        return new ResponseEntity<>(new TechnicianListDTO(technicianService.getTechnicianList()), HttpStatus.OK);
    }

    @GetMapping("/grid")
    public ResponseEntity<Page<TechnicianDTO>> getTechnicianGrid(PaginationPage paginationPage){
        return new ResponseEntity<>(technicianService.getTechnicianGrid(paginationPage), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<TechnicianDTO> saveTechnician(@RequestBody TechnicianDTO technicianDTO){
        return new ResponseEntity<>(technicianService.saveTechnician(technicianDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianDTO> updateTechnician(@PathVariable Long id,
                                                          @RequestBody TechnicianDTO technicianDTO){
        return new ResponseEntity<>(technicianService.updateTechnician(id, technicianDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnician(@PathVariable Long id){
        technicianService.deleteTechnician(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
