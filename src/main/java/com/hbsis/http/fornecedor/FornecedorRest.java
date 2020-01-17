package com.hbsis.http.fornecedor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fornecedores")
public class FornecedorRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorRest.class);
    private final FornecedorService fornecedorService;
    private final IFornecedorRepository iFornecedorRepository;

    @Autowired
    public FornecedorRest(FornecedorService fornecedorService, IFornecedorRepository iFornecedorRepository) {
        this.fornecedorService = fornecedorService;
        this.iFornecedorRepository = iFornecedorRepository;
    }

    @PostMapping("/save")
    public FornecedorDTO save(@RequestBody FornecedorDTO FornecedorDTO) {
        LOGGER.info("Persistence is being done in 'Fornecedores'...");
        LOGGER.debug("Payaload: {}", FornecedorDTO);

        return this.fornecedorService.save(FornecedorDTO);
    }

    @GetMapping("/{id}")
    public FornecedorDTO find(@PathVariable("id") Long id) {
        LOGGER.info("Catching 'Funcionario' with ID ->: [{}]", id);

        return this.fornecedorService.findById(id);
    }

    @GetMapping("/findFornecedor")
    public List<Fornecedor> findAll() {
        LOGGER.info("Catching List from 'Fornecedores'");

        return this.iFornecedorRepository.findAll();
    }

    @PutMapping("/{id}")
    public FornecedorDTO udpate(@PathVariable("id") Long id, @RequestBody FornecedorDTO fornecedorDTO) {
        LOGGER.info("Catching Update from ID ->: [{}]", id);
        LOGGER.debug("Payload: {}", fornecedorDTO);

        return this.fornecedorService.update(fornecedorDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        LOGGER.info("Deleting 'Fornecedores' with ID ->: {}", id);

        this.fornecedorService.delete(id);
    }
}