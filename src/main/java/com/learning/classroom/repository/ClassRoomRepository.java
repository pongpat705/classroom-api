package com.learning.classroom.repository;

import com.learning.classroom.entity.ClassRoomEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassRoomRepository {

    private JdbcTemplate jdbcTemplate;

    public ClassRoomRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClassRoomEntity> findAll(){


        String sql = "select id, room \n" +
                "from classroom ";

        return this.jdbcTemplate.query(sql, new RowMapper<ClassRoomEntity>() {
            @Override
            public ClassRoomEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                ClassRoomEntity roomEntity = new ClassRoomEntity();
                int cols = 1;
                roomEntity.setId(resultSet.getLong(cols++));
                roomEntity.setRoom(resultSet.getString(cols));
                return roomEntity;
            }
        });
    }

    public ClassRoomEntity findById(Long id){


        String sql = "select id, room \n" +
                "from classroom where id = ?";
        List<Object> params = new ArrayList<>();
        params.add(id);


        List<ClassRoomEntity> resultList = this.jdbcTemplate.query(sql, params.toArray(), new RowMapper<ClassRoomEntity>() {
            @Override
            public ClassRoomEntity mapRow(ResultSet resultSet, int i) throws SQLException {
                ClassRoomEntity roomEntity = new ClassRoomEntity();
                int cols = 1;
                roomEntity.setId(resultSet.getLong(cols++));
                roomEntity.setRoom(resultSet.getString(cols));
                return roomEntity;
            }
        });
        ClassRoomEntity result = null;
        if(resultList.size()>0) {
            result = resultList.get(0);
        }

        return result;
    }

    public int addClassRoom(ClassRoomEntity classRoomEntity) {
        String sql = "insert into classroom \n" +
                "(room) \n" +
                "value \n" +
                "(?) ";
        List<Object> params = new ArrayList<>();
        params.add(classRoomEntity.getRoom());

        return this.jdbcTemplate.update(sql, params.toArray());
    }

    public int updateClassRoom(ClassRoomEntity classRoomEntity) {

        String sql = "update classroom\n " +
                "set room = ? \n" +
                "where id = ?";


        List<Object> params = new ArrayList<>();
        params.add(classRoomEntity.getRoom());
        params.add(classRoomEntity.getId());

        return this.jdbcTemplate.update(sql, params.toArray());
    }
}
