package Tema.Service;

import Tema.repository.ArtistiJdbcRepository;
import Tema.repository.LogerJdbcRepository;
import Tema.repository.SpectacolJdbcRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class ServiceConfiguration {

    @Bean
    public LogerJdbcRepository getLogerRepo() throws SQLException, ClassNotFoundException {
        return new LogerJdbcRepository();
    }

    @Bean
    public ArtistiJdbcRepository getArtistiRepo() throws SQLException, ClassNotFoundException {
        return new ArtistiJdbcRepository();
    }

    @Bean
    public SpectacolJdbcRepository getSpectacolRepo() throws SQLException, ClassNotFoundException {
        return new SpectacolJdbcRepository();
    }

    @Bean
    public Service getService() throws SQLException, ClassNotFoundException {
        return new Service( getArtistiRepo(), getSpectacolRepo(),getLogerRepo());
    }

}