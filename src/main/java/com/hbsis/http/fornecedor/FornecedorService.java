package com.hbsis.http.fornecedor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FornecedorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FornecedorService.class);

    private final IFornecedorRepository iFornecedorRepository;

    public FornecedorService(IFornecedorRepository iFornecedorRepository) {
        this.iFornecedorRepository = iFornecedorRepository;
    }

    public FornecedorDTO save(FornecedorDTO fornecedorDTO) {
        this.validate(fornecedorDTO);
        LOGGER.info("Saving 'Fornecedor'");
        LOGGER.debug("Fornecedor {}", fornecedorDTO);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazao(fornecedorDTO.getRazao());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());
        fornecedor.setNome(fornecedorDTO.getNome());
        fornecedor.setEndereco(fornecedorDTO.getEndereco());
        fornecedor.setTelefone(fornecedorDTO.getTelefone());
        fornecedor.setEmail(fornecedorDTO.getEmail());

        fornecedor = this.iFornecedorRepository.save(fornecedor);

        return FornecedorDTO.of(fornecedor);
    }

    private void validate(FornecedorDTO fornecedorDTO) {
        LOGGER.info("Validationg 'Fornecedor'");

        if (fornecedorDTO == null) {
            throw new IllegalArgumentException("Fornecedor não deve ser nulo");
        }

        //Validação cnpj, se tem 14 caracteres e se são números.
        boolean valida = false;
        String forne = String.valueOf(fornecedorDTO.getCnpj());
        String telefoneN = fornecedorDTO.getTelefone();
        for (int i = 0; i < forne.length(); ++i) {
            char ch = forne.charAt(i);
            if (!(ch >= '0' && ch <= '9')) {
                valida = true;
            }

        }

        if (valida == true) {
            throw new IllegalArgumentException("Caracteres não permitidos.");
        }

        if (forne.length() != 14) {
            long valor = forne.length();
            throw new IllegalArgumentException(String.format("O número de caracteres permitidos é 14! Você está colocando %s", valor));
        }
    }

    public FornecedorDTO findById(Long id) {
        Optional<Fornecedor> fornecedorOptional = this.iFornecedorRepository.findById(id);

        if (fornecedorOptional.isPresent()) {
            Fornecedor fornecedor = fornecedorOptional.get();
            return FornecedorDTO.of(fornecedor);
        }
        throw new IllegalArgumentException(String.format("ID -> %s , doesn't exist!", id));
    }

    public FornecedorDTO update(FornecedorDTO fornecedorDTO, Long id) {
        this.validate(fornecedorDTO);
        Optional<Fornecedor> fornecedorExisteOptional = this.iFornecedorRepository.findById(id);

        if (fornecedorExisteOptional.isPresent()) {
            Fornecedor fornecedorExistente = fornecedorExisteOptional.get();
            LOGGER.info("Updating 'Fornecedor' with ID -> [{}]", fornecedorDTO.getId());
            LOGGER.debug("Payload: {}", fornecedorDTO);
            LOGGER.debug("'Fornecedor' already exists: {}", fornecedorExistente);

            fornecedorExistente.setRazao(fornecedorExistente.getRazao());
            fornecedorExistente.setNome(fornecedorExistente.getNome());
            fornecedorExistente.setEndereco(fornecedorExistente.getEndereco());
            fornecedorExistente.setTelefone(fornecedorExistente.getTelefone());
            fornecedorExistente.setEmail(fornecedorExistente.getEmail());
            fornecedorExistente.setCnpj(fornecedorExistente.getCnpj());
            fornecedorExistente = this.iFornecedorRepository.save(fornecedorExistente);

            return FornecedorDTO.of(fornecedorExistente);
        }
        throw new IllegalArgumentException(String.format("ID %s não existe", id));
    }

    public void delete(Long id) {
        LOGGER.info("Executando delete em Fornecedor de ID -> [{}]", id);
        this.iFornecedorRepository.deleteById(id);
    }
}