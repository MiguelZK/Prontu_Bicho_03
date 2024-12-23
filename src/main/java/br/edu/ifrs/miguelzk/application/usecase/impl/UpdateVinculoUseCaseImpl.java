package br.edu.ifrs.miguelzk.application.usecase.impl;

import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VinculoResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.UpdateVinculoUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Usuario;
import br.edu.ifrs.miguelzk.domain.entities.Vinculo;
import br.edu.ifrs.miguelzk.domain.repository.VinculoRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;

public class UpdateVinculoUseCaseImpl implements UpdateVinculoUseCase {

  private final ModelMapper modelMapper;
  private VinculoRepository vinculoRepository;

  public UpdateVinculoUseCaseImpl(VinculoRepository vinculoRepository, ModelMapper modelMapper) {
    this.vinculoRepository = vinculoRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public VinculoResponseDTO execute(Long id, VinculoRequestDTO dto) {
    Vinculo vinculoExistente = vinculoRepository.findVinculoById(id);
    if (vinculoExistente == null) {
      throw new EntityNotFoundException("Usuário não encontrado");
    }
    Vinculo vinculo = modelMapper.map(dto, Vinculo.class);
    Vinculo vinculoSaved = vinculoRepository.update(vinculo);
    return modelMapper.map(vinculoSaved, VinculoResponseDTO.class);
  }

}
