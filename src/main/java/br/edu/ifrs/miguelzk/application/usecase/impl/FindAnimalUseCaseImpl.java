package br.edu.ifrs.miguelzk.application.usecase.impl;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.miguelzk.application.dto.AnimalComColecoesResponseDTO;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import br.edu.ifrs.miguelzk.application.dto.AnimalResponseDTO;
import br.edu.ifrs.miguelzk.application.usecase.FindAnimalUseCase;
import br.edu.ifrs.miguelzk.domain.entities.Animal;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;

public class FindAnimalUseCaseImpl implements FindAnimalUseCase {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    public FindAnimalUseCaseImpl(AnimalRepository animalRepository, ModelMapper modelMapper) {
        this.animalRepository = animalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AnimalResponseDTO> execute() {
        List<AnimalResponseDTO> listAnimalResponseDTO = new ArrayList<>();
        List<Animal> listAnimal = animalRepository.findAnimalAll();

        for (Animal animal : listAnimal) {
            listAnimalResponseDTO.add(modelMapper.map(animal, AnimalResponseDTO.class));
        }

        return listAnimalResponseDTO;
    }

    @Override
    public AnimalComColecoesResponseDTO findAnimalComColecoesExecute(Long id) {
        try {
            Animal animal = animalRepository.findAnimalById(id);

            if (animal == null) {
                throw new NotFoundException("Animal não encontrado");
            }
            return modelMapper.map(animal, AnimalComColecoesResponseDTO.class);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Animal não encontrado");
        }
    }

    @Override
    public AnimalResponseDTO execute(Long id) {
        try {
            Animal animal = animalRepository.findAnimalById(id);

            if (animal == null) {
              throw new NotFoundException("Animal não encontrado");
            }
            return modelMapper.map(animal, AnimalResponseDTO.class);
        } catch (IllegalArgumentException e) {
          throw new BadRequestException("Animal não encontrado");
        }
    }

    @Override
    public List<AnimalResponseDTO> execute(String nomeAnimal) {
        List<AnimalResponseDTO> listAnimalResponseDTO = new ArrayList<>();
        List<Animal> listAnimal = animalRepository.findAnimalByName(nomeAnimal);

        for (Animal animal : listAnimal) {
            listAnimalResponseDTO.add(modelMapper.map(animal, AnimalResponseDTO.class));
        }

        return listAnimalResponseDTO;
    }

}
