package br.edu.ifrs.miguelzk.infrastructure.persistence;

import br.edu.ifrs.miguelzk.application.dto.AnimalRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.UsuarioRequestDTO;
import br.edu.ifrs.miguelzk.application.dto.VinculoRequestDTO;
import br.edu.ifrs.miguelzk.application.service.AnimalService;
import br.edu.ifrs.miguelzk.application.service.UsuarioService;
import br.edu.ifrs.miguelzk.application.service.VinculoService;
import br.edu.ifrs.miguelzk.domain.enums.PorteCachorro;
import br.edu.ifrs.miguelzk.domain.repository.AnimalRepository;
import br.edu.ifrs.miguelzk.domain.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class DataLoaderVinculos {

    private static final Logger LOG = Logger.getLogger(DataLoaderVinculos.class);
    private final UsuarioService usuarioService;
    private final AnimalService animalService;
    private final VinculoService vinculoService;
    private final UsuarioRepository usuarioRepository;
    private final AnimalRepository animalRepository;

/*    public DataLoaderInterface(UsuarioRepository usuarioRepository, AnimalRepository animalRepository
            , UsuarioService usuarioService, AnimalService animalService, VinculoService vinculoService) {
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
        this.usuarioService = usuarioService;
        this.animalService = animalService;
        this.vinculoService = vinculoService;
    }*/

    @jakarta.inject.Inject
    public DataLoaderVinculos(UsuarioService usuarioService, AnimalService animalService
            , VinculoService vinculoService, UsuarioRepository usuarioRepository, AnimalRepository animalRepository) {
        this.usuarioService = usuarioService;
        this.animalService = animalService;
        this.vinculoService = vinculoService;
        this.usuarioRepository = usuarioRepository;
        this.animalRepository = animalRepository;
    }

    @PostConstruct
    public void loadData() {
        // Verificar se os dados já foram inseridos para evitar duplicidade
        if (usuarioRepository.contaUsuarios() == 0 && animalRepository.contaAnimais() == 0) {
            LOG.info("Carregando os dados iniciais no Dataloader...");
            // Inserir usuários
            UsuarioRequestDTO usuario1 = new UsuarioRequestDTO();
            usuario1.setRole("admin, user");
            usuario1.setPassword("senha123");
            usuario1.setNomeCompleto("Miguel Zanona Krasner");
            usuario1.setUserName("miguel");

            UsuarioRequestDTO usuario2 = new UsuarioRequestDTO();
            usuario2.setRole("user");
            usuario2.setPassword("senha456");
            usuario2.setNomeCompleto("Laura I Marcaccio Arce");
            usuario2.setUserName("laura");

            UsuarioRequestDTO usuario3 = new UsuarioRequestDTO();
            usuario3.setRole("user");
            usuario3.setPassword("senha789");
            usuario3.setNomeCompleto("Caroline Teixeira Reinoso");
            usuario3.setUserName("carol");

            usuarioService.create(usuario1);
            usuarioService.create(usuario2);
            usuarioService.create(usuario3);

            // Inserir animais
            AnimalRequestDTO animal1 = new AnimalRequestDTO();
            animal1.setNomeAnimal("Shenka");
            animal1.setPorteCachorro(PorteCachorro.PEQUENO);

            AnimalRequestDTO animal2 = new AnimalRequestDTO();
            animal2.setNomeAnimal("Agnes");
            animal2.setPorteCachorro(PorteCachorro.PEQUENO);

            AnimalRequestDTO animal3 = new AnimalRequestDTO();
            animal3.setNomeAnimal("Bidu");
            animal3.setPorteCachorro(PorteCachorro.PEQUENO);

            animalService.create(animal1);
            animalService.create(animal2);
            animalService.create(animal3);

            // Relacionar usuários e animais programaticamente
            VinculoRequestDTO vinculo1 = new VinculoRequestDTO();
            vinculo1.setNomeAnimal("Shenka");
            vinculo1.setIdAnimal(1L);
            vinculo1.setIdUsuario(1L);

            VinculoRequestDTO vinculo2 = new VinculoRequestDTO();
            vinculo2.setNomeAnimal("Shenka");
            vinculo2.setIdAnimal(1L);
            vinculo2.setIdUsuario(2L);

            VinculoRequestDTO vinculo3 = new VinculoRequestDTO();
            vinculo3.setNomeAnimal("Agnes");
            vinculo3.setIdAnimal(2L);
            vinculo3.setIdUsuario(3L);

            VinculoRequestDTO vinculo4 = new VinculoRequestDTO();
            vinculo4.setNomeAnimal("Bidu");
            vinculo4.setIdAnimal(3L);
            vinculo4.setIdUsuario(3L);

            vinculoService.create(vinculo1);
            vinculoService.create(vinculo2);
            vinculoService.create(vinculo3);
            vinculoService.create(vinculo4);

            LOG.info("Usuario 1: " + usuario1);

            LOG.info("Dados carregados com sucesso.");
        }
    }
}