package org.iesvdm.videoclub;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class VideoclubApplicationTests {


    @Autowired
    private PeliculaRepository peliculaRepository;
    @Test
    void contextLoads() {
    }


    @Test
    void textPeliculaCategoriaManyToMany(){



        Pelicula pelicula = Pelicula.builder().titulo("Indiana Jones")
                .descripcion("Pelicula para toda la faminila de aventura")
                .anyoLanzamiento("1990").idioma("Español").idiomaOriginal("Ingles")
                .duracion(Duration.parse("PT1H40M")).rentalRate(new BigDecimal("20.50"))
                .duracionAlquiler(Period.of(0, 1, 15))
                .clasificacion("R")
                .caracteristicasEspeciales("Trailers, Comentaries")
                .categorias(new HashSet<>()).ultimaActualizacion(new Date())
                .build();


        Categoria categoria = Categoria.builder().nombre("Aventura").peliculas(new HashSet<>())
                .ultimaActualizacion(new Date()).build();

        pelicula.getCategorias().add(categoria);

        peliculaRepository.save(pelicula);

        List<Pelicula> peliculaList = peliculaRepository.findAll();

        assertThat(peliculaList.get(0).getCategorias().iterator().next().equals(categoria));
    }

}
