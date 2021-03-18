package com.learning.classroom.service;

import com.learning.classroom.entity.ClassRoomEntity;
import com.learning.classroom.model.ResponseModel;
import com.learning.classroom.repository.ClassRoomRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomService {

    private ClassRoomRepository classRoomRepository;

    public ClassRoomService(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }

    public ResponseModel<List<ClassRoomEntity>> getClassRoom(){

        ResponseModel<List<ClassRoomEntity>> result = new ResponseModel<>();

        try{
            List<ClassRoomEntity> data = this.classRoomRepository.findAll();
            result.setData(data);
            result.setStatus("OK");
            result.setMessage("");
        }catch (Exception e) {
            result.setStatus("ERROR");
            result.setMessage("data base error "+ e.getMessage());
        }

        return result;
    }

    public ResponseModel<ClassRoomEntity> getClassRoomById(Long id){

        ResponseModel<ClassRoomEntity> result = new ResponseModel<>();

        try{
            ClassRoomEntity data = this.classRoomRepository.findById(id);
            result.setData(data);
            result.setStatus("OK");
            result.setMessage("");
        }catch (Exception e) {
            result.setStatus("ERROR");
            result.setMessage("data base error "+ e.getMessage());
        }

        return result;
    }

    public ResponseModel<Void> addClassRoom(ClassRoomEntity classRoomEntity){

        ResponseModel<Void> result = new ResponseModel<>();

        try{
            int affected = 0;
            if(!StringUtils.isAllBlank(classRoomEntity.getRoom())){
                affected = this.classRoomRepository.addClassRoom(classRoomEntity);
            }

            if(affected == 0){
                result.setStatus("ERROR");
                result.setMessage("data not added");
            } else {
                result.setStatus("OK");
                result.setMessage("");
            }

        }catch (Exception e) {
            result.setStatus("ERROR");
            result.setMessage("data base error "+ e.getMessage());
        }
        return result;
    }

    public ResponseModel<Void> updateClassRoom(ClassRoomEntity classRoomEntity){

        ResponseModel<Void> result = new ResponseModel<>();

        try{
            int affected = this.classRoomRepository.updateClassRoom(classRoomEntity);

            if(affected == 0){
                result.setStatus("ERROR");
                result.setMessage("data not updated");
            } else {
                result.setStatus("OK");
                result.setMessage("");
            }

        } catch (Exception e) {
            result.setStatus("ERROR");
            result.setMessage("data base error "+ e.getMessage());
        }

        return result;

    }
}
