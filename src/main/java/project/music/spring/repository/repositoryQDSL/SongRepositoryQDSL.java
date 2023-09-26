package project.music.spring.repository.repositoryQDSL;

import org.springframework.data.repository.NoRepositoryBean;
import project.music.spring.model.entity.Song;

import java.util.List;

@NoRepositoryBean
public interface SongRepositoryQDSL {

    List<Song> findSongByDuration();
}
