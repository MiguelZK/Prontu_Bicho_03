package br.edu.ifrs.miguelzk.application.usecase;

import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioResponseDTO;

public interface UpdateUsuarioUseCase {
    UsuarioResponseDTO execute(Long idUsuario, UsuarioRequestDTO dto);
}
