package project.music.spring.repository.repositoryQDSL;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.music.spring.model.entity.QSong;
import project.music.spring.model.entity.Song;

import java.util.List;

@Repository
public class SongRepositoryQDSLImpl implements SongRepositoryQDSL{

    @Autowired
    private EntityManager entityManager;

    public List<Song> findSongByDuration() {
        JPAQuery<Song> query = new JPAQuery<>(entityManager);
        QSong song = QSong.song;
        query.select(song).from(song).where(song.duration.lt(4));

        return query.limit(3).fetch();
    }

}
